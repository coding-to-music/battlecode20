// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * Necessary due to flatbuffers requiring events to be wrapped in tables.
 */
public final class EventWrapper extends Table {
  public static EventWrapper getRootAsEventWrapper(ByteBuffer _bb) { return getRootAsEventWrapper(_bb, new EventWrapper()); }
  public static EventWrapper getRootAsEventWrapper(ByteBuffer _bb, EventWrapper obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public EventWrapper __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte eType() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public Table e(Table obj) { int o = __offset(6); return o != 0 ? __union(obj, o) : null; }

  public static int createEventWrapper(FlatBufferBuilder builder,
      byte e_type,
      int eOffset) {
    builder.startObject(2);
    EventWrapper.addE(builder, eOffset);
    EventWrapper.addEType(builder, e_type);
    return EventWrapper.endEventWrapper(builder);
  }

  public static void startEventWrapper(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addEType(FlatBufferBuilder builder, byte eType) { builder.addByte(0, eType, 0); }
  public static void addE(FlatBufferBuilder builder, int eOffset) { builder.addOffset(1, eOffset, 0); }
  public static int endEventWrapper(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

