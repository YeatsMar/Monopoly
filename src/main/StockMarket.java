package main;

import GUI.StockFrame;
import util.Calculation;
import util.Date;
import util.IO;


/**
 * Created by mayezhou on 16/4/25.
 */
public class StockMarket {
    public Stock[] stocks;
    private int num;

    public StockMarket(int n) {
        this.num = n;
        stocks = new Stock[n];
        for (int i = 0; i < n; i++) {
            Stock s = new Stock("股票"+i, Math.random()*20+1);
            s.setIndex(i);
            stocks[i] = s;
        }
        stocks[0].setName("壳牌石油");
        stocks[1].setName("诺贝尔");
        stocks[2].setName("宝丽金");
        stocks[3].setName("联合利华");
        stocks[4].setName("亨氏");
        stocks[5].setName("哈雷戴维森");
        stocks[6].setName("IBM公司");
        stocks[7].setName("瞻博网络");
        stocks[8].setName("摩根大通");
        stocks[9].setName("美赞臣");
    }

    public int getNum() {
        return num;
    }

    public void update() {//invoked by new round
        for (Stock s : stocks) {
            double rate = (Math.random()*20-10) / 100.0;//-10%~+10%
            rate = Calculation.roundUpDouble(rate);
            s.setRate(rate);
            s.saveOldPrice(s.getPrice());
            s.setPrice(s.getPrice()*(1+rate));
        }
    }

    public void enter(Date date, Player player) {
        if (!date.isWeekday()) {
            IO.print("双休日股市休市_(:зゝ∠)_");
        } else {
            StockFrame stockFrame = new StockFrame(player);
        }
    }

    public void buy(Player player, int x) {
        if (x < 0 || x >= num) {
            IO.warning("不存在改种股票,请输入正确的序号!");
            return;
        }
        int n = IO.getInt("请输入要购买的数量:");
        if (n < 0) {
            IO.warning("购买股数不可为负!");
            return;
        }
        double cost = n * stocks[x].getPrice();
        if (cost > player.getDeposit()) {
            IO.print("存款余额不足,将扣除现金");
            if (cost-player.getDeposit() > player.getCash()) {
                IO.warning("现金余额不足,本次购买操作失败");
                player.printProperty();
            } else {
                IO.print("成功买入股票"+x+" "+n+"股");
                player.setDeposit(0);
                player.setCash(player.getCash() - (cost-player.getDeposit()));
                player.addStockAmount(x, n);
                player.printProperty();
            }
        } else {
            IO.print("成功买入股票"+x+" "+n+"股");
            player.setDeposit(player.getDeposit() - cost);
            player.addStockAmount(x, n);
            player.printProperty();
        }
    }

    public void sell(Player player, int x) {
        if (x < 0 || x >= num) {
            IO.warning("不存在改种股票,请输入正确的序号!");
            return;
        }
        int n = IO.getInt("请输入要卖出的数量:");
        if (n < 0) {
            IO.warning("股数不可为负!");
            return;
        }
        if (player.getStockAmount(x) < n) {
            IO.warning("所持股票数量不足,本次操作失败");
        } else {
            IO.print("成功卖出股票"+x+" "+n+"股");
            player.addStockAmount(x, -n);
            player.setDeposit(player.getDeposit()+n*stocks[x].getPrice());
            player.printProperty();
        }
    }
}
