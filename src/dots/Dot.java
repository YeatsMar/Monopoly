package dots;

import main.Game;
import main.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mayezhou on 16/4/7.
 */
public abstract class Dot {
    //location
    protected int x;
    protected int y;
    protected int id;
    protected String symbol;
    protected String info;
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected boolean blocked;
    protected boolean accessible;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        blocked = false;
    }

    public abstract void event(Player player);

    public void printInfo() {
        System.out.println("类型：");
    }

    //getter & setter

    public boolean isAccessible() {
        return accessible;
    }

    public String getInfo() {
        return info;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toTextual() {
        return players.stream().map(item->item.getSymbol()).findFirst().orElse(symbol);
    }

    public String toOriginalTextual() {
        switch (symbol) {
            case "○ "://0's
            case "● "://1
            case "□ "://2
            case "■ "://3
                return "◎ ";
            default:
                return symbol;
        }
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean hasPlayer() {
        return !players.isEmpty();
    }

    public void addPlayer(Player player) {
        players.add(0, player);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

}
