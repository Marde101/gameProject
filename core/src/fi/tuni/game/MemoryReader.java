package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MemoryReader {

    static public void readBalance(Balance x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        int value = prefs.getInteger(x.getKey());
        x.setValue(value);
    }

    static public void readToilet(Toilets x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.xml");
        int tier = prefs.getInteger(x.getKey());
        x.setTier(tier);
    }

}
