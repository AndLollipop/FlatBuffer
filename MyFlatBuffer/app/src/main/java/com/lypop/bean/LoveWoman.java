// automatically generated, do not modify

package com.lypop.bean;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
public class LoveWoman extends Table {
  public static LoveWoman getRootAsLoveWoman(ByteBuffer _bb) { return getRootAsLoveWoman(_bb, new LoveWoman()); }
  public static LoveWoman getRootAsLoveWoman(ByteBuffer _bb, LoveWoman obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public LoveWoman __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public int age() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public boolean isBeatiful() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createLoveWoman(FlatBufferBuilder builder,
      int name,
      int age,
      boolean isBeatiful) {
    builder.startObject(3);
    LoveWoman.addAge(builder, age);
    LoveWoman.addName(builder, name);
    LoveWoman.addIsBeatiful(builder, isBeatiful);
    return LoveWoman.endLoveWoman(builder);
  }

  public static void startLoveWoman(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addAge(FlatBufferBuilder builder, int age) { builder.addInt(1, age, 0); }
  public static void addIsBeatiful(FlatBufferBuilder builder, boolean isBeatiful) { builder.addBoolean(2, isBeatiful, false); }
  public static int endLoveWoman(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

