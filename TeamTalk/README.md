关于web.xml文件:

load-on-startup元素标记容器是否在启动的时候就加载这个servlet(实例化并调用其init()方法)。

2)它的值必须是一个整数，表示servlet应该被载入的顺序

2)当值为0或者大于0时，表示容器在应用启动时就加载并初始化这个servlet；

3)当值小于0或者没有指定时，则表示容器在该servlet被选择时才会去加载。

4)正数的值越小，该servlet的优先级越高，应用启动时就越先加载。

5)当值相同时，容器就会自己选择顺序来加载。

所以，<load-on-startup>x</load-on-startup>，中x的取值1，2，3，4，5代表的是优先级，而非启动延迟时间


在web.xml中通过contextConfigLocation配置spring，contextConfigLocation
 参数定义了要装入的 Spring 配置文件。

如果想装入多个配置文件，可以在 <param-value>
 标记中用逗号作分隔符。


在web.xml里配置Listener
xml 代码如下： 
  < listener >  
       <    listener-class > org.springframework.web.context.ContextLoaderListener listener-class >  
  <   /  listener >   
如果在web.xml里给该Listener指定要加载的xml,如：
xml代码如下：
<!-- spring config -->
      <context-param>
           <param-name>contextConfigLocation</param-name>  
           <param-value>classpath:applicationContext.xml</param-value>
      </context-param>
则会去加载相应的xml,而不会去加载/WEB-INF/下的applicationContext.xml。
但是，如果没有指定的话，默认会去/WEB-INF/下加载applicationContext.xml。


classpath：只会到你的class路径中查找找文件;
classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找

filter 
用于指定WEB容器的过滤器，在请求和响应对象在Servlet处理之前和之后，可以通过此过滤器对这两个对象进行处理。
Filter的匹配原则和Servlet有些不同，只要匹配成功，这些Filter都会在请求链上被调用。


web.xml的url映射的小知识:
<url-pattern>/</url-pattern>  会匹配到/login这样的路径型url，不会匹配到模式为*.jsp这样的后缀型url
<url-pattern>/*</url-pattern> 会匹配所有url：路径型的和后缀型的url(包括/login,*.jsp,*.js和*.html等)
"/" 的设置将会将当前指定的 servlet 设为web应用的默认servlet，原来web容器的默认servlet将被覆盖。

url-pattern：表示哪些请求交给Spring Web MVC处理， “/” 是用来定义默认servlet映射的。也可以如“*.html”表示拦截所有以html为扩展名的请求

url-pattern
它们的作用都是匹配一次请求是否会执行这个Servlet或Filter

精确匹配 —— 如/foo.htm，只会匹配foo.htm这个URL
路径匹配 —— 如/foo/*，会匹配以foo为前缀的URL
后缀匹配 —— 如*.htm，会匹配所有以.htm为后缀的URL

匹配顺序精确匹配->路径匹配->后缀匹配
但是一次请求只会成功匹配到一个Servlet；


springMVC配置文件:

<context:component-scan>
 在xml配置了这个标签后，spring可以自动去扫描base-pack下面或者子包下面的java文件，如果扫描到有@Component @Controller@Service等这些注解的类，则把这些类注册为bean
注意：如果配置了<context:component-scan>那么<context:annotation-config/>标签就可以不用再xml中配置了，因为前者包含了后者。


@Controller, @Service, @Repository是@Component的细化，这三个注解比@Component带有更多的语义，它们分别对应了控制层、服务层、持久层的类

一般来说@Controller（控制层） 是action入口，调用@Service（业务层） ，Service再调用@Repository （持久层）完成数据持久化

@Autowired 自动注入

