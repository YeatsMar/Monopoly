package dots;

import main.Player;
import util.IO;
import util.Icon;

/**
 * Created by mayezhou on 16/6/1.
 */
public class HospitalDot extends Dot {
    private int round;
    private int location = 20;

    public HospitalDot(int x, int y) {
        super(x, y);
        symbol = "医";
        this.setIcon(Icon.hospital);
        info = "医院";
        accessible = true;
        round = 2;
    }

    private void reset() {
        round = 2;
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        if (!player.isHealthy()) {
            if (round > 0) {
                System.out.println("您需要治疗,在此期间您不得离开医院,根据您的情况,您至少需要停留在此地"+round+"回合");
                round--;
            } else {
                System.out.println("您的疗程已结束,但是治疗后您需要逆时针行走_(:зゝ∠)_");
                player.setHealthy(true);
                player.setDirection(-1);
                this.reset();
            }
        }
        else {
            System.out.println("您好,这里是医院,无事勿扰:-D");
        }
    }
}
