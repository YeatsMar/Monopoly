package util;

import main.Game;
import main.Player;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Date {//extends Calendar??
    private int year;
    private int month;
    private int day;
    private boolean isLeapYear;
    private int weekday;
    private boolean isWeekday;

    public Date() {//default
        year = 2014;
        month = 1;
        day = 1;
        isLeapYear = (year % 4 == 0 && year % 100 != 0)
                || (year % 400 == 0);
        weekday = 1;
        setWeekday();
    }

    public Date(int year, int month, int day, int weekday) {
        this.year = year;
        this.month = month;
        this.day = day;
        setLeapYear();
        this.weekday = weekday;
        setWeekday();
    }

    public void setLeapYear() {
        isLeapYear = (year % 4 == 0 && year % 100 != 0)
                || (year % 400 == 0);
    }

    public String getWeekday() {
        String s = "星期";
        switch (weekday) {
            case 1:
                s += "一";
                break;
            case 2:
                s += "二";
                break;
            case 3:
                s += "三";
                break;
            case 4:
                s += "四";
                break;
            case 5:
                s += "五";
                break;
            case 6:
                s += "六";
                break;
            case 7:
                s += "日";
                break;
        }
        return s;
    }

    public void print() {
        System.out.println("今天是"+year+"年"+month+"月"+day+"日\t"+getWeekday());
    }

    // addDate method
    public void addDate() {
        day++;
        weekday++;
        if (weekday > 7) {
            weekday -= 7;
        }
        setWeekday();
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10) {
            if (day > 31) {
                month++;
                day = 1;
                interests();
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                month++;
                day = 1;
                interests();
            }
        }
        if (month == 2) {
            if (isLeapYear) {
                if (day > 29) {
                    month++;
                    day = 1;
                    interests();
                }
            } else {
                if (day > 28) {
                    month++;
                    day = 1;
                    interests();
                }
            }
        }
        if (month == 12 && day == 32) {
            year++;
            month = 1;
            day = 1;
            interests();
        }
    }

    private void interests() {
        System.out.println("月底银行存款具有10%利息~");
        Player[] players = Game.getPlayers();
        for (Player p:
             players) {
            if (p.isBankrupt()) {
                continue;
            }
            p.setDeposit(p.getDeposit()*1.1);
            p.printProperty();
        }
        System.out.println("\n\n\n");
    }

    public boolean isWeekday() {
        return isWeekday;
    }

    private void setWeekday() {
        if (weekday == 6 || weekday == 7) {
            isWeekday = false;
        } else {
            isWeekday = true;
        }
    }

    // to get year
    public int getYear() {
        return year;
    }

    // to get month
    public int getMonth() {
        return month;
    }

    // to get day
    public int getDay() {
        return day;
    }

    // to set year
    public void setYear(int y) {
        year = y;
    }

    // to set month
    public void setMonth(int m) {
        month = m;
    }

    // to set day
    public void setDay(int d) {
        day = d;
    }
}
