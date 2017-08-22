// automatically generated, do not modify

package com.lypop.bean;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Action extends Table {
  public static Action getRootAsAction(ByteBuffer _bb) { return getRootAsAction(_bb, new Action()); }
  public static Action getRootAsAction(ByteBuffer _bb, Action obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Action __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public boolean isHead() { int o = __offset(4); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean isRun() { int o = __offset(6); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean isLove() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public LoveWoman lovePeople(int j) { return lovePeople(new LoveWoman(), j); }
  public LoveWoman lovePeople(LoveWoman obj, int j) { int o = __offset(10); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int lovePeopleLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }

  public static int createAction(FlatBufferBuilder builder,
      boolean isHead,
      boolean isRun,
      boolean isLove,
      int lovePeople) {
    builder.startObject(4);
    Action.addLovePeople(builder, lovePeople);
    Action.addIsLove(builder, isLove);
    Action.addIsRun(builder, isRun);
    Action.addIsHead(builder, isHead);
    return Action.endAction(builder);
  }

  public static void startAction(FlatBufferBuilder builder) { builder.startObject(4); }
  public static void addIsHead(FlatBufferBuilder builder, boolean isHead) { builder.addBoolean(0, isHead, false); }
  public static void addIsRun(FlatBufferBuilder builder, boolean isRun) { builder.addBoolean(1, isRun, false); }
  public static void addIsLove(FlatBufferBuilder builder, boolean isLove) { builder.addBoolean(2, isLove, false); }
  public static void addLovePeople(FlatBufferBuilder builder, int lovePeopleOffset) { builder.addOffset(3, lovePeopleOffset, 0); }
  public static int createLovePeopleVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startLovePeopleVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endAction(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

