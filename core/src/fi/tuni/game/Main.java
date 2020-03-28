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


    @Override
    public void create () {
        batch = new SpriteBatch();
        city = new CityScreen(this);
        field = new FieldScreen(this);
        setScreen(field);
        createFont();
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

    @Override
    public void render () {
        super.render();
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