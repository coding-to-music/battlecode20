// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * A table of vectors.
 */
public final class VecTable extends Table {
  public static VecTable getRootAsVecTable(ByteBuffer _bb) { return getRootAsVecTable(_bb, new VecTable()); }
  public static VecTable getRootAsVecTable(ByteBuffer _bb, VecTable obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public VecTable __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public float xs(int j) { int o = __offset(4); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int xsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer xsAsByteBuffer() { return __vector_as_bytebuffer(4, 4); }
  public float ys(int j) { int o = __offset(6); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int ysLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer ysAsByteBuffer() { return __vector_as_bytebuffer(6, 4); }

  public static int createVecTable(FlatBufferBuilder builder,
      int xsOffset,
      int ysOffset) {
    builder.startObject(2);
    VecTable.addYs(builder, ysOffset);
    VecTable.addXs(builder, xsOffset);
    return VecTable.endVecTable(builder);
  }

  public static void startVecTable(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addXs(FlatBufferBuilder builder, int xsOffset) { builder.addOffset(0, xsOffset, 0); }
  public static int createXsVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startXsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addYs(FlatBufferBuilder builder, int ysOffset) { builder.addOffset(1, ysOffset, 0); }
  public static int createYsVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startYsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endVecTable(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

