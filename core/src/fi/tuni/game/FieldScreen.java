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
    private Texture fence;
    private Texture balanceBackground;

    private ArrayList<Fields> allFields;
    private Field field;
    private Fields fields;

    private boolean menuOpen = false;


    public FieldScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                    WINDOW_WIDTH*100,
                    WINDOW_HEIGHT*100);

        balanceBackground = new Texture("coin.png");
        fence = new Texture("fence.png");
        allFields = new ArrayList<>();
        generateFields();

    }

    private void generateFields() {
        generateField(0f,0f, "Field_1");
        generateField(0f,3.2f, "Field_2");
        generateField(4.266f,0f, "Field_3");
        generateField(4.266f,3.2f, "Field_4");
        generateField(8.532f,0f, "Field_5");
        generateField(8.532f,3.2f, "Field_6");
    }

    private void generateField(float posX, float posY, String key) {
        field = new Field(posX, posY);
        fields = new Fields(field, key);
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

        batch.begin();
        drawFields();
        if (!menuOpen) {
            batch.draw(fence, 0,0);
        }
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
        for(Fields tmpFields: allFields) {
            Field tmpField = tmpFields.getField();
            Menu tmpMenu = tmpFields.getMenu();
            BackButton tmpBackButton = tmpFields.getBackButton();
            ButtonBackground tmpContract = tmpFields.getContractButton();
            ButtonBackground tmpContract2 = tmpFields.getContractButton2();
            ButtonBackground tmpContract3 = tmpFields.getContractButton3();

            objectMain.getUIStage().addActor(tmpFields.getField());


            //field menu
            if (tmpField.getHappened()) {
                menuOpen = true;
                if (!tmpFields.getState()) {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                    objectMain.getUIStage().addActor(tmpContract);
                    objectMain.getFontSmall().draw(batch, "Vilja", 740, 440);
                    objectMain.getUIStage().addActor(tmpContract2);
                    objectMain.getFontSmall().draw(batch, "Kaali", 740, 340);
                    objectMain.getUIStage().addActor(tmpContract3);
                    objectMain.getFontSmall().draw(batch, "Sipuli", 740, 240);
                } else {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                }

                if (tmpContract.getHappened()) {
                    tmpFields.startProduction(0);
                    closeMenu();
                } else if (tmpContract2.getHappened()) {
                    tmpFields.startProduction(1);
                    closeMenu();
                } else if (tmpContract3.getHappened()) {
                    tmpFields.startProduction(2);
                    closeMenu();
                }

                if (tmpBackButton.getHappened()) {
                    closeMenu();
                }
            }

            if (tmpFields.getState()) {
                tmpFields.checkProduction(objectMain.getBalanceCash());
            }
        }
    }

    private void closeMenu() {
        for(Fields tmpFields: allFields) {
            Field tmpField = tmpFields.getField();
            Menu tmpMenu = tmpFields.getMenu();
            BackButton tmpBackButton = tmpFields.getBackButton();
            ButtonBackground tmpContract = tmpFields.getContractButton();
            ButtonBackground tmpContract2 = tmpFields.getContractButton2();
            ButtonBackground tmpContract3 = tmpFields.getContractButton3();

            tmpField.setHappened(false);
            tmpMenu.setHappened(false);
            tmpBackButton.setHappened(false);
            tmpContract.setHappened(false);
            tmpContract2.setHappened(false);
            tmpContract3.setHappened(false);
        }
        objectMain.getUIStage().clear();
        menuOpen = false;
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