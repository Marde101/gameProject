package fi.tuni.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;

    SpriteBatch batch;
    private boolean inCity = false;

    public SpriteBatch getBatch() {
        return batch;
    }

    private FieldScreen field;
    private CityScreen city;

    private FreeTypeFontGenerator generator;
    private BitmapFont font;

    private Stage stage;
    private float width = WINDOW_WIDTH;
    private float height = WINDOW_HEIGHT;
    private Clickable sceneSwitch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        city = new CityScreen(this);
        field = new FieldScreen(this);
        setScreen(field);
        createFont();

        stage = new Stage(new FitViewport(width, height));
        sceneSwitch = new Clickable();
        stage.addActor(sceneSwitch);

        Currency.setBalance(1234561);
    }

    private void createFont() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
    }

    public BitmapFont getFont() {
        return font;
    }

    public Stage getStage() {
        return stage;
    }

    public Clickable getSceneSwitch() {
        return sceneSwitch;
    }

    @Override
    public void render () {
        super.render();
    }

    public void switchScene() {
        if (Gdx.input.justTouched() && inCity == false) {
            inCity = true;
            setScreen(city);
        } else if (Gdx.input.justTouched() && inCity == true) {
            inCity = false;
            setScreen(field);
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        field.dispose();
        city.dispose();
        font.dispose();
        generator.dispose();
    }
}