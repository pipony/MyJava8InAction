package indi.cindy.Java8InAction.Part11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/*
复现11.3节各代码：给定商家列表和特定商品，提供“最佳价格查询器”
 */
public class Demo3 {
    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));
    //1.用流来实现
    public static List<String> findPrices(String product){
        return shops.stream().map(s -> String.format("the shop of %s 's price is %.2f"
                , s.getShopName(), s.getPrice(product)))
                .collect(toList());
    }
    //2.（并行流方式）用流来实现
    public static List<String> findPrices1(String product){
        return shops.parallelStream().map(s -> String.format("the shop of %s 's price is %.2f"
                , s.getShopName(), s.getPrice(product)))
                .collect(toList());
    }
    //3.用CompletableFuture来实现，有点复杂。。。
    public static List<String> findPrices2(String product){
        List<CompletableFuture<String>> future =
                shops.stream()
                        .map(s -> CompletableFuture.supplyAsync(
                                () -> s.getShopName() + "'s price is "+s.getPrice(product)))
                .collect(toList());
        return future.stream().map(CompletableFuture::join).collect(toList());
    }
    //代码清单11-12 为“最优价格查询器”应用定制的执行器
    static final Executor executor1 = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true); //设为守护线程
                    return t;
                }
            });
    //4.利用定制的Executor来优化方式3
    public static List<String> findPrices3(String product){
        List<CompletableFuture<String>> future =
                shops.stream()
                        .map(s -> CompletableFuture.supplyAsync(
                                () -> s.getShopName() + "'s price is "+s.getPrice(product), executor1))
                        .collect(toList());
        return future.stream().map(CompletableFuture::join).collect(toList());
    }

    //测试一下
    public static void main(String[] args) {
        //测试方式1
        long start = System.currentTimeMillis();
        System.out.println(findPrices("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start)/1000);
        //测试方式2
        long start1 = System.currentTimeMillis();
        System.out.println(findPrices1("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start1)/1000);
        //测试方式3
        long start2 = System.currentTimeMillis();
        System.out.println(findPrices2("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start2)/1000);
        //测试方式4
        long start3 = System.currentTimeMillis();
        System.out.println(findPrices3("myProduct"));
        System.out.println("pass time is "+ (System.currentTimeMillis() - start3)/1000);
    }

}
