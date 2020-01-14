import {Config} from '../../config';
import * as cst from '../../constants';
import {AllImages} from '../../imageloader';
import {Block,Transaction} from 'battlecode-playback';
import {Scorecard} from '../index';

import {schema} from 'battlecode-playback';

const hex: Object = {
  1: "#db3627",
  2: "#4f7ee6"
};

export type StatBar = {
  bar: HTMLDivElement,
  label: HTMLSpanElement
};

/**
* Loads game stats: team name, soups, robot count
* We make the distinction between:
*    1) Team names - a global string identifier i.e. "Teh Devs"
*    2) Team IDs - each Battlecode team has a unique numeric team ID i.e. 0
*    3) In-game ID - used to distinguish teams in the current match only;
*       team 1 is red, team 2 is blue
*/
export default class Stats {

  readonly div: HTMLDivElement;
  private readonly images: AllImages;

  // Key is the team ID
  private robotTds: Object = {}; // Secondary key is robot type
  private statBars: Map<number, { soups: StatBar }>;
  private statsTableElement: HTMLTableElement;

  private readonly teams: HTMLDivElement;

  // Scorecard for tournaments
  private redID: number;
  private blueID: number;
  private scorecard: Scorecard = new Scorecard();


  private robotConsole: HTMLDivElement;

  private blockchain: HTMLDivElement;

  private conf: Config;

  // Note: robot types and number of teams are currently fixed regardless of
  // match info. Keep in mind if we ever change these, or implement this less
  // statically.

  readonly robots: schema.BodyType[] = [
    cst.MINER, cst.LANDSCAPER, cst.DRONE, cst.NET_GUN, cst.REFINERY, cst.VAPORATOR, cst.HQ, cst.DESIGN_SCHOOL, cst.FULFILLMENT_CENTER
  ];

  constructor(conf: Config, images: AllImages) {
    this.conf = conf;
    this.images = images;
    this.div = document.createElement("div");

    this.teams = document.createElement("div");
    this.div.appendChild(this.teams);
    this.div.appendChild(this.scorecard.div);

    let teamNames: Array<string> = ["?????", "?????"];
    let teamIDs: Array<number> = [1, 2];
    this.statsTableElement = document.createElement("table");
    this.initializeGame(teamNames, teamIDs);
  }

  /**
   * Colored banner labeled with the given teamName
   */
  private teamHeaderNode(teamName: string, inGameID: number, avatar?: string) {
    let teamHeader: HTMLDivElement = document.createElement("div");
    teamHeader.className += ' teamHeader';

    if (avatar) {
      let teamAvatarNode = document.createElement('img');
      teamAvatarNode.src = avatar;
      teamAvatarNode.className = "teamAvatar";
      let teamAvatarDiv = document.createElement('div');
      teamAvatarDiv.appendChild(teamAvatarNode);
      teamHeader.appendChild(teamAvatarDiv);
    }

    let teamNameNode = document.createTextNode(teamName);
    teamHeader.style.backgroundColor = hex[inGameID];
    teamHeader.appendChild(teamNameNode);
    return teamHeader;
  }

  /**
   * Create the table that displays the robot images along with their counts.
   * Uses the teamID to decide which color image to display.
   */
  private robotTable(teamID: number, inGameID: number): HTMLTableElement {
    let table: HTMLTableElement = document.createElement("table");
    table.setAttribute("align", "center");

    // Create the table row with the robot images
    let robotImages: HTMLTableRowElement = document.createElement("tr");
    
    // Create the table row with the robot counts
    let robotCounts: HTMLTableRowElement = document.createElement("tr");

    for (let robot of this.robots) {
      let robotName: string = cst.bodyTypeToString(robot);
      let tdRobot: HTMLTableCellElement = document.createElement("td");

      if(robotName === "drone"){
        tdRobot.appendChild(this.images.robot[robotName]['carry'][inGameID]);
        tdRobot.appendChild(this.images.robot[robotName]['empty'][inGameID]);
      }
      else{
        tdRobot.appendChild(this.images.robot[robotName][inGameID]);
      }

      if(robotName === 'vaporator'){
        // Wrap around
        table.appendChild(robotImages);
        robotImages = document.createElement("tr");
        table.appendChild(robotCounts);
        robotCounts = document.createElement("tr");
      }
      robotImages.appendChild(tdRobot);

      let tdCount: HTMLTableCellElement = this.robotTds[teamID][robot];
      robotCounts.appendChild(tdCount);
    }
    table.appendChild(robotImages);
    table.appendChild(robotCounts);

    return table;
  }

