# FlatBuffer
这个开源库最开始是由Google研发的，专注于提供更优秀的性能
具体的链接：https://github.com/google/flatbuffers

首先我们来说一下FlatBuffer的优点

1. 直接读取序列化数据，而不需要解析或者解包，FlatBuffer把数据层级结构保存在一个扁平化的二进制缓存中，同时能够保持直接获取里面的数据，而不需要解析，并且还能保证数据结构变化的向前向后兼容。
2. 高效的内存使用和速度：FlatBuffer在使用的过程中，不需要额外的内存，几乎接近原始数据在内存中的大小
3. 灵活：数据能够向前向后兼容，并且能够灵活控制你的数据结构
4. 很少的代码侵入性：使用少量的生成的代码即可实现
5. 强数据类型，易于使用，跨平台，几乎语言无关
![image](https://github.com/AndLollipop/FlatBuffer/blob/master/img01.png)
在做 Android 开发的时候，JSON 是最常用的数据序列化技术。我们知道，JSON 的可读性很强，但是序列化和反序列化性能却是最差的。解析的时候，JSON 解析器首先，需要在内存中初始化一个对应的数据结构，这个事件经常会消耗 100ms ~ 200ms2；解析过程中，要产生大量的临时变量，造成 Java 虚拟机的 GC 和内存抖动，解析 20KB 的数据，大概会消耗 100KB 的临时内存。FlatBuffers 就解决了这些问题。

使用方法：
 
我们需要按照IDL定义的数据结构schema，然后在通过flatc编译生成相应的代码。
具体执行命令：

	$ ./flatc –j -b Person.fbs
通过命令可以看出.fbs便是我们要写的IDL数据结构，例如我们要实现下面代码该如何写

	class Person {  
	    String name;
	    int friendshipStatus;
	    Person spouse;
	    List<Person>friends;
	}

IDL数据结构：

	namespace com.race604.fbs;//定义命名空间，执行命令之后也就是相应的包名
	enum FriendshipStatus: int {Friend = 1, NotFriend}
	//定义一个相应的枚举类型
	table Person {  
	  name: string;
	  friendshipStatus: FriendshipStatus = Friend;
	  spouse: Person;//定义一个对象类型
	  friends: [Person];
	  isRun: bool;//定义一个boolean类型
	}
这样我们就写好了IDL结构，这样一看突然发现有点像Kotlin语言有木有。。。下面是我所生成相应的java类
![image](https://github.com/AndLollipop/FlatBuffer/blob/master/img2.png)

下面是我生成的相应的java文件，最后我们要做的便是对数据进行序列化和反序列化的操作了。

在介绍序列化和反序列化的操作之前我们为了更好的理解，先来介绍一下相应的基本原理

正如官方文档的介绍，FlatBuffers就像它的名字所表示的一样，就是把结构化的对象，用一个扁平化的缓冲区保存，简单的来说就是把内存对象数据保存到一个一维的数组中，可以对照以下的图片：
![image](https://github.com/AndLollipop/FlatBuffer/blob/master/img3.png)
可见，FlatBuffers 保存在一个 byte 数组中，有一个“支点”指针（pivot point）以此为界，存储的内容分为两个部分：元数据和数据内容。

其中元数据部分就是数据在前面，其长度等于对象中的字段数量，每个 byte 保存对应字段内容在数组中的索引（从支点位置开始计算）。

如图，上面的 Person 对象第一个字段是 name，其值的索引位置是 1，所以从索引位置 1 开始的字符串，就是 name 字段的值 "John"。第二个字段是 friendshipStatus，其索引值是 6，找到值为 2， 表示 NotFriend。第三个字段是 spouse，也一个 Person 对象，索引值是 12，指向的是此对象的支点位置。第四个字段是一个数组，图中表示的数组为空，所以索引值是 0。

通过上面的解析，可以看出，FlatBuffers 通过自己分配和管理对象的存储，使对象在内存中就是线性结构化的，直接可以把内存内容保存或者发送出去，加载“解析”数据只需要把 byte 数组加载到内存中即可，不需要任何解析，也不产生任何中间变量。
它与具体的机器或者运行环境无关，例如在 Java 中，对象内的内存不依赖 Java 虚拟机的堆内存分配策略实现，所以也是跨平台的。

下面说说 FlatBuffers 的几点缺点：
1. FlatBuffers 需要生成代码，对代码有侵入性
2. 数据序列化没有可读性，不方便 Debug
3. 构建 FlatBuffers 对象比较麻烦，不直观，特别是如果对象比较复杂情况下需要写大段的代码
4. 数据的所有内容需要使用 Schema 严格定义，灵活性不如 JSON
所以针对相应的缺点我们可以总结出使用的场景
1. 项目中有大量数据传输和解析，使用 JSON 成为了性能瓶颈
2. 稳定的数据结构定义
