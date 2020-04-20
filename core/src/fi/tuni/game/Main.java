package fi.tuni.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class Main extends Game {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;

    private SpriteBatch batch;
    private StartScreen startScreen;
    private FieldScreen fieldScreen;
    private CityScreen cityScreen;

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
    private boolean inCity = true;

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
        settings = new Settings();
        fetchValues();
        MemoryWriter.writeCurrentTimestamp();
        RequestSound.playBackgroundMusic();
    }

    private void fetchValues() {
        cash = new Balance("Cash");
        pee = new Balance("Pee");
        poo = new Balance("Poo");
        cash.addValue(1500000);
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
