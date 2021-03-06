package fi.tuni.shitionaire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MemoryReader {

    static public void readBalance(Balance x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int value = prefs.getInteger(x.getKey());
        x.setValue(value);
    }

    static public void readToilet(Toilets x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int tier = prefs.getInteger(x.getKey());
        x.setTier(tier);
    }

    static public void readField(Fields x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int cont = prefs.getInteger(x.getKey());
        x.setCont(cont);
    }

    static public void readToiletCont(Toilets x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int cont = prefs.getInteger(x.getKeyC());
        x.setCont(cont);
    }

    static public long readTimer(String key) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        return prefs.getLong(key);
    }


    static public long readCurrentTimestamp() {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        long time = prefs.getLong("CurrentTimestamp");
        return time;
    }

    static public boolean readVolume(String key) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        boolean ON = prefs.getBoolean(key);
        return ON;
    }

    static public boolean readLang() {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        boolean eng = prefs.getBoolean("Language");
        return eng;
    }

    static public boolean readFirstLaunch() {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        boolean firstLaunch = prefs.getBoolean("Launched");
        return firstLaunch;
    }
}