package indi.cindy.Java8InAction.Part11;
/*
复现11.4节
 */
import java.util.Random;

public class Shop1 {
    //相较于Shop，在计算price时多考虑了Discount的因素
    private String shopName;
    public Shop1(){

    }
    public Shop1(String shopName){
        this.shopName = shopName;
    }
    //手动延迟1s
    private static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //手动随机延迟0.5秒至2.5秒
    private static void randomDelay(){
        Random random = new Random();
        try {
            Thread.sleep(500 + random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double calculatePrice(String product){
        return new Random().nextDouble() + product.charAt(0) + product.charAt(1);
    }
    public String getPrice(String product){
        delay();
        double price =  calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", getShopName(), price, code);
    }
    public String getPriceRandomDelay(String product){
        randomDelay();
        double price =  calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", getShopName(), price, code);
    }

    public String getShopName() {
        return shopName;
    }
}
