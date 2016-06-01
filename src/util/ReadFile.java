package util;

import dots.Dot;
import main.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mayezhou on 16/4/10.
 */
public class ReadFile {
    public static Map read() {
        try {
            Scanner input = new Scanner(new File("map.txt"));
            String[] dimension = input.nextLine().split(" ");
            Map map = new Map(Integer.parseInt(dimension[0]),Integer.parseInt(dimension[1]));
            for (int y = 0; y < map.getHeight(); y++) {
                char[] chars = input.nextLine().toCharArray();
                for (int x = 0; x < map.getWidth()*3; x+=3) {
                    char symbol = chars[x];
                    try {
                        int index = Integer.parseInt(chars[x+1]+"") * 10 + Integer.parseInt(chars[x+2]+"");
                        map.getDot(x/3, y, symbol, index);
                    } catch (Exception e) {
                        map.getDot(x/3, y, symbol, 0);
                    }
                }
            }
            input.close();
            map.setDotsNumber(map.getDots().size());
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
