# Java 8  Streams 

### Stream 总览
Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。而和迭代器又不同的是，Stream 可以并行化操作，迭代器只能命令式地、串行化操作。

当我们使用一个流的时候，通常包括三个基本步骤：   
获取一个数据源（source）→ 数据转换→执行操作获取想要的结果

有多种方式生成 Stream Source：
从 Collection 和数组
Collection.stream()
Collection.parallelStream()
Arrays.stream(T array) or Stream.of()
从 BufferedReader
java.io.BufferedReader.lines()
静态工厂
java.util.stream.IntStream.range()
java.nio.file.Files.walk()
自己构建
java.util.Spliterator
其它
Random.ints()
BitSet.stream()
Pattern.splitAsStream(java.lang.CharSequence)
JarFile.stream()

流的操作类型分为两种：
Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。

Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。

demo
```Java
int sum = widgets.stream()
.filter(w -> w.getColor() == RED)
 .mapToInt(w -> w.getWeight())
 .sum();
 ```
简单说，对 Stream 的使用就是实现一个 filter-map-reduce 过程，产生一个最终结果，或者导致一个副作用（side effect）

### 流的构造与转换
 构造流的几种常见方法
 ```Java
 // 1. Individual values
Stream stream = Stream.of("a", "b", "c");
// 2. Arrays
String [] strArray = new String[] {"a", "b", "c"};
stream = Stream.of(strArray);
stream = Arrays.stream(strArray);
// 3. Collections
List<String> list = Arrays.asList(strArray);
stream = list.stream();
```

流转换为其它数据结构
 ```Java
// 1. Array
String[] strArray1 = stream.toArray(String[]::new);
// 2. Collection
List<String> list1 = stream.collect(Collectors.toList());
List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
Set set1 = stream.collect(Collectors.toSet());
Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
// 3. String
String str = stream.collect(Collectors.joining()).toString();
 ```

### 流的操作
Intermediate：
map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered

Terminal：
forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator

Short-circuiting：
anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

 ### 总结：
 Stream 的特性可以归纳为：
不是数据结构  
它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。   
它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
所有 Stream 的操作必须以 lambda 表达式为参数  
不支持索引访问   
你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。  
很容易生成数组或者 List  
惰性化  
很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。  
Intermediate 操作永远是惰性化的。  
并行能力  
当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。  
可以是无限的  
集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。  
 

