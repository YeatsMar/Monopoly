package dots;

import main.Player;
import main.Street;
import util.Calculation;
import util.InputHandler;

/**
 * Created by mayezhou on 16/4/7.
 */
public class Estate extends Dot{
    private Player owner;
    private int level;
    private Street street;
    private int price;
    private int index;

    public Estate(int x, int y) {
        super(x, y);
        symbol = "◎ ";
        info = "房屋";
        accessible = true;
        owner = null;
        level = 1;
        price = 200;//default fundamental cost
    }

    @Override
    public void event(Player player) {
        switch (symbol) {
            case "◎ "://no owner
                buy(player);
                break;
            case "○ "://0's
            case "● "://1
            case "□ "://2
            case "■ "://3
                if (player == owner) {
                    levelUp();
                } else {
                    toll(player);
                }
                break;
        }
    }

    private void toll(Player player) {
        double toll = getPrice()*0.3+getStreetBonus();
        toll = Calculation.roundUpDouble(toll);
        System.out.println("此处为"+owner.getName()+"的房产,需要支付过路费"+toll+"元");
        if (player.getCash() >= toll) {
            player.addCash(-toll);
            System.out.println("扣除现金");
        } else if (player.getDeposit() >= toll-player.getCash()){
            System.out.println("现金不足,剩余部分交付存款");
            player.setCash(0);
            player.addDeposit(-(toll-player.getCash()));
        } else {
            System.out.println("现金、存款均不足，依照土地编号顺序卖房抵债");
            double remaining = toll-player.getDeposit()-player.getCash();
            player.setDeposit(0);
            player.setCash(0);
            for (Estate estate : player.getEstates()) {
                estate.sell();
                if (player.getCash() >= remaining) {
                    break;
                }
            }
            player.addCash(-remaining);
            if (player.getCash() < 0) {
                System.out.println(player.getName()+"始终无法交付全部过路费,已破产,将其全部财产给予"+owner.getName()+", " +
                        owner.getName()+"仅得部分过路费");
                owner.addCash(toll+player.getCash());
            }
        }
    }

    private double getStreetBonus() {
        double bonus = 0;
        Street s = getStreet();
        for (Estate e : s.getEstates()) {
            if (e.owner == owner && e != this) {
                bonus += e.getPrice();
            }
        }
        bonus = Calculation.roundUpDouble(bonus*0.1);
        return bonus;
    }

    private void sell() {
        owner.addCash(getPrice());
        symbol = "◎ ";
        owner = null;
    }

    private void levelUp() {
        String s = InputHandler.getString("该地为您的土地，请问是否选择升级？升级需花费100元\tY/N");
        s = s.toUpperCase();
        while (!s.equals("Y") && !s.equals("N")) {
            InputHandler.warning();
            s = InputHandler.getString("该地为您的土地，请问是否选择升级？升级需花费100元\tY/N");
        }
        if (s.equals("Y")) {
            if (owner.getCash() >= 100) {
                if (level < 6) {
                    level++;
                    System.out.println("土地升级成功,当前等级为"+level);
                } else {
                    System.out.println("土地已满级_(:з╂∠)_");
                }
                owner.addCash(-100);
            } else {
                System.out.println("现金不足,升级失败");
            }
        }
    }

    private void buy(Player player) {
        String s = InputHandler.getString("是否购买土地?需用现金支付200元\tY/N");
        while (!s.equals("Y") && !s.equals("N")) {
            InputHandler.warning();
            s = InputHandler.getString("请输入Y/N(大写)");
        }
        if (s.equals("Y")) {
            if (player.getCash() >= getPrice()) {
                owner = player;
                switch (player.getPlayerID()) {
                    case 0:
                        symbol = "○ ";
                        break;
                    case 1:
                        symbol = "● ";
                        break;
                    case 2:
                        symbol = "□ ";
                        break;
                    case 3:
                        symbol = "■ ";
                        break;
                }
                player.setCash(player.getCash()-getPrice());
                System.out.println("玩家"+player.getName()+"购买土地成功~");
                player.printProperty();
            } else {
                System.out.println("没钱买什么地_(:з╂∠)_");
            }
        } else {
            System.out.println("居然不买地_(:зゝ∠)_");
        }
    }

    @Override
    public void printInfo() {
        System.out.println("类型：房产\n名称："
                + (street==null?"零散房屋" : street.getName()+index+"座")
                + "\n初始价格：200\n当前等级："
                + getLevel()
                + "\n拥有者："+(owner== null? "无" : owner.getName()));
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Street getStreet() {
        return street;
    }

    public int getPrice() {
        return price*level;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
