package com.keepthinker.example.spring.lifecircle;

import com.keepthinker.example.spring.lifecircle.zoo.Zookeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.Lifecycle;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanLifeCircleObserver implements BeanNameAware, BeanClassLoaderAware, EnvironmentAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, Lifecycle {

    private static Logger logger = LoggerFactory.getLogger(BeanLifeCircleObserver.class);

    private int lifetime;

    private Zookeeper zookeeper;

    public BeanLifeCircleObserver(){
        logger.info("java constructor invoked");
    }

    @Autowired
    private void setZookeeper(Zookeeper zookeeper){
        this.zookeeper = zookeeper;
        logger.info("property zookeeper set");
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    /**
     * 如果这个 Bean 已经实现了 BeanNameAware 接口，会调用它实现的 setBeanName(String)
     * 方法，此处传递的就是 Spring 配置文件中 Bean 的 id 值
     */
    @Override
    public void setBeanName(String name) {
        logger.info("-------setBeanName|{}", name);
    }

    /**
     * 如果这个Bean实现了BeanClassLoaderAware接口
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        logger.info("-------setBeanClassLoader|{}", classLoader);
    }

    /**
     * 如果这个 Bean 已经实现了 BeanFactoryAware 接口，会调用它实现的 setBeanFactory，
     * setBeanFactory(BeanFactory)传递的是 Spring 工厂自身（可以用这个方式来获取其它 Bean，
     * 只需在 Spring 配置文件中配置一个普通的 Bean 就可以）。
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("-------setBeanFactory|{}", beanFactory);
    }

    /**
     * 如果这个Bean实现了EnvironmentAware接口
     */
    @Override
    public void setEnvironment(Environment environment) {
        logger.info("-------setEnvironment|{}", environment);
    }

    /**
     *如果这个 Bean 已经实现了 ApplicationContextAware 接口，会调用
     * setApplicationContext(ApplicationContext)方法，传入 Spring 上下文（同样这个方式也
     * 可以实现步骤 4 的内容，但比 4 更好，因为 ApplicationContext 是 BeanFactory 的子接
     * 口，有更多的实现方法）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("-------setApplicationContext|{}", applicationContext);
    }

    @PostConstruct
    public void PostConstructMethod() {
        logger.info("-------@PostConstructMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("-------afterPropertiesSet");
    }

    public void xmlInitMethod() {
        logger.info("-------XmlInitMethod");
    }

    @PreDestroy
    public void preDestroyMethod() throws Exception {
        logger.info("-------@PreDestroyMethod");
    }
    /**
     * Destroy 过期自动清理阶段
     * 当 Bean 不再需要时，会经过清理阶段，如果 Bean 实现了 DisposableBean 这个接口，会调
     * 用那个其实现的 destroy()方法；
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        logger.info("-------destroy");
    }

    public void xmlDestroyMethod() {
        logger.info("-------xmlDestroyMethod");
    }

    @Override
    public void start() {
        logger.info("Lifecycle.start()");
    }

    @Override
    public void stop() {
        logger.info("Lifecycle.stop()");
    }

    @Override
    public boolean isRunning() {
        logger.info("Lifecycle.isRunning()");
        return false;
    }

    /*
    destroy-method
    自配置清理，如果这个 Bean 的 Spring 配置中配置了 destroy-method 属性，会自动调用其配置的
    销毁方法。
     */

    /*
    bean 标签有两个重要的属性（init-method 和 destroy-method）。用它们你可以自己定制
    初始化和注销方法。它们也有相应的注解（@PostConstruct 和@PreDestroy） 。
    <bean id="" class="" init-method="初始化方法" destroy-method="销毁方法">
     */
}
