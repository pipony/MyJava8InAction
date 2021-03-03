package indi.cindy.Java8InAction.Part11;

import java.util.concurrent.*;

/*
复现11.1节，代码清单11-1 使用 Future 以异步的方式执行一个耗时的操作
 */
public class Demo1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });
        doSomethingElse();
        try {
            //获取future的结果，并设定超过1s则退出
            Double res = future.get(1, TimeUnit.SECONDS);
            System.out.println("the result of future is "+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("the end!!");
    }
    public static Double doSomeLongComputation(){
        System.out.println("start SomeLongComputation...");
        Long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 10000; i++){
            for (int j = 0; j < 10000; j++)
                sum += j;
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("end SomeLongComputation by "+(endTime - startTime)+" ms");
        return 0.1;
    }
    public static void doSomethingElse(){
        System.out.println("start doSomethingElse...");
        Long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++)
                sum += j;
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("end doSomethingElse by "+(endTime - startTime)+" ms");
    }
}
