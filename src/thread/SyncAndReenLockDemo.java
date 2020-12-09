package thread;

/**
 * @author Duanjianhui
 * @create 2020-12-09 11:35
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目:多线程之间按顺序调用，实现A->B->C三个线程启动， 要求如下: .
 * AA打印15次，BB打印10次, CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次, CC打印15次
 * 当来10轮
 */

class ShareData1 {
    private Lock lock = new ReentrantLock();
    private int number = 1;// A线程：1 B：2 C：3
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print(int num) {
        if (num == 1) {
            lock.lock();
            try {
                while (number != 1) {
                    c1.await();
                }
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + number);
                }
                number=2;
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else if (num == 2) {
            lock.lock();
            try {
                while (number != 2) {
                    c2.await();
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + number);
                }
                number=3;
                c3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else if (num == 3) {
            lock.lock();
            try {
                while (number != 3) {
                    c3.await();
                }
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + number);
                }
                number=1;
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("没有此线程");
            return;
        }
    }
    //判断

    //干活

    //通知
}

class SyncAndReenLockDemo {
    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareData1.print(1);
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareData1.print(2);
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareData1.print(3);
            }
        },"C").start();
    }
}


