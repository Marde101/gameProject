package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class FieldScreen implements Screen {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera camera;
    private OrthographicCamera fontCamera;

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

        balanceBackground = new Texture("coin.png");
        allFields = new ArrayList<>();
        generateFields();

    }

    private void generateFields() {
        generateField(0f,0f);
        generateField(0f,3.2f);
        generateField(4.266f,0f);
        generateField(4.266f,3.2f);
        generateField(8.532f,0f);
        generateField(8.532f,3.2f);
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

        //fontCamera
        batch.setProjectionMatrix(fontCamera.combined);

        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();

        drawFields();

        //ui
        batch.begin();
        objectMain.getFontBig().draw(batch, objectMain.getBalanceCash().getValueToString(), 825, 615);
        batch.draw(balanceBackground, 740, 555);
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

    private void drawFields() {
        //add actors
        for(Fields fied: allFields) {
            Field tmpField = fied.getField();
            Menu tmpMenu = fied.getMenu();
            BackButton tmpBackButton = fied.getBackButton();
            objectMain.getUIStage().addActor(fied.getField());

            //field menu
            if (tmpField.getHappened()) {
                objectMain.getUIStage().addActor(tmpMenu);
                objectMain.getUIStage().addActor(tmpBackButton);

                if (tmpBackButton.getHappened()) {
                    closeMenu();
                }
            }
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
        drawFields();
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