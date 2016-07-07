// automatically generated, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * The final event sent in the game.
 */
public final class GameFooter extends Table {
  public static GameFooter getRootAsGameFooter(ByteBuffer _bb) { return getRootAsGameFooter(_bb, new GameFooter()); }
  public static GameFooter getRootAsGameFooter(ByteBuffer _bb, GameFooter obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public GameFooter __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  /**
   * The ID of the winning team of the game.
   */
  public int winner() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0; }

  public static int createGameFooter(FlatBufferBuilder builder,
      int winner) {
    builder.startObject(1);
    GameFooter.addWinner(builder, winner);
    return GameFooter.endGameFooter(builder);
  }

  public static void startGameFooter(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addWinner(FlatBufferBuilder builder, int winner) { builder.addByte(0, (byte)winner, 0); }
  public static int endGameFooter(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

