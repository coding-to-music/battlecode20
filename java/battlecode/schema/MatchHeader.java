// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * Sent to start a match.
 */
public final class MatchHeader extends Table {
  public static MatchHeader getRootAsMatchHeader(ByteBuffer _bb) { return getRootAsMatchHeader(_bb, new MatchHeader()); }
  public static MatchHeader getRootAsMatchHeader(ByteBuffer _bb, MatchHeader obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public MatchHeader __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  /**
   * The map the match was played on.
   */
  public GameMap map() { return map(new GameMap()); }
  public GameMap map(GameMap obj) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  /**
   * The maximum number of rounds in this match.
   */
  public int maxRounds() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createMatchHeader(FlatBufferBuilder builder,
      int mapOffset,
      int maxRounds) {
    builder.startObject(2);
    MatchHeader.addMaxRounds(builder, maxRounds);
    MatchHeader.addMap(builder, mapOffset);
    return MatchHeader.endMatchHeader(builder);
  }

  public static void startMatchHeader(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addMap(FlatBufferBuilder builder, int mapOffset) { builder.addOffset(0, mapOffset, 0); }
  public static void addMaxRounds(FlatBufferBuilder builder, int maxRounds) { builder.addInt(1, maxRounds, 0); }
  public static int endMatchHeader(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

