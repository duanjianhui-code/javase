import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Duanjianhui
 * @create 2020-11-24 20:22
 */
public class MyTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask =new FutureTask<>(new MyCall());
        Thread thread = new Thread(futureTask,"AAA");


        thread.start();

        System.out.println("main方法 >>>");

        Integer integer = futureTask.get();
        System.out.println("futureTask >>>"+integer);
    }

    static class MyCall implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {

            TimeUnit.SECONDS.sleep(4);
            System.out.println("call方法执行了...");


            return 12;
        }
    }
}
