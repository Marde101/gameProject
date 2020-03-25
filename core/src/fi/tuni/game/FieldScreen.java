package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class FieldScreen implements Screen {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main object;
    private OrthographicCamera camera;
    private TiledMap fieldtiledMap;
    private TiledMapRenderer fieldtiledMapRenderer;

    public FieldScreen(Main x) {
        batch = x.getBatch();
        object = x;
        camera = new OrthographicCamera();
        // Show always area of our world 8.00 x 4.80
        camera.setToOrtho(false,         // y points up
                WINDOW_WIDTH,            // width
                WINDOW_HEIGHT);          // height
        fieldtiledMap = new TmxMapLoader().load("uus.tmx");
        fieldtiledMapRenderer = new OrthogonalTiledMapRenderer(fieldtiledMap, 1 / 100f);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        fieldtiledMapRenderer.setView(camera);
        fieldtiledMapRenderer.render();
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }
}