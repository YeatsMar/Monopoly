package main;


import util.Calculation;

/**
 * Created by mayezhou on 16/4/25.
 */
public class Stock {
    private String name;
    private double originalPrice;
    public double[] prices;
    private double rate;
    private int index;
    private double price;

    public Stock(String name, double originalPrice) {
        this.name = name;
        this.originalPrice = originalPrice;
        rate = 1;
        price = originalPrice * rate;
        prices = new double[5];
        prices[4] = getPrice();
    }

    public void saveOldPrice(double oldPrice) {
        for (int i = 0; i < 4; i++) {
            prices[i] = prices[i+1];
        }
        prices[4] = oldPrice;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getInfo() {
        return "序号:"+index+"\t股票名:"+name+"\t单价:"+getPrice()+"\t涨跌幅:"+rate+"\t\t\t";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPrice() {
        return Calculation.roundUpDouble(price);
    }

    public void setPrice(double price) {
        price = Calculation.roundUpDouble(price);
        this.price = price;
    }
}
