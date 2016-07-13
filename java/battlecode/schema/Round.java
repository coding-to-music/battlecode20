// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * A single time-step in a Game.
 * The bulk of the data in the file is stored in tables like this.
 * Note that a struct-of-arrays format is more space efficient than an array-
 * of-structs.
 */
public final class Round extends Table {
  public static Round getRootAsRound(ByteBuffer _bb) { return getRootAsRound(_bb, new Round()); }
  public static Round getRootAsRound(ByteBuffer _bb, Round obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Round __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  /**
   * The IDs of bodies that moved.
   */
  public int movedIDs(int j) { int o = __offset(4); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int movedIDsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer movedIDsAsByteBuffer() { return __vector_as_bytebuffer(4, 4); }
  /**
   * The new locations of bodies that have moved. They are defined to be in
   * their new locations at exactly the time round.number*dt.
   */
  public VecTable movedLocs() { return movedLocs(new VecTable()); }
  public VecTable movedLocs(VecTable obj) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * New bodies.
   */
  public SpawnedBodyTable spawned() { return spawned(new SpawnedBodyTable()); }
  public SpawnedBodyTable spawned(SpawnedBodyTable obj) { int o = __offset(8); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The IDs of bodies with changed health.
   */
  public int healthChangedIDs(int j) { int o = __offset(10); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int healthChangedIDsLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer healthChangedIDsAsByteBuffer() { return __vector_as_bytebuffer(10, 4); }
  /**
   * The new health levels of bodies with changed health.
   */
  public float healthChangeLevels(int j) { int o = __offset(12); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int healthChangeLevelsLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer healthChangeLevelsAsByteBuffer() { return __vector_as_bytebuffer(12, 4); }
  /**
   * The IDs of bodies that died.
   */
  public int diedIDs(int j) { int o = __offset(14); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int diedIDsLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer diedIDsAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  /**
   * The IDs of bullets that died.
   */
  public int diedBulletIDs(int j) { int o = __offset(16); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int diedBulletIDsLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer diedBulletIDsAsByteBuffer() { return __vector_as_bytebuffer(16, 4); }
  /**
   * The IDs of robots that performed actions.
   * IDs may repeat.
   */
  public int actionIDs(int j) { int o = __offset(18); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int actionIDsLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionIDsAsByteBuffer() { return __vector_as_bytebuffer(18, 4); }
  /**
   * The actions performed.
   */
  public byte actions(int j) { int o = __offset(20); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int actionsLength() { int o = __offset(20); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionsAsByteBuffer() { return __vector_as_bytebuffer(20, 1); }
  /**
   * The 'targets' of the performed actions. Actions without targets may have
   * any target (typically 0).
   */
  public int actionTargets(int j) { int o = __offset(22); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int actionTargetsLength() { int o = __offset(22); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer actionTargetsAsByteBuffer() { return __vector_as_bytebuffer(22, 4); }

  public static int createRound(FlatBufferBuilder builder,
      int movedIDsOffset,
      int movedLocsOffset,
      int spawnedOffset,
      int healthChangedIDsOffset,
      int healthChangeLevelsOffset,
      int diedIDsOffset,
      int diedBulletIDsOffset,
      int actionIDsOffset,
      int actionsOffset,
      int actionTargetsOffset) {
    builder.startObject(10);
    Round.addActionTargets(builder, actionTargetsOffset);
    Round.addActions(builder, actionsOffset);
    Round.addActionIDs(builder, actionIDsOffset);
    Round.addDiedBulletIDs(builder, diedBulletIDsOffset);
    Round.addDiedIDs(builder, diedIDsOffset);
    Round.addHealthChangeLevels(builder, healthChangeLevelsOffset);
    Round.addHealthChangedIDs(builder, healthChangedIDsOffset);
    Round.addSpawned(builder, spawnedOffset);
    Round.addMovedLocs(builder, movedLocsOffset);
    Round.addMovedIDs(builder, movedIDsOffset);
    return Round.endRound(builder);
  }

  public static void startRound(FlatBufferBuilder builder) { builder.startObject(10); }
  public static void addMovedIDs(FlatBufferBuilder builder, int movedIDsOffset) { builder.addOffset(0, movedIDsOffset, 0); }
  public static int createMovedIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startMovedIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMovedLocs(FlatBufferBuilder builder, int movedLocsOffset) { builder.addOffset(1, movedLocsOffset, 0); }
  public static void addSpawned(FlatBufferBuilder builder, int spawnedOffset) { builder.addOffset(2, spawnedOffset, 0); }
  public static void addHealthChangedIDs(FlatBufferBuilder builder, int healthChangedIDsOffset) { builder.addOffset(3, healthChangedIDsOffset, 0); }
  public static int createHealthChangedIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startHealthChangedIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addHealthChangeLevels(FlatBufferBuilder builder, int healthChangeLevelsOffset) { builder.addOffset(4, healthChangeLevelsOffset, 0); }
  public static int createHealthChangeLevelsVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startHealthChangeLevelsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addDiedIDs(FlatBufferBuilder builder, int diedIDsOffset) { builder.addOffset(5, diedIDsOffset, 0); }
  public static int createDiedIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDiedIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addDiedBulletIDs(FlatBufferBuilder builder, int diedBulletIDsOffset) { builder.addOffset(6, diedBulletIDsOffset, 0); }
  public static int createDiedBulletIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDiedBulletIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActionIDs(FlatBufferBuilder builder, int actionIDsOffset) { builder.addOffset(7, actionIDsOffset, 0); }
  public static int createActionIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startActionIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActions(FlatBufferBuilder builder, int actionsOffset) { builder.addOffset(8, actionsOffset, 0); }
  public static int createActionsVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startActionsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addActionTargets(FlatBufferBuilder builder, int actionTargetsOffset) { builder.addOffset(9, actionTargetsOffset, 0); }
  public static int createActionTargetsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startActionTargetsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endRound(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

