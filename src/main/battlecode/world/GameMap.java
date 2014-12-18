package battlecode.world;

import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotType;
import battlecode.common.TerrainTile;
import battlecode.serial.GenericGameMap;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * The class represents the map in the game world on which
 * objects interact.
 */
public class GameMap implements GenericGameMap {

    private static final long serialVersionUID = -2068896916199851260L;
    /**
     * The default game seed.
     */
    public static final int GAME_DEFAULT_SEED = 6370;
    /**
     * The default game maxiumum number of rounds.
     */
    public static final int GAME_DEFAULT_MAX_ROUNDS = 10000;
    /**
     * The width and height of the map.
     */
    private final int mapWidth, mapHeight;
    /**
     * The tiles on the map.
     */
    private final TerrainTile[][] mapTiles;
    /**
     * The scalar field of the ore on the map.
     */
    private final int[][] mapInitialOre;
    /**
     * The coordinates of the origin.
     */
    private final int mapOriginX, mapOriginY;
    /**
     * The random seed contained in the map file
     */
    private final int seed;
    /**
     * The maximum number of rounds in the game
     */
    private final int maxRounds;

    /**
     * The name of the map
     */
    private final String mapName;

    /**
     * Represents the various integer properties a GameMap
     * can have.
     */
    static enum MapProperties {
        WIDTH, HEIGHT, SEED, MAX_ROUNDS
    }

    public GameMap(GameMap gm) {
        this.mapWidth = gm.mapWidth;
        this.mapHeight = gm.mapHeight;
        this.mapTiles = new TerrainTile[this.mapWidth][this.mapHeight];
        this.mapInitialOre = new int[this.mapWidth][this.mapHeight];
        for (int i = 0; i < this.mapWidth; i++) {
            System.arraycopy(gm.mapTiles[i], 0, this.mapTiles[i], 0, this.mapHeight);
            System.arraycopy(gm.mapInitialOre[i], 0, this.mapInitialOre[i], 0, this.mapHeight);
        }
        this.mapOriginX = gm.mapOriginX;
        this.mapOriginY = gm.mapOriginY;
        this.seed = gm.seed;
        this.maxRounds = gm.maxRounds;
        this.mapName = gm.mapName;
    }

    /**
     * Creates a new GameMap from the given properties, tiles, and territory
     * locations.
     *
     * @param mapProperties      a map of MapProperties to their integer values containing dimensions, etc.
     * @param mapTiles           a matrix of TerrainTypes representing the map
     */
    GameMap(Map<MapProperties, Integer> mapProperties, TerrainTile[][] mapTiles, int[][] mapInitialOre, String mapName) {
        if (mapProperties.containsKey(MapProperties.WIDTH))
            this.mapWidth = mapProperties.get(MapProperties.WIDTH);
        else
            this.mapWidth = mapTiles[0].length;

        if (mapProperties.containsKey(MapProperties.HEIGHT))
            this.mapHeight = mapProperties.get(MapProperties.HEIGHT);
        else
            this.mapHeight = mapTiles.length;

        if (mapProperties.containsKey(MapProperties.SEED))
            this.seed = mapProperties.get(MapProperties.SEED);
        else
            this.seed = GAME_DEFAULT_SEED;

        if (mapProperties.containsKey(MapProperties.MAX_ROUNDS))
            this.maxRounds = mapProperties.get(MapProperties.MAX_ROUNDS);
        else
            this.maxRounds = GAME_DEFAULT_MAX_ROUNDS;

        //if (mapProperties.containsKey(MapProperties.MIN_POINTS))
        //	this.minPoints = mapProperties.get(MapProperties.MIN_POINTS);
        //else this.minPoints = GAME_DEFAULT_MIN_POINTS;

        Random rand = new Random(this.seed);
        this.mapOriginX = rand.nextInt(32001) - 16000;
        this.mapOriginY = rand.nextInt(32001) - 16000;
        
        this.mapTiles = mapTiles;

        this.mapInitialOre = mapInitialOre;

        this.mapName = mapName;
    }

    /**
     * Returns the width of this map.
     *
     * @return the width of this map
     */
    public int getWidth() {
        return mapWidth;
    }

    /**
     * Returns the height of this map.
     *
     * @return the height of this map
     */
    public int getHeight() {
        return mapHeight;
    }

    public String getMapName() {
        return mapName;
    }

