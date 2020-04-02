package fi.tuni.game;

public class Balance {

    private String key = "";
    private int value = 0;

    public Balance(String y, int x) {
        key = y;
        value = x;
        MemoryWriter.writeBalance(key, value);
    }

    public Balance(String name) {
        key = name;
        MemoryReader.readBalance(this);
    }

    public void setValue(int x) {
        value = x;
        MemoryWriter.writeBalance(key, value);
    }

    public void addValue(int x) {
        value += x;
        MemoryWriter.writeBalance(key, value);
    }

    public void removeValue(int x) {
        value -= x;
        MemoryWriter.writeBalance(key, value);
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        MemoryReader.readBalance(this);
        return value;
    }

    public String getValueToString() {
        return Integer.toString(value);
    }
}
