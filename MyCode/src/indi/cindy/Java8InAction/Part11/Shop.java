package indi.cindy.Java8InAction.Part11;


import java.util.Random;

/*
配合Demo2使用
 */
public class Shop {
    private String shopName;
    public Shop(){

    }
    public Shop(String shopName){
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
    private double calculatePrice(String product){
        return new Random().nextDouble() + product.charAt(0) + product.charAt(1);
    }
    public double getPrice(String product){
        delay();
        return calculatePrice(product);
    }

    public String getShopName() {
        return shopName;
    }
}
