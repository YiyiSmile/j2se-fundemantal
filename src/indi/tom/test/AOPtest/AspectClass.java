package indi.tom.test.AOPtest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author totian
 * @Date 2019/11/6 18:06
 * @Version 1.0
 * @Description
 */
@Component
@Aspect
public class AspectClass {

    @Before("indi.tom.test.AOPtest.PointCuts.test01()")
    public void beforeAdvisor(){
        System.out.println("Before Advisor");
    }
    @After("indi.tom.test.AOPtest.PointCuts.test01()")
    public void afterAdvisor(){
        System.out.println("After Advisor");
    }
}
