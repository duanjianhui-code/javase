package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Duanjianhui
 * @create 2020-12-08 23:25
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1、判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3、通知
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //1、判断
            while (number == 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3、通知
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 初始值为0的变量，两个线程交替操作，一个+1，一个-1，执行五轮
 * 1 线程  操作  资源类
 * 2 判断  干活  通知
 * 3 防止虚假唤醒机制
 */

class ProdConsumer_TraditonDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