  private statsTable(teamIDs: Array<number>): HTMLTableElement {
    const table = document.createElement("table");
    const bars = document.createElement("tr");
    const counts = document.createElement("tr");
    const labels = document.createElement("tr");
    table.id = "stats-table";
    bars.id = "stats-bars";
    table.setAttribute("align", "center");

    // Duplicate of the following, but left just in case
    // teamIDs.forEach((id: number) => {
    //   const bar = document.createElement("td");
    //   bar.height = "150";
    //   bar.vAlign = "bottom";
    //   // TODO: figure out if statbars.get(id) can actually be null??
    //   // bar.appendChild(this.statBars.get(id)!.bullets.bar);
    //   bars.appendChild(bar);

    //   const count = document.createElement("td");
    //   // TODO: figure out if statbars.get(id) can actually be null??
    //   // count.appendChild(this.statBars.get(id)!.bullets.label);
    //   counts.appendChild(count);
    // });

    teamIDs.forEach((id: number) => {
      const bar = document.createElement("td");
      bar.height = "150";
      bar.vAlign = "bottom";
      // TODO: figure out if statbars.get(id) can actually be null??
      bar.appendChild(this.statBars.get(id)!.soups.bar);
      bars.appendChild(bar);

      const count = document.createElement("td");
      // TODO: figure out if statbars.get(id) can actually be null??
      count.appendChild(this.statBars.get(id)!.soups.label);
      counts.appendChild(count);
    });

    // Label for soup
    const labelSoups = document.createElement("td");
    labelSoups.colSpan = 2;
    labelSoups.innerText = "Soup";

    table.appendChild(bars);
    table.appendChild(counts);
    table.appendChild(labels);
    labels.appendChild(labelSoups);
    return table;
  }

  /**
   * Clear the current stats bar and reinitialize it with the given teams.
   */
  initializeGame(teamNames: Array<string>, teamIDs: Array<number>, teamAvatars?: Array<string>){
    // Remove the previous match info
    while (this.teams.firstChild) {
      this.teams.removeChild(this.teams.firstChild);
    }
    this.robotTds = {};
    this.statBars = new Map<number, { soups: StatBar }>();

    // Store the team IDs as red and blue
    if (teamIDs.length >= 2) {
      this.redID = teamIDs[0];
      this.blueID = teamIDs[1];
    }


    // Add view toggles
    this.div.append(this.addViewOptions());
    
    // Populate with new info
    // Add a section to the stats bar for each team in the match
    for (var index = 0; index < teamIDs.length; index++) {
      // Collect identifying information
      let teamID = teamIDs[index];
      let teamName = teamNames[index];
      let teamAvatar = teamAvatars? teamAvatars[index] : undefined;
      let inGameID = index + 1; // teams start at index 1
      console.log("Team: " + inGameID);

      // A div element containing all stats information about this team
      let teamDiv = document.createElement("div");

      // Create td elements for the robot counts and store them in robotTds
      // so we can update these robot counts later; maps robot type to count
      let initialRobotCount: Object = {};
      for (let robot of this.robots) {
        let td: HTMLTableCellElement = document.createElement("td");
        td.innerHTML = "0";
        initialRobotCount[robot] = td;
      }
      this.robotTds[teamID] = initialRobotCount;

      // Create the stat bar for bullets
      // let bullets = document.createElement("div");
      // bullets.className = "stat-bar";
      // bullets.style.backgroundColor = hex[inGameID];
      // let bulletsSpan = document.createElement("span");
      // bulletsSpan.innerHTML = "0";

      // Create the stat bar for Soups
      let soups = document.createElement("div");
      soups.className = "stat-bar";
      soups.style.backgroundColor = hex[inGameID];
      let soupsSpan = document.createElement("span");
      soupsSpan.innerHTML = "0";

      // Store the stat bars
      this.statBars.set(teamID, {
        soups: {
          bar: soups,
          label: soupsSpan
        }
      });

      // Add the team name banner and the robot count table
      teamDiv.appendChild(this.teamHeaderNode(teamName, inGameID, teamAvatar));
      teamDiv.appendChild(this.robotTable(teamID, inGameID));
      teamDiv.appendChild(document.createElement("br"));

      this.teams.appendChild(teamDiv);
    }

    // Add stats table
    this.statsTableElement.remove();
    this.statsTableElement = this.statsTable(teamIDs);
    this.div.appendChild(this.statsTableElement);
    
    this.div.appendChild(document.createElement('br'));

    const bl = document.createElement("span");
    bl.innerText = "Blockchain";
    this.div.appendChild(bl);
    this.div.appendChild(document.createElement('br'));
    this.div.appendChild(this.blockchainViewer());
  }

  blockchainViewer(): HTMLDivElement {
    // create a blockchain
    this.blockchain = document.createElement('div');

    // make it a console
    this.blockchain.id = "blockchain";
    this.blockchain.className = "console";

    return this.blockchain;
  }

