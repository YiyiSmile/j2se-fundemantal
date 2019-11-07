package indi.tom.test.AOPtest;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author totian
 * @Date 2019/11/6 18:02
 * @Version 1.0
 * @Description
 */

@Component
public class PointCuts {

    @Pointcut("execution(* indi.tom.test.AOPtest.PointCuts.test01())")
    public void test01(){
        System.out.println("point cut test01()");
    }
}
