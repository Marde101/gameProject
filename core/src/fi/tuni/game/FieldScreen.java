package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FieldScreen implements Screen {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera camera;
    private OrthographicCamera fontCamera;
    private TiledMap fieldtiledMap;
    private TiledMapRenderer fieldtiledMapRenderer;


    private Texture balanceBackground;

    public FieldScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_HEIGHT*100);
        // Show always area of our world 8.00 x 4.80
        camera.setToOrtho(false,         // y points up
                WINDOW_WIDTH,            // width
                WINDOW_HEIGHT);          // height
        fieldtiledMap = new TmxMapLoader().load("prototiled.tmx");
        fieldtiledMapRenderer = new OrthogonalTiledMapRenderer(fieldtiledMap, 1 / 100f);

        balanceBackground = new Texture("coin.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        fieldtiledMapRenderer.setView(camera);
        fieldtiledMapRenderer.render();

        //rahamäärä
        batch.setProjectionMatrix(fontCamera.combined);
        batch.begin();
        objectMain.getFont().draw(batch, Currency.getStringBalance(), 825, 615);
        batch.draw(balanceBackground, 740, 555);
        batch.end();

        //scenenvaihto nappi
        objectMain.getStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getStage().draw();

        //toiminnallisuus nappiin
        Gdx.input.setInputProcessor(objectMain.getStage());
        if (objectMain.getSceneSwitch().getHappened()) {
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);
        }
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