  public showBlock(block: Block): void {
    if (block !== undefined) {
      const div = document.createElement("div");
      // Replace \n with <br>
      const span = document.createElement("span");
      let text = "Block #" + String(block.round) + ":<br><br>";
      block.messages.forEach(t => {
          text += "cost: " + t.cost + "<br>message: " + t.message.join(', ') + "<br><br>";
      });
      span.innerHTML = text;
      div.appendChild(span);
      while (this.blockchain.firstChild) {
        this.blockchain.removeChild(this.blockchain.firstChild);
      }
      this.blockchain.appendChild(div);
    }
  }

  addViewOptions(){
    let viewOptionForm = document.createElement("form");
    viewOptionForm.setAttribute("id", "viewoptionformid");
    
    let pollutionInp = document.createElement("input");
    let pollutionLabel = document.createElement("label");
    let pollutionSpan = document.createElement("span");
    pollutionSpan.setAttribute("class", "viewspan");
    pollutionInp.checked = true;
    pollutionInp.setAttribute("type", "checkbox");
    pollutionInp.setAttribute("name", "view");
    pollutionInp.setAttribute("value", "pollution");
    pollutionInp.setAttribute("id", "pollutionid");
    pollutionLabel.setAttribute("for", "pollutionid");
    pollutionInp.setAttribute("class", "checkbox");
    pollutionInp.onclick = () => { this.conf.viewPoll = !this.conf.viewPoll; };

    pollutionSpan.innerHTML = "pollution";
    pollutionLabel.appendChild(pollutionInp);
    pollutionLabel.appendChild(pollutionSpan);
    viewOptionForm.appendChild(pollutionLabel);

    let waterInp = document.createElement("input");
    let waterLabel = document.createElement("label");
    let waterSpan = document.createElement("span");
    waterSpan.setAttribute("class", "viewspan");
    waterInp.checked = true;
    waterInp.setAttribute("type", "checkbox");
    waterInp.setAttribute("name", "view");
    waterInp.setAttribute("value", "water");
    waterInp.setAttribute("id", "waterid");
    waterLabel.setAttribute("for", "waterid");
    waterInp.setAttribute("class", "checkbox");
    waterInp.onclick = () => { this.conf.viewWater = !this.conf.viewWater; };

    waterSpan.innerHTML = "water";
    waterLabel.appendChild(waterInp);
    waterLabel.appendChild(waterSpan);
    viewOptionForm.appendChild(waterLabel);

    let dirtInp = document.createElement("input");
    let dirtLabel = document.createElement("label");
    let dirtSpan = document.createElement("span");
    dirtSpan.setAttribute("class", "viewspan");
    dirtInp.checked = true;
    dirtInp.setAttribute("type", "checkbox");
    dirtInp.setAttribute("name", "view");
    dirtInp.setAttribute("value", "dirt");
    dirtInp.setAttribute("id", "dirtid");
    dirtLabel.setAttribute("for", "dirtid");
    dirtInp.setAttribute("class", "checkbox");
    dirtInp.onclick = () => { this.conf.viewDirt = !this.conf.viewDirt; };

    dirtSpan.innerHTML = "dirt";
    dirtLabel.appendChild(dirtInp);
    dirtLabel.appendChild(dirtSpan);
    viewOptionForm.appendChild(dirtLabel);

    return viewOptionForm;
  }

  /**
   * Change the robot count on the stats bar
   */
  setRobotCount(teamID: number, robotType: schema.BodyType, count: number) {
    let td: HTMLTableCellElement = this.robotTds[teamID][robotType];
    td.innerHTML = String(count);
  }

  /**
   * Change the soups of the given team
   */
  setSoups(teamID: number, count: number) {
    // TODO: figure out if statbars.get(id) can actually be null??
    const statBar: StatBar = this.statBars.get(teamID)!.soups;
    statBar.label.innerText = String(count);
    const maxSoup = 1000;
    statBar.bar.style.height = `${Math.min(100 * count / maxSoup, 100)}%`;

    if (this.images.star.parentNode === statBar.bar) {
      this.images.star.remove();
    }
  }

  /**
   * Change the bullets of the given team
   */
  // setBullets(teamID: number, count: number) {
  //   // TODO: figure out if statbars.get(id) can actually be null??
  //   const statBar: StatBar = this.statBars.get(teamID)!.bullets;
  //   statBar.label.innerText = String(count.toPrecision(5));
  //   statBar.bar.style.height = `${100 * count / cst.BULLET_THRESH}%`;
  // }


  /**
   * Resets the scorecard to 0-0. Call this at the BEGINNING of a game.
   */
  resetScore(): void {
    this.scorecard.setScore(0, 0);
  }

  /**
   * Changes the scorecard by giving 1 point to the winning team of a match.
   * Call this at the END of each match. (we may have to invoke this manually? :/)
   */
  updateScore(winnerID: number) {
    if (winnerID === this.redID) {
      this.scorecard.incrementA();
    } else if (winnerID === this.blueID) {
      this.scorecard.incrementB();
    }
  }
}
