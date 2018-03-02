# shiro-demo
该项目搭建了一个shiro常用结构

![](http://www.yqplayer.com/netpicyd/7d62513fc6d7e898a16c596e87d295cc.png)

#主界面
![](http://www.yqplayer.com/netpicyd/eeed69e6fc9d7760c9dbf629c9e2ebc4.png)
## session为1分钟

    <!-- 会话管理器 -->
    <bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
        <!-- session的失效时长，单位毫秒 -->
        <property name="globalSessionTimeout" value="60000"/>
        <!-- 删除失效的session -->
        <property name="deleteInvalidSessions" value="true"/>
    </bean>

## rememberMe为2分钟

    <!-- 记住我cookie -->
    <bean id="rememberMeCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <!--rememberMe时cookie的名字-->
        <property name="name" value="rememberMe" />
        <!-- 记住我cookie生效时间120秒 -->
        <property name="maxAge" value="120" />
    </bean>

> 该项目不适用缓存管理器