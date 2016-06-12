package util;

import GUI.TextMessage;

import javax.swing.*;

/**
 * Created by mayezhou on 16/4/8.
 */
public class IO {
    public static int getInt(String instruction) {
        boolean continueInput = true;
        int result = 0;
        do {
            try {
                String s = JOptionPane.showInputDialog(instruction);
                result = Integer.parseInt(s);
                continueInput = false;
            } catch (Exception e) {
                warning();
            }
        } while (continueInput);
        return result;
    }

    public static double getDouble(String instruction) {
        boolean continueInput = true;
        double result = 0;
        do {
            try {
                String s = JOptionPane.showInputDialog(instruction);
                result = Double.parseDouble(s);
                continueInput = false;
            } catch (Exception e) {
                warning();
            }
        } while (continueInput);
        return result;
    }

    public static String getString(String instruction) {
        boolean continueInput = true;
        String result = null;
        do {
            try {
                result = JOptionPane.showInputDialog(instruction);
                continueInput = false;
            } catch (Exception e) {
                warning();
            }
        } while (continueInput);
        return result;
    }

    public static void warning() {
        JOptionPane.showMessageDialog(null, "输入有误,请根据操作重新输入!");
    }

    public static void warning(String s) {
        JOptionPane.showMessageDialog(null, s);
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

    public static void showMessage(String s) {
        warning(s);
        print(s);
    }

    public static void print(String s) {
//        TextMessage.text.append(s);
        TextMessage.text.setText(s);
    }
}
