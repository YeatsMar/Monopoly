package main;

import dots.Dot;
import dots.Estate;
import util.DotFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Map {
    private Collection<Dot> allDots = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>(68);
    private int height;
    private int width;
    private int dotsNumber;//dots.size

    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        for (int i = 0; i < 68; i++) {//set the size
            dots.add(new Estate(0, 0));
        }
    }

    public Dot getDot(int x, int y, char symbol, int index) {// TODO: 16/6/6 Lambda: avoid adding new dots 
        return allDots.stream().filter(
                item -> (item.getX() == x && item.getY() == y))
                .findFirst().orElse(createDot(x, y, symbol, index));
    }

    public Dot getDot(int x, int y) {
        return allDots.stream().filter(
                item -> (item.getX() == x && item.getY() == y))
                .findFirst().orElse(null);
    }

    public Dot getDot(int id) {
        return dots.get(id);
    }

    private Dot createDot(int x, int y, char symbol, int index) {
        Dot d = DotFactory.createDot(x, y, symbol);
        allDots.add(d);
        if (d.isAccessible()) {
            dots.set(index, d);
        }
        return d;
    }

    public Estate getEstate(int location) {
        return (Estate) dots.get(location);
    }

    public void setStreet(Game game) {
        Street mercury = new Street("水星", 4, 0, game);
        Street venus = new Street("金星", 4, 5, game);
        Street earth = new Street("地球", 4, 10, game);
        Street mars = new Street("火星", 6, 31, game);
        Street jupiter = new Street("木星", 4, 46, game);
        Street saturn = new Street("土星", 4, 52, game);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDotsNumber() {
        return dotsNumber;
    }

    public void setDotsNumber(int dotsNumber) {
        this.dotsNumber = dotsNumber;
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public void printCurrent() {
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(getDot(x, y).toTextual());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void printInitial() {
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(getDot(x, y).toOriginalTextual());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
