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
    private Toilets toilets;
    private ArrayList<Toilets> allToilets;

    public CityScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_HEIGHT*100);
        //Camera for tiledmap
        camera.setToOrtho(false,
                WINDOW_WIDTH,
                WINDOW_HEIGHT);
        cityTiledMap = new TmxMapLoader().load("kaupunkikesken.tmx");
        cityTiledMapRenderer = new OrthogonalTiledMapRenderer(cityTiledMap, 1 / 100f);

        cashBackground = new Texture("coin.png");

        allToilets = new ArrayList<>();
        generateToilets();
    }

    private void generateToilets() {
        generateToilet(2.86f,1f, "Toilet_1");
        generateToilet(2.22f,3.88f, "Toilet_2");
        generateToilet(9.58f,3.88f, "Toilet_3");
        generateToilet(10.55f,0.36f, "Toilet_4");
        generateToilet(5.75f,2.6f, "Toilet_5");
        generateToilet(7.98f,2.28f, "Toilet_6");
        generateToilet(10.87f,4.53f, "Toilet_7");
    }

    private void generateToilet(float posX, float posY, String key) {
        toilet = new Toilet(posX, posY);
        toilets = new Toilets(toilet, key);
        allToilets.add(toilets);
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        cityTiledMapRenderer.setView(camera);
        cityTiledMapRenderer.render();

        //fontCamera
        batch.setProjectionMatrix(fontCamera.combined);

        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();

        batch.begin();
        drawToilets();
        objectMain.getFontBig().draw(batch, objectMain.getBalanceCash().getValueToString(), 825, 615);
        batch.draw(cashBackground, 740, 555);
        batch.end();
        objectMain.getUIStage().addActor(objectMain.getSceneSwitch());
        //sceneswitch function
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        if (objectMain.getSceneSwitch().getHappened()) {
            closeMenu();
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);
        }
    }

    private void drawToilets() {
        //add actors
        for(Toilets huus: allToilets) {
            Toilet tmpHuussi = huus.getToilet();
            Menu tmpMenu = huus.getMenu();
            BackButton tmpBackButton = huus.getBackButton();
            ButtonBackground tmpContract = huus.getContractButton();
            ButtonBackground tmpContract2 = huus.getContractButton2();
            ButtonBackground tmpUpgrade = huus.getUpgradeButton();
            objectMain.getUIStage().addActor(huus.getToilet());

            //toilet menu
            if (tmpHuussi.getHappened()) {
                objectMain.getUIStage().addActor(tmpMenu);
                objectMain.getUIStage().addActor(tmpBackButton);
                if (huus.getTier() > 0) {
                    objectMain.getUIStage().addActor(tmpContract);
                    objectMain.getFontSmall().draw(batch, "Virtsa", 740, 440);
                    objectMain.getUIStage().addActor(tmpContract2);
                    objectMain.getFontSmall().draw(batch, "Uloste", 740, 340);
                }

                if (huus.getTier() < 4) {
                    objectMain.getUIStage().addActor(tmpUpgrade);
                    objectMain.getFontSmall().draw(batch, huus.getPrice(), 810, 240);
                }

                if (tmpUpgrade.getHappened()) {
                    if (objectMain.getBalanceCash().getValue() > Integer.parseInt(huus.getPrice())) {
                        objectMain.getBalanceCash().removeValue(Integer.parseInt(huus.getPrice()));
                        huus.upgrade();
                        closeMenu();
                    }
                }

                if (tmpBackButton.getHappened()) {
                    closeMenu();
                }
            }
        }
    }

    private void closeMenu() {
        for(Toilets huus: allToilets) {
            Toilet tmpHuussi = huus.getToilet();
            Menu tmpMenu = huus.getMenu();
            BackButton tmpBackButton = huus.getBackButton();
            ButtonBackground tmpContract = huus.getContractButton();
            ButtonBackground tmpContract2 = huus.getContractButton2();
            ButtonBackground tmpUpgrade = huus.getUpgradeButton();

            tmpHuussi.setHappened(false);
            tmpMenu.setHappened(false);
            tmpBackButton.setHappened(false);
            tmpContract.setHappened(false);
            tmpContract2.setHappened(false);
            tmpUpgrade.setHappened(false);
        }
        objectMain.getUIStage().clear();
        drawToilets();
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