    /**
     * Determines whether or not the location at the specified
     * unshifted coordinates is on the map.
     *
     * @param x the (shifted) x-coordinate of the location
     * @param y the (shifted) y-coordinate of the location
     * @return true if the given coordinates are on the map,
     *         false if they're not
     */
    private boolean onTheMap(int x, int y) {
        return (x >= mapOriginX && y >= mapOriginY && x < mapOriginX + mapWidth && y < mapOriginY + mapHeight);
    }

    /**
     * Determines whether or not the specified location is on the map.
     *
     * @param location the MapLocation to test
     * @return true if the given location is on the map,
     *         false if it's not
     */
    public boolean onTheMap(MapLocation location) {
        return onTheMap(location.x, location.y);
    }

    /**
     * Determines the type of the terrain on the map at the
     * given location.
     *
     * @param location the MapLocation to test
     * @return the TerrainTile at the given location
     *         of the map, and TerrainTile.OFF_MAP if the given location is
     *         off the map.
     */
    public TerrainTile getTerrainTile(MapLocation location) {
        if (!onTheMap(location))
            return TerrainTile.OFF_MAP;

        return mapTiles[location.x - mapOriginX][location.y - mapOriginY];
    }

    /**
     * Returns a two-dimensional array of terrain data for this map.
     *
     * @return the map's terrain in a 2D array
     */
    public TerrainTile[][] getTerrainMatrix() {
        return mapTiles;
    }

    /**
     * Determines the amount of ore on the map at the
     * given location.
     *
     * @param location the MapLocation to test
     * @return the amount of ore in the given location, or 0 if off the map
     */
    public int getInitialOre(MapLocation location) {
        if (!onTheMap(location))
            return 0;

        return mapInitialOre[location.x - mapOriginX][location.y - mapOriginY];
    }

    /**
     * Returns a two-dimensional array of ore data for this map.
     *
     * @return the map's ore in a 2D array
     */
    public int[][] getInitialOreMatrix() {
        return mapInitialOre;
    }

    /**
     * Gets the maximum number of rounds for this game.
     *
     * @return the maximum number of rounds for this game
     */
    public int getMaxRounds() {
        return maxRounds;
    }

    public int getStraightMaxRounds() {
        return maxRounds;
    }

    public int getSeed() {
        return seed;
    }

    /**
     * Gets the origin (i.e., upper left corner) of the map
     *
     * @return the origin of the map
     */
    public MapLocation getMapOrigin() {
        return new MapLocation(mapOriginX, mapOriginY);
    }

    public static class MapMemory {
        private final GameMap map;
        private final boolean[][] seen;
        private final int[][] supplyLevel;

        public MapMemory(GameMap map) {
            this.map = map;
            this.seen = new boolean[map.getWidth()][map.getHeight()];
            this.supplyLevel = new int[map.getWidth()][map.getHeight()];
        }

        public void rememberLocations(MapLocation loc, int radiusSquared, Map<MapLocation, Integer> droppedSupplies) {
            MapLocation[] locs = MapLocation.getAllMapLocationsWithinRadiusSq(loc, radiusSquared);

            for (int i = 0; i < locs.length; i++) {
                int x = locs[i].x - map.mapOriginX;
                int y = locs[i].y - map.mapOriginY;
                if (x >= 0 && x < map.getWidth() && y >= 0 && y < map.getHeight()) {
                    seen[x][y] = true;
                    if (droppedSupplies.containsKey(locs[i])) {
                        supplyLevel[x][y] = droppedSupplies.get(locs[i]);
                    }
                }
            }
        }

        public boolean seenBefore(MapLocation loc) {
            int X = loc.x - map.mapOriginX;
            int Y = loc.y - map.mapOriginY;

            if (X >= 0 && X < map.getWidth() && Y >= 0 && Y < map.getHeight() && seen[X][Y]) {
                return seen[X][Y];
            } else {
                return false;
            }
        }

        public TerrainTile recallTerrain(MapLocation loc) {
            int X = loc.x - map.mapOriginX;
            int Y = loc.y - map.mapOriginY;

            if (X < 0 || X >= map.getWidth() || Y < 0 || Y >= map.getHeight()) {
                return TerrainTile.OFF_MAP;
            } else if (seen[X][Y]) {
                return map.getTerrainTile(loc);
            } else {
                return TerrainTile.UNKNOWN;
            }
        }

        public int recallSupplyLevel(MapLocation loc) {
            int X = loc.x - map.mapOriginX;
            int Y = loc.y - map.mapOriginY;

            if (X < 0 || X >= map.getWidth() || Y < 0 || Y >= map.getHeight()) {
                return 0;
            } else if (seen[X][Y]) {
                return supplyLevel[X][Y];
            } else {
                return -1;
            }
        }
    }
}
