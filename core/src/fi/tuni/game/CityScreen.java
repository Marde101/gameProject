package fi.tuni.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class CityScreen implements Screen {


    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera camera;
    private OrthographicCamera fontCamera;
    private TiledMap cityTiledMap;
    private TiledMapRenderer cityTiledMapRenderer;


    public CityScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_WIDTH*100);
        // Show always area of our world 8.00 x 4.80
        camera.setToOrtho(false,         // y points up
                WINDOW_WIDTH,            // width
                WINDOW_HEIGHT);          // height
        cityTiledMap = new TmxMapLoader().load("protoCity.tmx");
        cityTiledMapRenderer = new OrthogonalTiledMapRenderer(cityTiledMap, 1 / 100f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        cityTiledMapRenderer.setView(camera);
        cityTiledMapRenderer.render();
        batch.setProjectionMatrix(fontCamera.combined);
        batch.begin();
        objectMain.getFont().draw(batch, "$ 12350", 800, 1250);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

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
