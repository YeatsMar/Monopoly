package dots;

import main.Player;
import util.InputHandler;

import java.util.Random;

/**
 * Created by mayezhou on 16/4/26.
 */
public class LotteryDot extends Dot{
    public LotteryDot(int x, int y) {
        super(x, y);
        symbol = "彩";
        info = "彩票开奖点";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        String s = InputHandler.getString("刮刮乐售卖中,是否购买彩票?\tY/N\n彩票200元,中奖概率10%,奖金2000元~");
        while (!s.equals("Y") && !s.equals("N")) {
            InputHandler.warning();
            s = InputHandler.getString("请输入Y/N(大写):");
        }
        if (s.equals("Y")) {
            if (player.getCash() >= 200) {
                player.addCash(-200);
                if (new Random().nextInt(9) < 1) {//10%
                    System.out.println("恭喜中奖!获得2000元奖金");
                    player.addCash(2000);
                    player.printProperty();
                } else {
                    System.out.println("很遗憾本次没有中奖,希望下次有料");
                }
            } else {
                System.out.println("现金不足,无法购买");
            }
        } else {
            System.out.println(player.getName()+"放弃购买彩票");
        }
    }
}
