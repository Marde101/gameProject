package fi.tuni.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Locale;

public class Main extends Game {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;

    private SpriteBatch batch;

    //screens
    private StartScreen startScreen;
    private FieldScreen fieldScreen;
    private CityScreen cityScreen;

    //fonts
    private FreeTypeFontGenerator generator;
    private BitmapFont fontBig;
    private BitmapFont fontSmall;
    private BitmapFont fontSmallest;

    private Stage uiStage;
    private float width = WINDOW_WIDTH;
    private float height = WINDOW_HEIGHT;
    private Clickable sceneSwitch;
    private Settings settings;
    private Balance cash;
    private Balance pee;
    private Balance poo;

    // determines which screen is active
    // and also which screen launches from startScreen
    private boolean inCity = false;


    private I18NBundle myBundle;
    private I18NBundle myBundle_en;
    private I18NBundle myBundle_fi;

    @Override
    public void create () {
        batch = new SpriteBatch();
        cityScreen = new CityScreen(this);
        fieldScreen = new FieldScreen(this);
        startScreen = new StartScreen(this);
        setScreen(startScreen);
        fontBig = createFont(60);
        fontSmall = createFont(35);
        fontSmallest = createFont(20);
        uiStage = new Stage(new FitViewport(width, height));
        sceneSwitch = new Clickable();
        RequestSound.playBackgroundMusic();
        settings = new Settings();
        fetchValues();
        MemoryWriter.writeCurrentTimestamp();
        Locale locale = new Locale("en", "UK");
        Locale localeen = new Locale("fi", "FI");
        Locale localefi = new Locale("fi", "FI");

        myBundle =I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
        myBundle_en =I18NBundle.createBundle(Gdx.files.internal("MyBundle_en"), localeen);
        myBundle_fi =I18NBundle.createBundle(Gdx.files.internal("MyBundle_fi"), localefi);
    }

    private void fetchValues() {
        cash = new Balance("Cash");
        pee = new Balance("Pee");
        poo = new Balance("Poo");
        if (cash.getValue() == 0 && pee.getValue()
                == 0 && poo.getValue() == 0) {
            cash.addValue(1000);
            pee.addValue(100);
            poo.addValue(100);
        }
    }

    private BitmapFont createFont(int size) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        return generator.generateFont(parameter);
    }

    public void switchScene() {
        if (Gdx.input.justTouched() && inCity == false) {
            inCity = true;
            setScreen(cityScreen);
            uiStage.clear();
        } else if (Gdx.input.justTouched() && inCity == true) {
            inCity = false;
            setScreen(fieldScreen);
            uiStage.clear();
        }
        sceneSwitch.setHappened(false);
    }

    public void setStartScreen() {
        setScreen(startScreen);
        uiStage.clear();
    }

    public I18NBundle getBundle() {
        if (settings.getEng()) {
            return myBundle_en;
        } else {
            return myBundle_fi;
        }
    }
    public BitmapFont getFontBig() {
        return fontBig;
    }
    public BitmapFont getFontSmall() {
        return fontSmall;
    }
    public BitmapFont getFontSmallest() {
        return fontSmallest;
    }
    public Stage getUIStage() {
        return uiStage;
    }
    public Clickable getSceneSwitch() {
        return sceneSwitch;
    }
    public Settings getSettings() {
        return settings;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public ArrayList getFields() {
        return fieldScreen.getAllFields();
    }
    public ArrayList getToilets() {
        return cityScreen.getAllToilets();
    }
    public Balance getBalanceCash() {
        return cash;
    }
    public Balance getBalancePee() {
        return pee;
    }
    public Balance getBalancePoo() {
        return poo;
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
        startScreen.dispose();
        fieldScreen.dispose();
        cityScreen.dispose();
        fontBig.dispose();
        fontSmall.dispose();
        fontSmallest.dispose();
        generator.dispose();
        uiStage.dispose();
    }
}
