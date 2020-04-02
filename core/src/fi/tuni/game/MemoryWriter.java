package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class MemoryWriter {

    public MemoryWriter(Balance x) {
        init(x);
    }

    void init(Balance x) {
        Preferences prefs = Gdx.app.getPreferences("MyPreferences.txt");
        prefs.putInteger(x.getKey(), x.getValue());
        prefs.flush();
    }
}
