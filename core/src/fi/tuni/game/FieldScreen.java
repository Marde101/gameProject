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

    private ArrayList<Fields> allFields;
    private Field field;
    private Fields fields;

    public FieldScreen(Main x) {
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
        fieldtiledMap = new TmxMapLoader().load("prototiled.tmx");
        fieldtiledMapRenderer = new OrthogonalTiledMapRenderer(fieldtiledMap,
                        1 / 100f);

        balanceBackground = new Texture("coin.png");
        allFields = new ArrayList<>();
        generateFields();

    }

    private void generateFields() {
        /*generateField(2.86f,1f);
        generateField(2.22f,3.88f);
        generateField(9.58f,3.88f);
        generateField(10.55f,0.36f);
        generateField(5.75f,2.6f);
        generateField(7.98f,2.28f);*/
        generateField(1f,1f);
    }

    private void generateField(float posX, float posY) {
        field = new Field(posX, posY);
        fields = new Fields(field);
        allFields.add(fields);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        fieldtiledMapRenderer.setView(camera);
        fieldtiledMapRenderer.render();

        //cash
        batch.setProjectionMatrix(fontCamera.combined);
        batch.begin();
        objectMain.getFont().draw(batch, objectMain.getBalanceCash().getValueToString(), 825, 615);
        batch.draw(balanceBackground, 740, 555);
        batch.end();

        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();

        //add actors
        for(Fields fied: allFields) {
            Field tmpField = fied.getField();
            Menu tmpMenu = fied.getMenu();
            BackButton tmpBackButton = fied.getBackButton();
            objectMain.getUIStage().addActor(fied.getField());
            objectMain.getUIStage().addActor(objectMain.getSceneSwitch());

            //field menu
            if (tmpField.getHappened()) {
                objectMain.getUIStage().addActor(tmpMenu);
                objectMain.getUIStage().addActor(tmpBackButton);

                if (tmpBackButton.getHappened()) {
                    closeMenu();
                }
            }
        }

        //sceneswitch function
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        if (objectMain.getSceneSwitch().getHappened()) {
            closeMenu();
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);
        }
    }

    private void closeMenu() {
        for(Fields fied: allFields) {
            Field tmpFied = fied.getField();
            Menu tmpMenu = fied.getMenu();
            BackButton tmpBackButton = fied.getBackButton();

            tmpFied.setHappened(false);
            tmpMenu.setHappened(false);
            tmpBackButton.setHappened(false);
        }
        objectMain.getUIStage().clear();
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