package indi.tom.test.JUC;

/**
 * @Author totian
 * @Date 2019/11/6 22:17
 * @Version 1.0
 * @Description
 */
public class ProduceConsume {
    public static void main(String[] args) {
        SynchContainer container = new SynchContainer();
        new Thread(new ProduceWorker(container)).start();
        new Thread(new ConsumeWorker(container)).start();
    }

    static class ProduceWorker implements Runnable{
        SynchContainer container;
        public ProduceWorker(SynchContainer container){
            this.container = container;
        }
        @Override
        public void run() {
            for (int i = 0; i <100 ; i++) {
                try {
                    container.push(new SteamedBun(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者生产了" + i + "个馒头");
            }
        }
    }

    static class ConsumeWorker implements Runnable{
        SynchContainer container;
        public ConsumeWorker(SynchContainer container){
            this.container = container;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    container.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费了" + i + "个馒头");
            }
        }
    }
}

class SynchContainer{
    private SteamedBun[] container = new SteamedBun[10];
    private int count = 0;

    public synchronized  void push(SteamedBun bun) throws InterruptedException {
        if (count == container.length) {
            System.out.println("开始wait");
            this.wait();
            System.out.println("结束wait");
        }
        container[count] = bun;
        count++;
        this.notifyAll();

    }
    public synchronized SteamedBun pop() throws InterruptedException {
        if(count == 0){
            this.wait();
        }
        count--;
        SteamedBun bun = container[count];
        this.notifyAll();
        return bun;
    }

}

class SteamedBun{
    private int id;

    public SteamedBun(int id) {
        this.id = id;
    }
}
