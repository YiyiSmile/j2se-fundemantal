package indi.tom.test.aspectj;

public aspect TxAspect {
    void around():call(void indi.tom.test.aspectj.Hello.sayHello()){
        System.out.println("开始事务...");
        proceed();
        System.out.println("事务结束...");
    }
}
