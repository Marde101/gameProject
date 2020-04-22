package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    private ArrayList<Fields> allFields;
    private boolean infoFetched = false;
    private boolean menuOpen = false;
    private String which = "";

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
        cityTiledMap = new TmxMapLoader().load("cityFinal.tmx");
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
        generateToilet(10.80f,4.53f, "Toilet_7");
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MemoryWriter.writeCurrentTimestamp();
        cityTiledMapRenderer.setView(camera);
        cityTiledMapRenderer.render();

        //fontCamera
        batch.setProjectionMatrix(fontCamera.combined);
        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();

        batch.begin();
        drawToilets();
        if (!infoFetched) {
            allFields = objectMain.getFields();
            infoFetched = true;
        }
        checkFieldsProduct();
        //balance
        objectMain.getFontBig().draw(batch, objectMain.getBalanceCash().getValueToString(), 825, 615);
        batch.draw(cashBackground, 740, 555);
        batch.end();


        objectMain.getUIStage().addActor(objectMain.getSceneSwitch());
        objectMain.getUIStage().addActor(objectMain.getSettings());
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        //sceneswitch function
        if (objectMain.getSceneSwitch().getHappened()) {
            closeMenu();
            objectMain.switchScene();
        }
        //settings function
        if (objectMain.getSettings().getHappened()) {
            closeMenu();
            objectMain.setStartScreen();
        }
    }

    private void drawToilets() {
        //add actors
        for(Toilets tmpToilets: allToilets) {
            Toilet tmpToilet = tmpToilets.getToilet();
            Menu tmpMenu = tmpToilets.getMenu();
            BackButton tmpBackButton = tmpToilets.getBackButton();
            ButtonBackground tmpContract = tmpToilets.getContractButton();
            ButtonBackground tmpContract2 = tmpToilets.getContractButton2();
            ButtonBackground tmpUpgrade = tmpToilets.getUpgradeButton();
            objectMain.getUIStage().addActor(tmpToilets.getToilet());

            //toilet menu
            if (tmpToilet.getHappened() &&
                    (!menuOpen || which.equals(tmpToilets.getKey()))) {
                if (!menuOpen) {
                    RequestSound.playButtonClick();
                }
                which = tmpToilets.getKey();
                menuOpen = true;
                if (tmpToilets.getTier() > 0 && !tmpToilets.getState()) {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                    objectMain.getFontBig().draw(batch, objectMain.getBundle().get("back"),575, 125);
                    objectMain.getUIStage().addActor(tmpContract);
                    objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("product1"), 740, 440);
                    objectMain.getFontSmallest().draw(batch, objectMain.getBundle().get("productdesc1"),265, 460);
                    objectMain.getUIStage().addActor(tmpContract2);
                    objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("product2"), 740, 340);
                    objectMain.getFontSmallest().draw(batch, objectMain.getBundle().get("productdesc2"),265, 360);
                } else {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                    objectMain.getFontBig().draw(batch, objectMain.getBundle().get("back"),575, 125);
                    if (tmpToilets.getState()) {
                        objectMain.getFontBig().draw(batch, tmpToilets.getTimeLeftString(),600, 420);
                        if (tmpToilets.getCont()==1) {
                            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("productstate1"), 380, 350);
                        } else if (tmpToilets.getCont()==2) {
                            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("productstate2"), 380, 350);
                        }
                    }
                }

                if (tmpToilets.getTier() == 0) {
                    objectMain.getUIStage().addActor(tmpUpgrade);
                    objectMain.getFontSmall().draw(batch, tmpToilets.getPrice(), 810, 240);
                    objectMain.getFontSmallest().draw(batch, objectMain.getBundle().get("toiletupgrade1"),265, 260);
                } else if (tmpToilets.getTier() < 5 && !tmpToilets.getState()) {
                    objectMain.getUIStage().addActor(tmpUpgrade);
                    objectMain.getFontSmall().draw(batch, tmpToilets.getPrice(), 810, 240);
                    objectMain.getFontSmallest().draw(batch, objectMain.getBundle().get("toiletupgrade2"),265, 260);
                }

                if (tmpContract.getHappened()) {
                    tmpToilets.startProduction(1);
                    closeMenu();
                } else if (tmpContract2.getHappened()) {
                    tmpToilets.startProduction(2);
                    closeMenu();
                }


                if (tmpUpgrade.getHappened()) {
                    if (objectMain.getBalanceCash().getValue() >= Integer.parseInt(tmpToilets.getPrice())) {
                        objectMain.getBalanceCash().removeValue(Integer.parseInt(tmpToilets.getPrice()));
                        tmpToilets.upgrade();
                        closeMenu();
                    }
                }

                if (tmpBackButton.getHappened()){
                    closeMenu();
                }
            } else if (tmpToilet.getHappened()){
                closeMenu();
            }

            //production check
            if (tmpToilets.getState()) {
                tmpToilets.checkProduction(objectMain.getBalancePee(), objectMain.getBalancePoo());
            }
            if (tmpToilets.getState() && !menuOpen) {
                objectMain.getFontBig().draw(batch,
                        tmpToilets.getTimeLeftString(),
                        tmpToilet.getPosX()*100,
                        tmpToilet.getPosY()*100+120);
            }
        }
    }

    public ArrayList getAllToilets() {
        return allToilets;
    }

    private void checkFieldsProduct() {
        for(Fields tmpFields: allFields) {
            if (tmpFields.getState()) {
            tmpFields.checkProduction(objectMain.getBalanceCash());
            }
        }
    }

    private void closeMenu() {
        RequestSound.playButtonClick();
        menuOpen = false;
        which = "";
        for(Toilets tmpToilets: allToilets) {
            Toilet tmpToilet = tmpToilets.getToilet();
            Menu tmpMenu = tmpToilets.getMenu();
            BackButton tmpBackButton = tmpToilets.getBackButton();
            ButtonBackground tmpContract = tmpToilets.getContractButton();
            ButtonBackground tmpContract2 = tmpToilets.getContractButton2();
            ButtonBackground tmpUpgrade = tmpToilets.getUpgradeButton();

            tmpToilet.setHappened(false);
            tmpMenu.setHappened(false);
            tmpBackButton.setHappened(false);
            tmpContract.setHappened(false);
            tmpContract2.setHappened(false);
            tmpUpgrade.setHappened(false);
        }
        objectMain.getSettings().setHappened(false);
        objectMain.getSettings().getMenu().setHappened(false);
        objectMain.getSettings().getBackButton().setHappened(false);
        objectMain.getSettings().setHappened(false);
        objectMain.getUIStage().clear();
        //drawToilets();
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
