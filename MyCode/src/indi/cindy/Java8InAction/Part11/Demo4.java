package indi.cindy.Java8InAction.Part11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
复现代码清单11-15/16/20：实现添加Discount服务的findPrices
 */
public class Demo4 {
    //注意，此时类型是Shop1
    private List<Shop1> shops = Arrays.asList(new Shop1("BestPrice"),
            new Shop1("LetsSaveBig"),
            new Shop1("MyFavoriteShop"),
            new Shop1("BuyItAll"));
    //1.以最简单的方式（流）实现使用 Discount 服务的 findPrices 方法
    public List<String> findPrices1(String product){
        //利用三次map
        return shops.stream().map(s -> s.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }
    private Executor executor1 = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true); //设为守护线程
                    return t;
                }
            });
    //2.使用 CompletableFuture 实现 findPrices 方法
    public List<String> findPrices2(String product){
        //涉及两次异步操作，因为涉及两次远程计算（更耗时）
        List<CompletableFuture<String>> futures = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPrice(product), executor1))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor1)))
                .collect(toList());
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
    //3.代码清单11-20 重构 findPrices 方法返回一个由 Future 构成的流
    public Stream<CompletableFuture<String>> findPriceStream(String product){
        return shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPrice(product), executor1))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor1)));
    }
    //4.利用3中得到的Future流，输出流的结果
    public void printPrices(Stream<CompletableFuture<String>> futureStream){
        CompletableFuture[] futures = findPriceStream("myProduct")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
    }

    //测试一下
    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();
        //测试方式1
        long start = System.currentTimeMillis();
        //System.out.println(demo4.findPrices1("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start)/1000);
        //测试方式2
        long start2 = System.currentTimeMillis();
        //System.out.println(demo4.findPrices2("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start2)/1000);
        //测试方式4
        long start4 = System.currentTimeMillis();
        demo4.printPrices(demo4.findPriceStream("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start4)/1000);
    }

}
