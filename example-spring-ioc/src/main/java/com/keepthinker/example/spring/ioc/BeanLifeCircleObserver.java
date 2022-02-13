package com.keepthinker.example.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 2022-02-12 17:38:48,400 [main] DEBUG [org.springframework.context.support.ClassPathXmlApplicationContext]: Bean factory for org.springframework.context.support.ClassPathXmlApplicationContext@28ba21f3: org.springframework.beans.factory.support.DefaultListableBeanFactory@6500df86: defining beans [annotationConfig,beanInsideComponent,beanInsideConfiguration,configA,configB,cglibBean,person,xmlMain,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,abstractAnimal,animal,animalDuplicate,inheritedAnimal,overrideAnimal,earth,earthFromFactory,inner,main,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor2#0,com.keepthinker.example.spring.ioc.postprocessor.MyInstantiationAwareBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.ObscenityRemovingBeanFactoryPostProcessor#0,com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0]; root of factory hierarchy
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Creating shared instance of singleton bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Creating instance of bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.context.annotation.CommonAnnotationBeanPostProcessor]: Found init method on class [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: public void com.keepthinker.example.spring.ioc.BeanLifeCircleObserver.PostConstructMethod()
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.context.annotation.CommonAnnotationBeanPostProcessor]: Registered init method on class [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleElement@8c636b8
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Eagerly caching bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0' to allow for resolving potential circular references
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------setBeanName|com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------setBeanFactory|org.springframework.beans.factory.support.DefaultListableBeanFactory@6500df86: defining beans [annotationConfig,beanInsideComponent,beanInsideConfiguration,configA,configB,cglibBean,person,xmlMain,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,abstractAnimal,animal,animalDuplicate,inheritedAnimal,overrideAnimal,earth,earthFromFactory,inner,main,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor2#0,com.keepthinker.example.spring.ioc.postprocessor.MyInstantiationAwareBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.ObscenityRemovingBeanFactoryPostProcessor#0,com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0,panda,compTiger,confTiger,a,b,myTiger,tiger,propertyConfigInDev]; root of factory hierarchy
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------setApplicationContext|org.springframework.context.support.ClassPathXmlApplicationContext@28ba21f3: startup date [Sat Feb 12 17:38:48 CST 2022]; root of context hierarchy
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.context.annotation.CommonAnnotationBeanPostProcessor]: Invoking init method on bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0': public void com.keepthinker.example.spring.ioc.BeanLifeCircleObserver.PostConstructMethod()
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------PostConstructMethod
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor]: postProcessBeforeInitialization before init0 : com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor]: postProcessBeforeInitialization before init1 : com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Invoking afterPropertiesSet() on bean with name 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------afterPropertiesSet
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Invoking init method  'xmlInitMethod' on bean with name 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------XmlInitMethod
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor]: postProcessAfterInitialization after init0 : com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor]: postProcessAfterInitialization after init1 : com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Finished creating instance of bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,587 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@6500df86: defining beans [annotationConfig,beanInsideComponent,beanInsideConfiguration,configA,configB,cglibBean,person,xmlMain,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,abstractAnimal,animal,animalDuplicate,inheritedAnimal,overrideAnimal,earth,earthFromFactory,inner,main,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor2#0,com.keepthinker.example.spring.ioc.postprocessor.MyInstantiationAwareBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.ObscenityRemovingBeanFactoryPostProcessor#0,com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0,panda,compTiger,confTiger,a,b,myTiger,tiger,propertyConfigInDev]; root of factory hierarchy
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessBeforeInitialization|bean:com.keepthinker.example.spring.ioc.AnnotationConfig$$EnhancerBySpringCGLIB$$ec1b4486@78b1cc93|beanName:annotationConfig
 2022-02-12 17:38:48,587 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessAfterInitialization|bean:com.keepthinker.example.spring.ioc.AnnotationConfig$$EnhancerBySpringCGLIB$$ec1b4486@78b1cc93|beanName:annotationConfig
 2022-02-12 17:38:48,603 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessBeforeInitialization|bean:com.keepthinker.example.spring.ioc.AtBean.BeanInsideComponent@6646153|beanName:beanInsideComponent
 2022-02-12 17:38:48,603 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessAfterInitialization|bean:com.keepthinker.example.spring.ioc.AtBean.BeanInsideComponent@6646153|beanName:beanInsideComponent
 2022-02-12 17:38:48,697 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessBeforeInitialization|bean:com.keepthinker.example.spring.ioc.XmlMain@6af93788|beanName:main
 2022-02-12 17:38:48,697 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessAfterInitialization|bean:com.keepthinker.example.spring.ioc.XmlMain@6af93788|beanName:main
 2022-02-12 17:38:48,697 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Returning cached instance of singleton bean 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:48,697 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessBeforeInitialization|bean:com.keepthinker.example.spring.ioc.atimport.A@ef9296d|beanName:a
 2022-02-12 17:38:48,697 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------postProcessAfterInitialization|bean:com.keepthinker.example.spring.ioc.atimport.A@ef9296d|beanName:a
 2022-02-12 17:38:49,743 [main] DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory]: Destroying singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@6500df86: defining beans [annotationConfig,beanInsideComponent,beanInsideConfiguration,configA,configB,cglibBean,person,xmlMain,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,abstractAnimal,animal,animalDuplicate,inheritedAnimal,overrideAnimal,earth,earthFromFactory,inner,main,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.InstantiationTracingBeanPostProcessor2#0,com.keepthinker.example.spring.ioc.postprocessor.MyInstantiationAwareBeanPostProcessor#0,com.keepthinker.example.spring.ioc.postprocessor.ObscenityRemovingBeanFactoryPostProcessor#0,com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0,panda,compTiger,confTiger,a,b,myTiger,tiger,propertyConfigInDev]; root of factory hierarchy
 2022-02-12 17:38:49,743 [main] DEBUG [org.springframework.beans.factory.support.DisposableBeanAdapter]: Invoking destroy() on bean with name 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:49,743 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------destroy
 2022-02-12 17:38:49,743 [main] DEBUG [org.springframework.beans.factory.support.DisposableBeanAdapter]: Invoking destroy method 'xmlDestroyMethod' on bean with name 'com.keepthinker.example.spring.ioc.BeanLifeCircleObserver#0'
 2022-02-12 17:38:49,743 [main] INFO  [com.keepthinker.example.spring.ioc.BeanLifeCircleObserver]: -------xmlDestroyMethod
 */
public class BeanLifeCircleObserver implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, BeanPostProcessor, DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(BeanLifeCircleObserver.class);

    /**
     * 如果这个 Bean 已经实现了 BeanNameAware 接口，会调用它实现的 setBeanName(String)
     * 方法，此处传递的就是 Spring 配置文件中 Bean 的 id 值
     */
    @Override
    public void setBeanName(String name) {
        logger.info("-------setBeanName|{}", name);
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
     *如果这个 Bean 已经实现了 ApplicationContextAware 接口，会调用
     * setApplicationContext(ApplicationContext)方法，传入 Spring 上下文（同样这个方式也
     * 可以实现步骤 4 的内容，但比 4 更好，因为 ApplicationContext 是 BeanFactory 的子接
     * 口，有更多的实现方法）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("-------setApplicationContext|{}", applicationContext);
    }

    /**
     * 如果这个 Bean 关联了 BeanPostProcessor 接口，将会调用
     * postProcessBeforeInitialization(Object obj, String s)方法， BeanPostProcessor 经常被用
     * 作是 Bean 内容的更改，并且由于这个是在 Bean 初始化结束时调用那个的方法，也可以被应
     * 用于内存或缓存技术。
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("-------postProcessBeforeInitialization|bean:{}|beanName:{}", bean, beanName);
        return bean;
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

    /*
    init-method
    如果 Bean 在 Spring 配置文件中配置了 init-method 属性会自动调用其配置的初始化方法。
    */

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("-------postProcessAfterInitialization|bean:{}|beanName:{}", bean, beanName);
        return bean;
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
