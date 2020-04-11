##一、简单题
###1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？
1）动态sql主要是为了满足开发人员写通用sql的需求而提供的一个功能，根据使用者传递的参数值的情况动态的拼接sql。  
2）动态sql最常用的是set、where、if、choose、foreach标签。  
3）原理实现主要是MyBatis将配置文件中的动态sql标签都封装成sqlNode，
之后在sqlNode apply的时候使用OGNL表达式去处理动态标签上的表达式最后封装成BoundSql，
执行器Executor拿着BoundSql去执行预处理执行。

###2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？
1）MyBatis是支持懒加载的，针对于通过MyBatis关联查询的结果集支持懒加载。  
2）当开启懒加载时，会在存在关联查询的时候使用动态代理对返回结果做动态代理增强，
这个代理对象会在真正使用关联查询的结果集时再发起查询获取对应的数据。

###3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？
1）MyBatis的执行器常用的有三种SimpleExecutor、BatchExecutor、ReuseExecutor如果开启二级缓存会将这些执行期再进行包装成带缓存的CachingExecutor
在这个执行器中会先去查寻二级缓存，二级缓存不存在再去执行sql  
2）SimpleExecutor是每次执行sql都会生成一个statement，获取结果集之后关闭，MyBatis默认使用该执行器。  
BatchExecutor会将多次除了查询以外，需要操作数据库statement通过addBatch()缓存起来，等到真正执行executeBatch的时候才真正的去一次性执行。  
ReuseExecutor会将每次使用的PrepareStatement对象通过预处理sql作为key进行缓存，复用PrepareStatement对象。等到下一次再遇见这个sql直接取出来该PrepareStatement对象。
###4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？
1）一级缓存结构为HashMap，而二级缓存默认是HashMap结构，二级缓存可以自定义，MyBatis为SqlSession默认开启一级缓存，二级缓存需要使用者设置cacheEnable为true打开  
一级缓存的key是通过statementID、分页参数RowBounds、以及预处理sql封装而成的一个对象，value存储的是查询的结果。  
二级缓存的key是有两级key，第一级key就是缓存本身，第二级key就是一级缓存的key。value存储的是查询结果。
2）一级缓存的作用范围是SqlSession级别，每一个SqlSession之间相互隔离，  
如果是由同一个SqlSessionFactory创建的SqlSession，其作用范围是namespace级别，只要namespace相同就共享该缓存。  
3）当调用SqlSession的clearCache的时候或者有增删改操作的commit时一级缓存会清空，  
当SqlSession有增删改操作的commit时也会清空二级缓存，除此之外还可以通过设置flushCache属性来对二级缓存进行刷新


###5、简述Mybatis的插件运行原理，以及如何编写一个插件？
1）MyBatis为四大核心组件提供插件功能，通过遍历所有的插件指定的类型和方法名称及参数列表能够判断是否使用动态代理的方式将当前插件增强，
没有一个插件就会多代理一次，在运行的时候实际上是运行这一些代理对象。  
2）通过在自定义类上声明注解Interceptor指明要拦截哪一个核心组件(Executor、StatementHandler、ParameterHandler、ResultSetHandler)，  
然后指明要拦截核心组件的哪一个方法，以及方法的参数列表  
实现MyBatis的Interceptor接口，重写intercept、plugin、setProperties方法  
在MyBatis的核心配置文件中配置plugins声明plugin标签指明interceptor标签属性，如果需要可以再标签内部使用property标签进行配置常量

##二、编程题

###请完善自定义持久层框架IPersistence，在现有代码基础上添加、修改及删除功能。【需要采用getMapper方式】
请看my_persistence项目
测试样例请看dangdang项目com.youngforcoding.test.FrameTest