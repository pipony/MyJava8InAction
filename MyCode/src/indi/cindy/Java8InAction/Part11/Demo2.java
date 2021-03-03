package indi.cindy.Java8InAction.Part11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
复现11.2.1节，代码清单11-5/6/7 使用异步API满足用户查询商品价格的需求
因为使用异步技术，所以用户可以在无需等待漫长的价格查询过程完成时可转去执行其他任务
 */
public class Demo2 {
    public static Future<Double> getPriceAsync1(String product){
        //1.新建CompletableFuture类
        CompletableFuture<Double> future = new CompletableFuture<>();
        //2.新开一个线程，执行future.complete()
        new Thread(() -> {
            try{
                future.complete(new Shop().getPrice(product));
            } catch (Exception ex){
                future.completeExceptionally(ex);  //当complete内部方法出现异常时，可以报出具体错误
            }
        }).start();
        //3.不等Shop.getPrice()执行完，直接返回future
        return future;
    }
    public static Future<Double> getPriceAsync(String product){
        //利用CompleteFuture.supplyAsync来简化getPriceAsync1的写法
        return CompletableFuture.supplyAsync(() -> new Shop().getPrice(product));
    }
    //测试一下
    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime(); //纳秒时间
        //获得Future对象
        Future<Double> future = getPriceAsync("Product1");
        //输出获得Future对象的耗时
        System.out.println("the time of getting future: "+(System.nanoTime() - start) / 1_000_000+"ms");
        //获取商品价格（当然得等Future对象中的getPrice执行完毕）
        try {
            double price = future.get();
            System.out.printf("the price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //输出真正获得Future对象结果的耗时
        System.out.println("the time of getting price: "+(System.nanoTime() - start) / 1_000_000+"ms");
    }
}
