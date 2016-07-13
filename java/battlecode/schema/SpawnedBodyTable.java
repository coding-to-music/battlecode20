// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * A list of new bodies to be placed on the map.
 */
public final class SpawnedBodyTable extends Table {
  public static SpawnedBodyTable getRootAsSpawnedBodyTable(ByteBuffer _bb) { return getRootAsSpawnedBodyTable(_bb, new SpawnedBodyTable()); }
  public static SpawnedBodyTable getRootAsSpawnedBodyTable(ByteBuffer _bb, SpawnedBodyTable obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public SpawnedBodyTable __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  /**
   * The numeric ID of the new bodies.
   * Will never be negative.
   * There will only be one body/bullet with a particular ID at a time.
   * So, there will never be two robots with the same ID, or a robot and
   * a bullet with the same ID.
   */
  public int robotIDs(int j) { int o = __offset(4); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int robotIDsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer robotIDsAsByteBuffer() { return __vector_as_bytebuffer(4, 4); }
  /**
   * The teams of the new bodies.
   */
  public byte teamIDs(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int teamIDsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer teamIDsAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  /**
   * The types of the new bodies.
   */
  public byte types(int j) { int o = __offset(8); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int typesLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer typesAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  /**
   * The radii of the bodies.
   */
  public float radii(int j) { int o = __offset(10); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int radiiLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer radiiAsByteBuffer() { return __vector_as_bytebuffer(10, 4); }
  /**
   * The locations of the bodies.
   */
  public VecTable locs() { return locs(new VecTable()); }
  public VecTable locs(VecTable obj) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }

  public static int createSpawnedBodyTable(FlatBufferBuilder builder,
      int robotIDsOffset,
      int teamIDsOffset,
      int typesOffset,
      int radiiOffset,
      int locsOffset) {
    builder.startObject(5);
    SpawnedBodyTable.addLocs(builder, locsOffset);
    SpawnedBodyTable.addRadii(builder, radiiOffset);
    SpawnedBodyTable.addTypes(builder, typesOffset);
    SpawnedBodyTable.addTeamIDs(builder, teamIDsOffset);
    SpawnedBodyTable.addRobotIDs(builder, robotIDsOffset);
    return SpawnedBodyTable.endSpawnedBodyTable(builder);
  }

  public static void startSpawnedBodyTable(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addRobotIDs(FlatBufferBuilder builder, int robotIDsOffset) { builder.addOffset(0, robotIDsOffset, 0); }
  public static int createRobotIDsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startRobotIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTeamIDs(FlatBufferBuilder builder, int teamIDsOffset) { builder.addOffset(1, teamIDsOffset, 0); }
  public static int createTeamIDsVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startTeamIDsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addTypes(FlatBufferBuilder builder, int typesOffset) { builder.addOffset(2, typesOffset, 0); }
  public static int createTypesVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startTypesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addRadii(FlatBufferBuilder builder, int radiiOffset) { builder.addOffset(3, radiiOffset, 0); }
  public static int createRadiiVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startRadiiVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addLocs(FlatBufferBuilder builder, int locsOffset) { builder.addOffset(4, locsOffset, 0); }
  public static int endSpawnedBodyTable(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

