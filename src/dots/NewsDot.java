package dots;

import main.Game;
import main.Player;
import props.Prop;
import util.IO;
import util.Icon;
import util.PropFactory;

/**
 * Created by mayezhou on 16/4/7.
 */
public class NewsDot extends Dot{
    public NewsDot(int x, int y) {
        super(x, y);
        symbol = "新";
        info = "新闻触发点";
        accessible = true;
        this.setIcon(Icon.newsIcon);
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        //trigger news randomly
        System.out.println("现在为您播报新闻:");
        int num = (int) (Math.random() * 6);
        Player[] p = Game.getPlayers();
        switch (num) {
            case 0:
                int id = 0;
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < p.length; i++) {
                    int n = p[i].getEstates().size();
                    if (n < min) {
                        min = n;
                        id = i;
                    } else if (n == min){
                        id = -1;
                    }
                }
                if (id == -1) {
                    System.out.println("本来想补助土地最少玩家,但是不存在\"最\",所以不补助了_(:зゝ∠)_");
                    return;
                }
                int amount = (int) (Math.random() * 2000 + 1);// 奖励金额
                System.out.println("公开补助土地最少者"
                        + p[id].getName() + " " + amount
                        + "元");
                p[id].setCash(amount+p[id].getCash());
                break;
            case 1:
                id = 0;
                int max = 0;
                for (int i = 0; i < p.length; i++) {
                    int n = p[i].getEstates().size();
                    if (n > max) {
                        max = n;
                        id = i;
                    } else if (n == max){
                        id = -1;
                    }
                }
                if (id == -1) {
                    System.out.println("本来想表扬土地最多玩家,但是不存在\"最\",所以不奖励了_(:зゝ∠)_");
                    return;
                }
                amount = (int) (Math.random() * 2000 + 1);
                System.out.println("公开表扬第一地主"
                        + p[id].getName() + " 奖励" + amount
                        + "元");
                p[id].setCash(amount+p[id].getCash());
                break;
            case 2:
                System.out.println("银行加发储金红利每个人得到存款 10%");
                for (Player i: p) {
                    i.setDeposit((int) (i.getDeposit()*1.1));
                    System.out.println("玩家"+i.getName()+"当前存款金额\t"+i.getDeposit());
                }
                break;
            case 3:
                System.out.println("所有人缴纳财产税 10%_(:з╂∠)_");
                for (Player i: p) {
                    i.setDeposit((int) (i.getDeposit()*0.9));
                    System.out.println("玩家"+i.getName()+"当前存款金额\t"+i.getDeposit());
                }
                break;
            case 4:
                int random = (int) Math.random() * 5;
                System.out.println("所有人随机获得道具卡" + Prop.names[random] + "一张");
                for (Player i: p) {
                    i.addCard(random, 1);
                }
                break;
            case 5:
                System.out.println(player.getName() + "突然被过路人袭击受伤,被送往医院抢救!");
                player.setHealthy(false);
                player.setLocation(20);
                break;
        }
    }

}
