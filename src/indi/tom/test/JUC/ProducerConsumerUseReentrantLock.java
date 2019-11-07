package indi.tom.test.JUC;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author totian
 * @Date 2019/11/7 9:01
 * @Version 1.0
 * @Description A simple consumer/producer exercise with ReentrantLock, the exmaple didn't consider the size might be < 0, didn't set a
 * maximum value either. Will solve this problem in next exercise.
 */
public class ProducerConsumerUseReentrantLock {
    public static void main(String[] args) {
        Depot depot = new Depot();
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
        private ReentrantLock lock;

        public Depot() {
            this.size = 0;
            this.lock = new ReentrantLock();
        }

        public void produce(int count){
            lock.lock();
            size += count;
            System.out.printf("%s produce %d ----> size = %d \n", Thread.currentThread().getName(), count, size);
            lock.unlock();
        }

        public void consume(int count){
            lock.lock();
            size -= count;
            System.out.printf("%s consume %d ----> size = %d \n", Thread.currentThread().getName(), count, size);
            lock.unlock();
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
