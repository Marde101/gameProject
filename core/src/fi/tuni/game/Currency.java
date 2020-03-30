package fi.tuni.game;

public class Currency {

    static private int balance = 0;

    static public void setBalance(int x) {
        balance = x;
    }

    static public void addBalance(int x) {
        balance += x;
    }

    static public void removeBalance(int x) {
        balance -= x;
    }

    static public int getBalance() {
        return balance;
    }

    static public String getStringBalance() {
        return Integer.toString(balance);
    }
}
