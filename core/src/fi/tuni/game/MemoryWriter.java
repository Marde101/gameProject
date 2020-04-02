package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class MemoryWriter {

    static public void writeBalance(String key, int value) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.txt");
        prefs.putInteger(key, value);
        prefs.flush();
    }
}
