package indi.cindy.Java8InAction.Part11;
/*
复现11.4.1节，解析Shop1.getPrice返回的字符串的三个内容
 */
public class Quote {
    private String shopName;
    private double price;
    private Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s){
        String[] strings = s.split(":");
        String shopName = strings[0];
        double price = Double.parseDouble(strings[1]);
        Discount.Code code = Discount.Code.valueOf(strings[2]);
        return new Quote(shopName, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
