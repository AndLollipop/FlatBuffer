// automatically generated, do not modify

package com.lypop.bean;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class People extends Table {
  public static People getRootAsPeople(ByteBuffer _bb) { return getRootAsPeople(_bb, new People()); }
  public static People getRootAsPeople(ByteBuffer _bb, People obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public People __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public int age() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String sex() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sexAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public Action action(int j) { return action(new Action(), j); }
  public Action action(Action obj, int j) { int o = __offset(10); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int actionLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }

  public static int createPeople(FlatBufferBuilder builder,
      int name,
      int age,
      int sex,
      int action) {
    builder.startObject(4);
    People.addAction(builder, action);
    People.addSex(builder, sex);
    People.addAge(builder, age);
    People.addName(builder, name);
    return People.endPeople(builder);
  }

  public static void startPeople(FlatBufferBuilder builder) { builder.startObject(4); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addAge(FlatBufferBuilder builder, int age) { builder.addInt(1, age, 0); }
  public static void addSex(FlatBufferBuilder builder, int sexOffset) { builder.addOffset(2, sexOffset, 0); }
  public static void addAction(FlatBufferBuilder builder, int actionOffset) { builder.addOffset(3, actionOffset, 0); }
  public static int createActionVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startActionVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endPeople(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishPeopleBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

