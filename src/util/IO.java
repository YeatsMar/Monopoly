package util;

import GUI.TextMessage;

import javax.swing.*;

/**
 * Created by mayezhou on 16/4/8.
 */
public class IO {
    public static int getInt(String instruction) {
        int result = 0;
        String s = null;
        try {
            s = JOptionPane.showInputDialog(instruction);
            result = Integer.parseInt(s);
        } catch (Exception e) {
            if (s.length() != 0) {
                warning();
            }
        }
        return result;
    }

    public static double getDouble(String instruction) {
        double result = 0;
        String s = null;
        try {
            s = JOptionPane.showInputDialog(instruction);
            result = Double.parseDouble(s);
        } catch (Exception e) {
            if (s.length() != 0) {
                warning();
            }
        }
        return result;
    }

    public static String getString(String instruction) {
        String result = null;
        try {
            result = JOptionPane.showInputDialog(instruction);
        } catch (Exception e) {
            if (result.length() != 0) {
                warning();
            }
        }
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
        TextMessage.text.append(s + "\n");
//        TextMessage.text.setText(s);
    }
}
