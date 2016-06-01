package main;

import dots.BankDot;
import dots.Dot;
import dots.Estate;
import props.Prop;
import util.Calculation;
import util.InputHandler;
import util.PropFactory;

import java.beans.Beans;
import java.util.ArrayList;

/**
 * Created by mayezhou on 16/4/7.
 */
public class Player {
    private String symbol;
    private String name;
    private double cash;
    private double deposit;
    private int playerID;
    private int location;
    private int direction;
    private int ticketPoint;
    private boolean healthy;
    private int[] card = new int[7];//only to record the number of each type
    private ArrayList<Estate> estates = new ArrayList<>();
    private int[] stockAmout = new int[Game.getStockMarket().getNum()];//only to record the number of each type
    private boolean bankrupt;

    public Player(String name, int cash, int deposit, int playerID, int direction, int ticketPoint, boolean healthy) {
        this.name = name;
        this.cash = cash;
        this.deposit = deposit;
        this.playerID = playerID;
        this.direction = direction;
        this.ticketPoint = ticketPoint;
        this.healthy = healthy;
        location = 0;
        ((Dot) Game.getMap().getDots().get(location)).addPlayer(this);
        bankrupt = false;
        for (int i = 0; i < card.length; i++) {
            card[i] = 1;
        }
        for (int i = 0; i < stockAmout.length; i++) {
            stockAmout[i] = 0;
        }
    }

    public Player(String name, int cash, int deposit, int playerID, int direction, int ticketPoint) {
        this.name = name;
        this.cash = cash;
        this.deposit = deposit;
        this.playerID = playerID;
        this.direction = direction;
        this.ticketPoint = ticketPoint;
        this.healthy = true;
        location = 0;
        ((Dot) Game.getMap().getDots().get(location)).addPlayer(this);
        bankrupt = false;
        for (int i = 0; i < card.length; i++) {
            card[i] = 1;
        }
        for (int i = 0; i < stockAmout.length; i++) {
            stockAmout[i] = 0;
        }
    }

    public Boolean useProp() {
        System.out.println("您现在拥有的道具数量如下:\n");
        for (int i = 0; i < card.length; i++) {
            System.out.print(i + ". " + Prop.names[i] + card[i] + "张     ");
        }
        System.out.println();
        boolean ct = true;
        do {
            String input = InputHandler.getString("请输入您想要的卡片编号(输入h获得帮助,输入x返回上一层)");
            switch (input) {
                case "h":
                    System.out.println("指示已经写明,道具卡顾名思义_(:зゝ∠)_");
                    break;
                case "x":
                    ct = false;
                    break;
                default:
                    try {
                        int inputValue = Integer.parseInt(input);
                        if (inputValue < 0 || inputValue >= card.length) {
                            System.out.println("卡片编号为0-" + card.length + "请重新输入");
                        } else {
                            if (card[inputValue] > 0) {
                                Prop prop = PropFactory.createProp(inputValue);
                                card[inputValue]--;
                                return prop.function(this);
                            } else {
                                System.out.println(Prop.names[inputValue]+"数量不足,操作失败");
                            }
                        }
                    } catch (Exception e) {
                        InputHandler.warning();
                    }
            }
        } while (ct);
        return true;
    }

    public void printProperty() {
        if (isBankrupt()) {
            System.out.println(name + "已破产,资产全部充公_(:зゝ∠)_");
            return;
        }
        System.out.println(name + "\t现金:" + cash + "\t存款:" + deposit + "\t房产价值："
                + getEstateTotal()+"\t资产总额"+getTotalProperty());
        System.out.println("股票持有:");
        for (int i = 0; i < stockAmout.length; i++) {
            if (stockAmout[i] != 0) {
                System.out.println("股票" + i + "\t持有:" + stockAmout[i]);
            }
        }
    }

    private int getEstateTotal() {
        int sum = 0;
        for (Estate e : estates) {
            sum += e.getPrice();
        }
        return sum;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public double getTotalProperty() {
        return cash + deposit + getEstateTotal();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        cash = Calculation.roundUpDouble(cash);
        this.cash = cash;
    }

    public void addCash(double c) {
        cash += c;
        cash = Calculation.roundUpDouble(cash);
    }

    public void addDeposit(double c) {
        deposit += c;
        deposit = Calculation.roundUpDouble(deposit);
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        deposit = Calculation.roundUpDouble(deposit);
        this.deposit = deposit;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getDirectionString() {
        if (direction == 1) {
            return "顺时针";
        } else {
            return "逆时针";
        }
    }

    public int getTicketPoint() {
        return ticketPoint;
    }

    public void setTicketPoint(int ticketPoint) {
        this.ticketPoint = ticketPoint;
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }

    public int[] getCard() {
        return card;
    }

    public void setCard(int[] card) {
        this.card = card;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int i) {
        i = Calculation.calculateLocation(i, 0);//insure
        Dot oldDot = (Dot) Game.getMap().getDots().get(this.location);
        oldDot.removePlayer(this);
        Dot newDot = (Dot) Game.getMap().getDots().get(i);
        newDot.addPlayer(this);
        System.out.println("到达"+newDot.getInfo());
        this.location = i;
        newDot.event(this);
    }

    public void go() {
        int go = 1 + (int) (Math.random() * 6);
        go(go);
    }

    public void go(int go) {
        System.out.println("前进"+go+"步");
        int l = location;
        while (go > 0) {
            l = Calculation.calculateLocation(l, direction);
            Dot dot = Game.getMap().getDot(l);
            if (dot.isBlocked()) {
                System.out.println("遇到路障!");
                setLocation(l);//invoke event here
                return;
            } else if (dot instanceof BankDot && go != 1) {
                System.out.println("途经银行可顺道办理业务~");
                ((BankDot) dot).event(this);
            }
            go--;
        }
        setLocation(l);
    }

    public boolean isBankrupt() {
        if (getTotalProperty() < 0) {
            bankrupt = true;
        }
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public int[] getStockAmout() {
        return stockAmout;
    }
}
