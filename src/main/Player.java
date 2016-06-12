package main;

import GUI.GoListener;
import dots.Dot;
import dots.Estate;
import props.Prop;
import util.Calculation;
import util.IO;
import util.Icon;
import util.PropFactory;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

/**
 * Created by mayezhou on 16/4/7.
 */
public class Player {
    public Game game;
    public ImageIcon icon;
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
    private int[] stockAmout;//only to record the number of each type
    private boolean bankrupt;

    public Player(String name, int cash, int deposit, int playerID, int direction, int ticketPoint, boolean healthy, Game game) {
        this.game = game;
        this.name = name;
        this.cash = cash;
        this.deposit = deposit;
        this.playerID = playerID;
        setIcon();
        this.direction = direction;
        this.ticketPoint = ticketPoint;
        this.healthy = healthy;
        location = 0;
        bankrupt = false;
        for (int i = 0; i < card.length; i++) {
            card[i] = 1;
        }
        stockAmout = new int[game.stockMarket.getNum()];
        for (int i = 0; i < stockAmout.length; i++) {
            stockAmout[i] = 0;
        }
    }

    public Player(String name, int cash, int deposit, int playerID, int direction, int ticketPoint, Game game) {
        this.game = game;
        this.name = name;
        this.cash = cash;
        this.deposit = deposit;
        this.playerID = playerID;
        setIcon();
        this.direction = direction;
        this.ticketPoint = ticketPoint;
        this.healthy = true;
        location = 0;
        bankrupt = false;
        for (int i = 0; i < card.length; i++) {
            card[i] = 1;
        }
        stockAmout = new int[game.stockMarket.getNum()];
        for (int i = 0; i < stockAmout.length; i++) {
            stockAmout[i] = 0;
        }
    }

    private void setIcon() {
        switch (playerID) {
            case 0:
                icon = Icon.player0Icon;
                break;
            case 1:
                icon = Icon.player1Icon;
                break;
            case  2:
                icon = Icon.player2Icon;
                break;
            case  3:
                icon = Icon.player3Icon;
                break;
        }
    }

    public Boolean useProp(int i) {
        try {
            if (card[i] > 0) {
                Prop prop = PropFactory.createProp(i);
                boolean result = prop.function(this);//may be NULL
                card[i]--;
                return result;
            } else {
                IO.warning(Prop.names[i]+"数量不足,操作失败");
                return true;
            }
        } catch (NullPointerException e) {
            IO.print(name+"中途放弃使用道具_(:зゝ∠)_");
            return true;
        }
    }

    public String printProperty() {
        String s = "";
        if (isBankrupt()) {
            s += name + "已破产,资产全部充公_(:зゝ∠)_";
            return s;
        }
        s += name + "\t现金:" + cash + "\t存款:" + deposit + "\t房产价值："
                + getEstateTotal()+"\t资产总额"+getTotalProperty();
        s += "\n股票持有:\n";
        for (int i = 0; i < stockAmout.length; i++) {
            if (stockAmout[i] != 0) {
                s += "股票" + i + "\t持有:" + stockAmout[i] + "\n";
            }
        }
        return s;
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
        game.notifyObserver();
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
        game.notifyObserver();
    }

    public void addCash(double c) {
        cash += c;
        cash = Calculation.roundUpDouble(cash);
        game.notifyObserver();
    }

    public void addDeposit(double c) {
        deposit += c;
        deposit = Calculation.roundUpDouble(deposit);
        game.notifyObserver();
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        deposit = Calculation.roundUpDouble(deposit);
        this.deposit = deposit;
        game.notifyObserver();
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
        game.notifyObserver();
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
        game.notifyObserver();
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }

    public void addCard(int index, int n) {
        card[index] += n;
        game.notifyObserver();
    }

    public int getCard(int index) {
        return card[index];
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int i) {
        i = Calculation.calculateLocation(i, 0);//insure
        Dot oldDot = game.map.getDots().get(this.location);
        oldDot.removePlayer(this);
        Dot newDot = game.map.getDots().get(i);
        newDot.addPlayer(this);
        this.location = i;
    }

    public void go(int go) {
        IO.print(name+"前进"+go+"步");
        Timer timer = new Timer(150, new GoListener(go, this));
        timer.start();
    }

    public boolean isBankrupt() {
        if (getTotalProperty() < 0) {
            bankrupt = true;
        }
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
        game.notifyObserver();
    }

    public int[] getStockAmout() {
        return stockAmout;
    }
}
