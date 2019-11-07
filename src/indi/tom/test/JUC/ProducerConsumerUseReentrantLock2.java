package indi.tom.test.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author totian
 * @Date 2019/11/7 9:01
 * @Version 2.0
 * @Description In this version, there is still a issue: while consumer is waiting due to a too larger count to consume, meanwhile,
 * another produce thread is waiting also due to a too large count to produce, it may cause the entire app to suspend.
 */
public class ProducerConsumerUseReentrantLock2 {
    public static void main(String[] args) {
        Depot depot = new Depot(400);
        Consumer consumer = new Consumer(depot);
        Producer producer = new Producer(depot);

        producer.produce(100);
        consumer.consume(20);
        producer.produce(200);
        consumer.consume(200);
        producer.produce(300);
        consumer.consume(250);
        producer.produce(150);
        consumer.consume(100);
    }

    //Depot
    static class  Depot{
        private int size;
        private int capacity;

        private ReentrantLock lock;
        private Condition fullCondition;
        private Condition emptyCondition;

        public Depot(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.lock = new ReentrantLock();
            this.fullCondition = lock.newCondition();
            this.emptyCondition = lock.newCondition();
        }

        public void produce(int count) {
            if (count <= 0) throw new IllegalArgumentException();
            lock.lock();
            try {
                while (size + count > capacity) {
                    System.out.println("It's too much to produce!!!");
                    fullCondition.await();
                }
                size += count;
                System.out.printf("%s produce %d ----> size = %d \n", Thread.currentThread().getName(), count, size);
                emptyCondition.signalAll();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }



        }

        public void consume(int count) {
            try {
                if (count <= 0) throw new IllegalArgumentException();

            lock.lock();
            while (size < count) {
                System.out.println("wowo, no enough stock!");
                emptyCondition.await();
            }
            size -= count;
            System.out.printf("%s consume %d ----> size = %d \n", Thread.currentThread().getName(), count, size);
            fullCondition.signalAll();
            lock.unlock();
            } catch(Exception exception){
                     exception.printStackTrace();
        }
        }
    }
    //Producer
    static class Producer{
        private Depot depot;
        public Producer(Depot depot){

            this.depot = depot;
        }

        public void produce(int count){
            new Thread(){
                @Override
                public void run() {
                    depot.produce(count);
                }
            }.start();
        }
    }

    //Consumer
    static class Consumer{
        private Depot depot;

        public Consumer(Depot depot){
            this.depot = depot;
        }

        public void consume(int count){


            class ConsumeThread extends Thread{
                @Override
                public void run() {

                    depot.consume(count);
                }
            }


            new ConsumeThread().start();
        }



    }

}
