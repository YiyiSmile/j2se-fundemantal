package indi.tom.test.AOPtest;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.Pointcuts;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author totian
 * @Date 2019/11/6 18:11
 * @Version 1.0
 * @Description
 */
public class AopTest01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
  /*      String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        System.out.println("----------------------------------------");*/
        PointCuts bean = ac.getBean(PointCuts.class);
        bean.test01();
    }
}
