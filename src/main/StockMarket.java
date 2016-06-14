package main;

import util.Calculation;
import util.Date;
import util.IO;


/**
 * Created by mayezhou on 16/4/25.
 */
public class StockMarket {
    private Stock[] stocks;
    private int num;

    public StockMarket(int n) {
        this.num = n;
        stocks = new Stock[n];
        for (int i = 0; i < n; i++) {
            Stock s = new Stock("股票"+i, Math.random()*20+1);
            s.setIndex(i);
            stocks[i] = s;
        }
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
            for (int i = 0; i < 10; i++) {
                IO.print(stocks[i].getInfo()+"持有数:"+player.getStockAmout()[i]);
            }
            boolean ct = true;
            do {
                // TODO: 16/6/13 bug
                String input = IO.getString("输入b x n表示买入序号为x的股票n股, s x n 表示卖出序号为x的股票n股, q离开股市");
                IO.print("仅用一个空格隔开, x和n均为数字");
                String[] inputs = input.split(" ");
                if (inputs.length == 3
                        && (inputs[0].equals("b") || inputs[0].equals("s"))
                        && IO.isNumeric(inputs[1])
                        && IO.isNumeric(inputs[2])) {
                    int x = Integer.parseInt(inputs[1]);
                    int n = Integer.parseInt(inputs[2]);
                    if (inputs[0].equals("b")) {
                        buy(player, x, n);
                    } else {
                        sell(player, x, n);
                    }
                } else if (inputs.length == 1 && inputs[0].equals("q")){
                    ct = false;
                } else {
                    IO.warning();
                }
            } while (ct);
        }
    }

    private void buy(Player player, int x, int n) {
        if (x < 0 || x >= num) {
            IO.print("不存在改种股票,请输入正确的序号!");
            return;
        }
        if (n < 0) {
            IO.print("购买股数不可为负!");
            IO.warning();
            return;
        }
        double cost = n * stocks[x].getPrice();
        if (cost > player.getDeposit()) {
            IO.print("存款余额不足,将扣除现金");
            if (cost-player.getDeposit() > player.getCash()) {
                IO.print("现金余额不足,本次购买操作失败");
                player.printProperty();
            } else {
                IO.print("成功买入股票"+x+" "+n+"股");
                player.setDeposit(0);
                player.setCash(player.getCash() - (cost-player.getDeposit()));
                player.getStockAmout()[x] += n;
                player.printProperty();
            }
        } else {
            IO.print("成功买入股票"+x+" "+n+"股");
            player.setDeposit(player.getDeposit() - cost);
            player.getStockAmout()[x] += n;
            player.printProperty();
        }
    }

    private void sell(Player player, int x, int n) {
        if (x < 0 || x >= num) {
            IO.print("不存在改种股票,请输入正确的序号!");
            return;
        }
        if (n < 0) {
            IO.print("股数不可为负!");
            IO.warning();
            return;
        }
        if (player.getStockAmout()[x] < n) {
            IO.print("所持股票数量不足,本次操作失败");
        } else {
            IO.print("成功卖出股票"+x+" "+n+"股");
            player.getStockAmout()[x]  -= n;
            player.setDeposit(player.getDeposit()+n*stocks[x].getPrice());
            player.printProperty();
        }
    }
}
