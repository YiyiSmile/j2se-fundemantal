package indi.tom.test.InheritancTest;

/**
 * @Author totian
 * @Date 2019/11/6 19:49
 * @Version 1.0
 * @Description
 */
public class Test {

    public static void run(Animal animal){
        animal.run();
    }

    public static void main(String[] args) {
        run(new Dog());
    }
}
