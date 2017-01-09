import Metadata from './metadata';
import GameWorld from './gameworld';
import { schema } from 'battlecode-schema';
export declare type Log = {
    team: string;
    robotType: string;
    id: number;
    round: number;
    text: string;
};
/**
 * A timeline of a match. Allows you to see what the state of the match was,
 * at any particular time.
 *
 * Call timeline.seek() to request the state of the world at a particular
 * time; then call timeline.compute() to allow the timeline to perform computations.
 *
 * Note that this API is a state machine: you call methods on it,
 * then call compute() to let it do its thing, and then inspect it
 * to see what its current state is.
 *
 * This is awkward, but less so than callbacks.
 */
export default class Match {
    /**
     * How frequently to store a snapshots of the gameworld.
     */
    readonly snapshotEvery: number;
    /**
     * Snapshots of the game world.
     * [0] is round 0 (the one stored in the GameMap), [1] is round
     * snapshotEvery * 1, [2] is round snapshotEvery * 2, etc.
     */
    readonly snapshots: Array<GameWorld>;
    /**
     * Sets of changes.
     * [1] is the change between round 0 and 1, [2] is the change between
     * round 1 and 2, etc.
     * [0] is not stored.
     */
    readonly deltas: Array<schema.Round>;
    /**
     * The logs of this match.
     */
    readonly logs: Array<Log>;
    /**
     * The current game world.
     * DO NOT CACHE this reference between calls to seek() and compute(), it may
     * change.
     */
    readonly current: GameWorld;
    private _current;
    /**
     * The farthest snapshot of the game world we've evaluated.
     * It is possible that _farthest === _current.
     */
    private _farthest;
    /**
     * The round we're attempting to seek to.
     */
    readonly seekTo: number;
    private _seekTo;
    /**
     * Whether we've arrived at the seek point.
     */
    readonly arrived: boolean;
    /**
     * The last turn in the match.
     */
    readonly lastTurn: number | null;
    private _lastTurn;
    /**
     * The ID of the winner of this match.
     */
    readonly winner: number | null;
    private _winner;
    /**
     * Whether this match has fully loaded.
     */
    readonly finished: boolean;
    /**
     * The maximum turn that can happen in this match.
     */
    readonly maxTurn: number;
    /**
     * Create a Timeline.
     */
    constructor(header: schema.MatchHeader, meta: Metadata);
    /**
     * Store a schema.Round and the logs contained in it.
     */
    applyDelta(delta: schema.Round): void;
    /**
     * Finish the timeline.
     */
    applyFooter(footer: schema.MatchFooter): void;
    /**
     * Attempt to set seekTo to a particular point.
     * Return whether or not it is possible to seek to that round;
     * if we don't have deltas to it, we can't.
     * If we can, each call to compute() will update state until current.turn === seekTo
     */
    seek(round: number): boolean;
    /**
     * Perform computations for some amount of time.
     * We try to overshoot timeGoal as little as possible; however, if turn applications start taking a long time, we may overshoot it arbitrarily far.
     * If timeGoal is 0, we'll compute until we're done.
     */
    compute(timeGoal?: number): void;
    /**
     * Apply a delta to a GameWorld, based on world.turn.
     */
    private _processDelta(world);
}
