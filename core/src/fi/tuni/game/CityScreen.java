package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class CityScreen implements Screen {


    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera camera;
    private OrthographicCamera fontCamera;
    private TiledMap cityTiledMap;
    private TiledMapRenderer cityTiledMapRenderer;

    private Texture cashBackground;

    private Toilet toilet;
    private Menut menut;
    private ArrayList<Menut> allMenut;

    public CityScreen(Main x) {
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
        cityTiledMap = new TmxMapLoader().load("siti.tmx");
        cityTiledMapRenderer = new OrthogonalTiledMapRenderer(cityTiledMap, 1 / 100f);

        cashBackground = new Texture("coin.png");

        allMenut = new ArrayList<>();
        generateHuusseja(5);

    }

    private void generateHuusseja(int x) {
        for (int y = 0; y < x; y++) {
            toilet = new Toilet(y, 1f);
            menut = new Menut(toilet);
            allMenut.add(menut);
        }
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        cityTiledMapRenderer.setView(camera);
        cityTiledMapRenderer.render();

        //rahamäärä
        batch.setProjectionMatrix(fontCamera.combined);
        batch.begin();
        objectMain.getFont().draw(batch, objectMain.getBalanceCash().getValueToString(), 825, 615);
        batch.draw(cashBackground, 740, 555);
        batch.end();

        //scenenvaihto nappi
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();
        //lisätään huussit stagelle
        for(Menut huus: allMenut) {
            Toilet tmpHuussi = huus.getToilet();
            Menu tmpMenu = huus.getMenu();
            BackButton tmpBackButton = huus.getBackButton();
            objectMain.getUIStage().addActor(huus.getToilet());
            //huussi menu avautuu
            if (tmpHuussi.getHappened()) {
                objectMain.getUIStage().addActor(tmpMenu);
                objectMain.getUIStage().addActor(tmpBackButton);
                if (tmpBackButton.getHappened()) {
                    closeMenu();
                }
            }
        }

        //toiminnallisuus nappiin
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        if (objectMain.getSceneSwitch().getHappened()) {
            closeMenu();
            objectMain.getUIStage().clear();
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);

        }


    }

    private void closeMenu() {
        for(Menut huus: allMenut) {
            Toilet tmpHuussi = huus.getToilet();
            Menu tmpMenu = huus.getMenu();
            BackButton tmpBackButton = huus.getBackButton();

            tmpHuussi.setHappened(false);
            tmpMenu.setHappened(false);
            tmpBackButton.setHappened(false);
        }

        objectMain.resetStage();
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
