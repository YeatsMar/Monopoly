package main;

import GUI.MyFrame;
import GUI.Observer;
import dots.Dot;
import util.Calculation;
import util.Date;
import util.IO;
import util.MapFactory;


/**
 * Created by mayezhou on 16/4/8.
 */
public class Game {
    public static Player[] players;
    public static Map map;
    public static Date date;
    public static StockMarket stockMarket;
    public static Player curPlayer;
    public static Observer observer;
    private int playerID;
    private int playern;

    public Game(int number) {
        this.playern = number;
        stockMarket = new StockMarket(10);
        map = MapFactory.build();
        map.setStreet(this);
        date = new Date(2014, 1, 1, 1, this);
        players = new Player[number];
        playerID = 0;
        for (int i = 0; i < number; i++) {
            players[i] = new Player("玩家" + (char) ('A' + i), 10000, 10000, i, 1, 10, this);
            players[i].setSymbol((char) ('A' + i) + " ");
        }
        curPlayer = players[playerID];
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getPlayern() {
        return playern;
    }

    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver() {
        observer.refresh();
    }

    public String showAllProperty() {
        String s = "";
        for (Player p : players) {
            s += p.printProperty() + "\n";
        }
        IO.warning(s);
        return s;
    }

    public String showWarning(Player player) {
        String warning = null;
        int l = player.getLocation();
        boolean noBlock = true;
        for (int i = 0; i < 10; i++) {
            l += player.getDirection();
            if (map.getDot(Calculation.calculateLocation(l)).isBlocked()) {
                noBlock = false;
                int step = Math.abs(l - player.getLocation());
                warning = "前方" + step + "步有路障";
                break;
            }
        }
        if (noBlock) {
            warning = "前方十步内没有路障";
        }
        return warning;
    }

    public void showCertainDotInfo(Player player) {
        try {
            int num = IO.getInt("请输入您想查看的点与您当前位置相差的步数（后方用负数）");
            int location = Calculation.calculateLocation(player.getLocation(), num);
            Dot dot = map.getDot(location);
            dot.printInfo();
        } catch (NullPointerException e) {
            //do nothing
        } catch (Exception e) {
            IO.warning();
        }
    }

    public boolean tbc() {
        int bankrupt = 0;
        for (Player p :
                players) {
            if (p.isBankrupt()) {
                bankrupt++;
            }
        }
        if (bankrupt > 2) {
            return false;
        }
        return true;
    }

    public void start() {
        for (Player player :
                players) {
            (map.getDots().get(player.getLocation())).addPlayer(player);
        }
        date.print();
        IO.print("现在是" + curPlayer.getName() + "的操作时间，您的前进方向是"
                + curPlayer.getDirectionString() + "。");
    }

    public void next() {
        ++playerID;
        if (playerID >= playern) {
            newRound();
        } else {
            curPlayer = players[playerID];
        }
        if (curPlayer.isBankrupt()) {
            next();
        }
        notifyObserver();
        IO.print("\n\n\n");
        date.print();
        IO.print("现在是" + curPlayer.getName() + "的操作时间，您的前进方向是"
                + curPlayer.getDirectionString() + "。");
    }

    private void newRound() {
        if (tbc()) {
            playerID = 0;
            curPlayer = players[playerID];
            date.addDate();
            if (date.isWeekday()) {
                stockMarket.update();
            }
        } else {
            MyFrame frame = (MyFrame) observer;
            over(frame);
            frame.cardLayout.next(frame.mainPanel);//endPanel
        }
    }

    private void over(MyFrame frame) {
        int i;
        for (i = 0; i < players.length; i++) {
            if (!players[i].isBankrupt()) {
                break;
            }
        }
        if (i < players.length) {
            frame.endPanel.setWinner(players[i].getName() + "最终获胜!");
        } else {
            frame.endPanel.setWinner("全军覆没Orz");
        }
    }
}
