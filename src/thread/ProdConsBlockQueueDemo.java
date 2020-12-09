package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Duanjianhui
 * @create 2020-12-09 12:02
 *
 * volatile/CAS/AtomicInteger/BlockingQueue/线程交互
 */
class MySouecer{
    //默认开启，进行生产与消费
    private volatile boolean FLAG=true;
    private AtomicInteger atomicInteger=new AtomicInteger();
    private BlockingQueue<Integer> blockingDeque=null;

    public MySouecer(BlockingQueue<Integer> blockingDeque) {
        System.out.println(blockingDeque.getClass().getName());
        this.blockingDeque = blockingDeque;
    }

    //生产
    public void myProd() throws Exception{
        int data=0;
        boolean result;
        while (FLAG){
            data=atomicInteger.incrementAndGet();
            result=blockingDeque.offer(data,2L, SECONDS);
            if(result){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停，生产线程结束");
    }
    //消费
    public void myConsumer() throws Exception{
        Integer result;
        while (FLAG){
            result=blockingDeque.poll(2, SECONDS);
            if(result==null){
                FLAG=false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒钟没有消费，退出消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "成功");
        }
    }

    //停止
    public void stop() throws Exception{
        this.FLAG=false;
    }

}
class ProdConsBlockQueueDemo {
    public static void main(String[] args) {
        MySouecer mySouecer = new MySouecer(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            System.out.println("生产线程启动");
            try {
                mySouecer.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产线程").start();

        new Thread(()->{
            System.out.println("消费线程启动");
            try {
                mySouecer.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费线程").start();

        try { SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("5秒钟后，叫停");
        try {
            mySouecer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
