package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class FieldScreen implements Screen {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera fontCamera;
    private Texture fence;
    private Texture balancePee;
    private Texture balancePoo;
    private Field field;
    private Fields fields;
    private ArrayList<Fields> allFields;
    private ArrayList<Toilets> allToilets;
    private boolean infoFetched = false;
    private boolean menuOpen = false;
    private String which = "";

    public FieldScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                    WINDOW_WIDTH*100,
                    WINDOW_HEIGHT*100);
        balancePee = new Texture("peeMoney.png");
        balancePoo = new Texture("pooMani.png");
        fence = new Texture("fenceFix.png");
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MemoryWriter.writeCurrentTimestamp();

        //fontCamera
        batch.setProjectionMatrix(fontCamera.combined);
        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();

        batch.begin();
        drawFields();
        if (!infoFetched) {
            allToilets = objectMain.getToilets();
            infoFetched = true;
        }
        checkToiletsProduct();
        if (!menuOpen) {
            batch.draw(fence, 0,0);
        }
        //balances
        objectMain.getFontBig().draw(batch, objectMain.getBalancePee().getValueToString(), 825, 615);
        batch.draw(balancePee, 740, 555);
        objectMain.getFontBig().draw(batch, objectMain.getBalancePoo().getValueToString(), 325, 615);
        batch.draw(balancePoo, 240, 555);
        batch.end();


        objectMain.getUIStage().addActor(objectMain.getSceneSwitch());
        objectMain.getUIStage().addActor(objectMain.getSettings());
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        //sceneswitch function
        if (objectMain.getSceneSwitch().getHappened()) {
            closeMenu();
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);
        }
        //settings function
        if (objectMain.getSettings().getHappened() && !menuOpen) {
            menuOpen = true;
            objectMain.getUIStage().addActor(objectMain.getSettings().getMenu());
            objectMain.getUIStage().addActor(objectMain.getSettings().getBackButton());
            if (Gdx.input.isKeyPressed(Input.Keys.BACK)
                    || objectMain.getSettings().getBackButton().getHappened()) {
                closeMenu();
            }
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
            if (tmpField.getHappened() && (!menuOpen || which.equals(tmpFields.getKey()))) {
                which = tmpFields.getKey();
                menuOpen = true;
                if (!tmpFields.getState()) {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                    objectMain.getFontBig().draw(batch, "BACK",575, 125);
                    objectMain.getUIStage().addActor(tmpContract);
                    objectMain.getFontSmall().draw(batch, tmpFields.getPrice(1), 825, 440);
                    objectMain.getFontSmallest().draw(batch, "Kasvata viljaa. Rahaa saat vähiten.\n" +
                            "Kasvamiseen menee aikaa vähiten.",265, 460);
                    objectMain.getUIStage().addActor(tmpContract2);
                    objectMain.getFontSmall().draw(batch, tmpFields.getPrice(2), 825, 340);
                    objectMain.getFontSmallest().draw(batch, "Kasvata kaalia. Rahaa saat keskiverrosti.\n" +
                            "Kasvamiseen menee aikaa keskiverrosti.",265, 360);
                    objectMain.getUIStage().addActor(tmpContract3);
                    objectMain.getFontSmall().draw(batch, tmpFields.getPrice(3), 825, 240);
                    objectMain.getFontSmallest().draw(batch, "Kasvata sipuleita. Rahaa saat eniten.\n" +
                            "Kasvamiseen menee aikaa kauiten.",265, 260);
                } else {
                    objectMain.getUIStage().addActor(tmpMenu);
                    objectMain.getUIStage().addActor(tmpBackButton);
                    objectMain.getFontBig().draw(batch, "BACK",575, 125);
                    if (tmpFields.getState()) {
                        objectMain.getFontBig().draw(batch, tmpFields.getTimeLeftString(),600, 300);
                    }
                }

                if (tmpContract.getHappened()) {
                    if (objectMain.getBalancePee().getValue() >= Integer.parseInt(tmpFields.getPrice(1))) {
                        objectMain.getBalancePee().removeValue(Integer.parseInt(tmpFields.getPrice(1)));
                        tmpFields.startProduction(1);
                        closeMenu();
                    }
                }
                if (tmpContract2.getHappened()) {
                    if (objectMain.getBalancePee().getValue() >= Integer.parseInt(tmpFields.getPrice(2))) {
                        objectMain.getBalancePee().removeValue(Integer.parseInt(tmpFields.getPrice(2)));
                        tmpFields.startProduction(2);
                        closeMenu();
                    }
                }
                if (tmpContract3.getHappened()) {
                    if (objectMain.getBalancePoo().getValue() >= Integer.parseInt(tmpFields.getPrice(3))) {
                        objectMain.getBalancePoo().removeValue(Integer.parseInt(tmpFields.getPrice(3)));
                        tmpFields.startProduction(3);
                        closeMenu();
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.BACK)
                        || tmpBackButton.getHappened()){
                    closeMenu();
                }
            } else if (tmpField.getHappened()){
                closeMenu();
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
        objectMain.getSettings().setHappened(false);
        objectMain.getSettings().getMenu().setHappened(false);
        objectMain.getSettings().getBackButton().setHappened(false);
        objectMain.getSettings().setHappened(false);
        objectMain.getUIStage().clear();
        drawFields();
        menuOpen = false;
        which = "";
    }

    public ArrayList getAllFields() {
        return allFields;
    }

    private void checkToiletsProduct() {
        for(Toilets tmpToilets: allToilets) {
            if (tmpToilets.getState()) {
                tmpToilets.checkProduction(objectMain.getBalancePee(), objectMain.getBalancePoo());
            }
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