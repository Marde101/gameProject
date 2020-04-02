package fi.tuni.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class Localization {

    public Localization() {
        //init();
    }

    private void init() {
        Locale locale = new Locale("en", "UK");

        Locale defaultLocale = Locale.getDefault();

        I18NBundle myBundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);

        String title = myBundle.get("title");
        String score = myBundle.format("score");
    }
}
