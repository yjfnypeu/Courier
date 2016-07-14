# Courier
相信大家很多时候。在Activity进行页面传值时。很苦恼，需要定义各种各样的KEY定义传递数据。在传递时如果参数较多。还得需要到目标Activity中去寻找传递的数据应该是怎么传递才是符合逻辑的。某条数据。应该如何传。才是符合逻辑的。现在好了。有了此框架的加入。相信定能使你的Activity代码更加简洁易懂。再遇到界面跳转传递参数过多的情况。那都不叫事！

Courier框架使用编译时注解处理。绿色无反射。完全不用担心你的运行效率问题

如何引进Courier:

- 在工程的根目录的build.gradle中加入：
```
classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
```
- 在要使用的工程module中添加应用apt插件

```
apply plugin: 'com.neenbedankt.android-apt'
```
- 加入引用：

```
dependencies {
	...
    compile 'org.lzh.compiler.courier:courier-api:0.2'
    apt 'org.lzh.compiler.courier:courier-compiler:0.2'
}
```
**Update:**

- 0.2

```
#对Params注解添加inherited属性。指定多层继承结构使用时。生成类是否继承父类参数
```

[具体使用姿势](./USAGE.md)