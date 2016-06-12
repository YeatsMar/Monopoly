package dots;

import main.Player;

/**
 * Created by mayezhou on 16/4/9.
 */
public class Nothing extends Dot {

    public Nothing(int x, int y) {
        super(x, y);
        symbol = "  ";
        accessible = false;
        this.setIcon(null);
    }

    @Override
    public void event(Player player) {

    }
}
