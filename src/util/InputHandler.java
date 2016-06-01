package util;

import java.util.Scanner;

/**
 * Created by mayezhou on 16/4/8.
 */
public class InputHandler {
    public static int getInt(String instruction) {
        System.out.println(instruction);
        boolean continueInput = true;
        int result = 0;
        do {
            try {
                Scanner input = new Scanner(System.in);
                result = input.nextInt();
                continueInput = false;
            } catch (Exception e) {
                System.out.println("输入有误! \n" + instruction);
            }
        } while (continueInput);
        return result;
    }

    public static double getDouble(String instruction) {
        System.out.println(instruction);
        boolean continueInput = true;
        double result = 0;
        do {
            try {
                Scanner input = new Scanner(System.in);
                result = input.nextDouble();
                continueInput = false;
            } catch (Exception e) {
                System.out.println("输入有误! \n" + instruction);
            }
        } while (continueInput);
        return result;
    }

    public static String getString(String instruction) {
        System.out.println(instruction);
        boolean continueInput = true;
        String result = null;
        do {
            try {
                Scanner input = new Scanner(System.in);
                result = input.nextLine();
                continueInput = false;
            } catch (Exception e) {
                System.out.println("输入有误! \n" + instruction);
            }
        } while (continueInput);
        return result;
    }

    public static void warning() {
        System.out.println("输入有误,请根据指示重新输入");
    }

    public static boolean isNumeric(String s) {
        boolean result = false;
        try {
            Integer.parseInt(s);
            result = true;
        } catch (Exception e) {
        }
        return result;
    }
}
