package dots;

import main.Player;

/**
 * Created by mayezhou on 16/4/7.
 */
public class TicketDot extends Dot{
    public TicketDot(int x, int y) {
        super(x, y);
        symbol = "券";
        info = "赠送券点";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        player.setTicketPoint(player.getTicketPoint()+1);
        System.out.println("获得点券1张");
    }
}
