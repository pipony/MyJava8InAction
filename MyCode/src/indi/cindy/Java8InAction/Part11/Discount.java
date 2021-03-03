package indi.cindy.Java8InAction.Part11;
/*
11.4节，商品折扣类
 */
public class Discount {
    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private int percentage;
        Code(int percentage){
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName() +" price is "+
                apply(quote.getPrice(), quote.getCode());
    }

    public static double apply(double price, Discount.Code code){
        delay(); //模拟Discount服务响应的延迟
        return price * (100 - code.percentage) / 100;
    }

    //手动延迟1s
    private static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
