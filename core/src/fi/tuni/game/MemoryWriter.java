package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;


public class MemoryWriter {

    static public void writeBalance(String key, int value) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        prefs.putInteger(key, value);
        prefs.flush();
    }

    static public void writeToilet(String key, int tier) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        prefs.putInteger(key, tier);
        prefs.flush();
    }

    static public void writeToiletTime(String key, long saved) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        prefs.putLong(key, saved);
        prefs.flush();
    }


    static public void writeCurrentTimestamp() {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        prefs.putLong("CurrentTimestamp", TimeUtils.millis());
        prefs.flush();
    }

}
