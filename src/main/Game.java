package main;

import dots.Dot;
import util.Calculation;
import util.Date;
import util.InputHandler;
import util.ReadFile;


/**
 * Created by mayezhou on 16/4/8.
 */
public class Game {
    private static Player[] players;
    private static Map map;
    private static Date date;
    private static StockMarket stockMarket;

    public Game(int number) {
        stockMarket = new StockMarket(10);
        map = ReadFile.read();
        map.setStreet();
        date = new Date();
        players = new Player[number];
        for (int i = 0; i < number; i++) {
            players[i] = new Player("玩家"+(char)('A'+i), 10000, 10000, i, 1, 10);
            players[i].setSymbol((char)('A'+i)+" ");
        }
    }

    private void round() {
        for (Player player : players) {
            if (player.isBankrupt()) {
                continue;
            }
            boolean continueInput = true;
            do {
                date.print();
                System.out.println("现在是" + player.getName() + "的操作时间，您的前进方向是"
                        + player.getDirectionString() + "。");
                int input = InputHandler.getInt("您现在可以执行如下操作：\n0.查看地图\n1.查看原始地图\n2.使用道具\n3.前方10步内示警\n4.查看前后指定步数的具体信息\n5.查看玩家的资产信息\n6.想看的都看了，心满意足地扔骰子\n7.不玩了！认输！\n8.进入股市\n\n\n请选择：");
                switch (input) {
                    case 0:
                        map.printCurrent();
                        break;
                    case 1:
                        map.printInitial();
                        break;
                    case 2:
                        continueInput = player.useProp();
                        break;
                    case 3:
                        showWarning(player);
                        break;
                    case 4:
                        showCertainDotInfo(player);
                        break;
                    case 5:
                        for (Player p : players) {
                            p.printProperty();
                        }
                        break;
                    case 6:
                        if (player.isHealthy()) {
                            player.go();
                        } else {
                            player.go(0);
                        }
                        continueInput = false;
                        break;
                    case 7:
                        player.setBankrupt(true);
                        System.out.println(player.getName()+"认输!");
                        continueInput = false;
                        break;
                    case 8:
                        stockMarket.enter(date, player);
                        break;
                    default:
                        InputHandler.warning();
                }
                System.out.println("\n\n\n");
            } while (continueInput);
        }
    }

    private void showWarning(Player player) {
        int startLocation = player.getLocation() + 1;
        int endLocation = player.getLocation() + 10;
        boolean noBlock = true;
        for (int location = startLocation; location <= endLocation; location++) {
            if (map.getDot(location).isBlocked()) {
                int step = location - startLocation + 1;
                System.out.println("前方" + step + "步有路障");
                noBlock = false;
            }
        }
        if (noBlock) {
            System.out.println("前方十步内没有路障");
        }
    }

    private void showCertainDotInfo(Player player) {
        boolean b = true;
        do {
            String n = InputHandler.getString("请输入您想查看的点与您当前位置相差的步数（后方用负数），输入x退出：");
            if (n.equals("x")) {
                b = false;
            } else {
                try {
                    int num = Integer.parseInt(n);
                    int location = Calculation.calculateLocation(player.getLocation(), num);
                    Dot dot = map.getDot(location);
                    dot.printInfo();
                } catch (Exception e) {
                    InputHandler.warning();
                }
            }
        } while (b);
    }

    private boolean tbc(){
        int bankrupt = 0;
        for (Player p:
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
        System.out.println("================    游   戏   开   始   ================");
        boolean over = false;
        while (!over) {
            round();
            over = !tbc();
            date.addDate();
            stockMarket.update();
        }
        int i;
        for (i = 0; i < players.length; i++) {
            if (!players[i].isBankrupt()) {
                break;
            }
        }
        System.out.println("================    游   戏   结   束   ================");
        if (i < players.length) {
            System.out.println(players[i].getName()+"最终获胜!");
        } else {
            System.out.println("全军覆没Orz");
        }
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        game.start();
    }

    public static Player[] getPlayers() {
        return players;
    }

    public static Map getMap() {
        return map;
    }

    public static StockMarket getStockMarket() {
        return stockMarket;
    }

    public static Date getDate() {
        return date;
    }
}
