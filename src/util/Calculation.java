package util;

import main.Game;
import main.Player;

import java.math.BigDecimal;

/**
 * Created by mayezhou on 16/4/23.
 */
public class Calculation {
    public static int calculateLocation(int location, int n) {
        int max = Game.map.getDots().size();
        location += n;
        if (location >= max) {
            location -= max;
        } else if (location < 0) {
            location += max;
        }
        return location;
    }

    public static int relativeDistance(int playerID0, int playerID1) {
        Player player0 = Game.players[playerID0];
        Player player1 = Game.players[playerID1];
        int l0 = Math.min(player0.getLocation(), player1.getLocation());
        int l1 = Math.max(player0.getLocation(), player1.getLocation());
        int max = Game.map.getDotsNumber();
        if (l0 >= 0 || l1 < max) {
            return Math.abs(l0 - l1);
        } else {
            return Math.abs(l1 - l0 - max);
        }
    }

    public static double roundUpDouble(double f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
}
