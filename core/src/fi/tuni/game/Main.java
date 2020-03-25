package fi.tuni.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    FieldScreen field;
    CityScreen city;

    @Override
    public void create () {
        batch = new SpriteBatch();
        city = new CityScreen(this);
        field = new FieldScreen(this);
        setScreen(field);
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
    }
}