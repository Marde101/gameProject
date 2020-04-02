package fi.tuni.game;

public class Balance {

    private String key;
    private int value;

    public Balance(String y, int x) {
        key = y;
        value = x;
    }

    public void setValue(int x) {
        value = x;
    }

    public void addValue(int x) {
        value += x;
    }

    public void removeValue(int x) {
        value -= x;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return Integer.toString(value);
    }
}
