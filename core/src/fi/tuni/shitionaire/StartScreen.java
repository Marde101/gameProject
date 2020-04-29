package fi.tuni.shitionaire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen implements Screen {

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private fi.tuni.shitionaire.Main objectMain;
    private OrthographicCamera camera;
    private Texture background;
    private ButtonBackground startButton;
    private ButtonBackground setButton;
    private ButtonBackground tutorButton;
    private boolean menuOpen = false;
    private fi.tuni.shitionaire.Tutorial tutorial;
    private boolean tutOpen;
    private boolean firstLaunch;
    private boolean languageSelected;

    public StartScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_HEIGHT*100);
        background = new Texture(Gdx.files.internal("menuBg.png"));
        startButton = new ButtonBackground((12.8f-3) / 2, 5.6f);
        setButton = new ButtonBackground((12.8f-3) / 2, 4.3f);
        tutorButton = new ButtonBackground(9f, 3f);
        tutorial = new Tutorial();
        tutOpen = false;
        languageSelected = false;
        firstLaunch = false;
        languageSelected = fi.tuni.shitionaire.MemoryReader.readFirstLaunch();
        firstLaunch = MemoryReader.readFirstLaunch();
        fi.tuni.shitionaire.MemoryWriter.writeFirstLaunch(firstLaunch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        //stage
        objectMain.getUIStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getUIStage().draw();
        Gdx.input.setInputProcessor(objectMain.getUIStage());
        if (firstLaunch) {
            objectMain.getUIStage().addActor(setButton);
            objectMain.getUIStage().addActor(startButton);
            objectMain.getUIStage().addActor(tutorButton);
        }

        if (startButton.getHappened()) {
            fi.tuni.shitionaire.RequestSound.playButtonClick();
            startButton.setHappened(false);
            objectMain.switchScene();
        }

        //texts
        batch.begin();
        if (!menuOpen && !tutOpen && languageSelected) {
            if (objectMain.getSettings().getEng()) {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("title"), WINDOW_WIDTH*100/2-60, 410);
            } else {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("title"), WINDOW_WIDTH*100/2-70, 410);
            }
            if (objectMain.getSettings().getEng()) {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("settings"), WINDOW_WIDTH*100/2-100, 280);
            } else {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("settings"), WINDOW_WIDTH*100/2-120, 280);
            }
            if (objectMain.getSettings().getEng()) {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("tutorial"),950, 150);
            } else {
                objectMain.getFontBig().draw(batch, objectMain.getBundle().get("tutorial"),960, 150);
            }
        }

        if (setButton.getHappened() && !tutOpen) {
            if (!menuOpen) {
                fi.tuni.shitionaire.RequestSound.playButtonClick();
            }
            menuOpen = true;
            objectMain.getUIStage().addActor(objectMain.getSettings().getMenu());
            objectMain.getUIStage().addActor(objectMain.getSettings().getLanguage());
            objectMain.getUIStage().addActor(objectMain.getSettings().getEffects());
            objectMain.getUIStage().addActor(objectMain.getSettings().getMusic());
            objectMain.getUIStage().addActor(objectMain.getSettings().getBackButton());
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("language"), 330, 420);
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("effects"), 650, 380);
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("music"), 650, 260);
            objectMain.getFontBig().draw(batch, objectMain.getBundle().get("back"),575, 125);

            if (objectMain.getSettings().getEffects().getHappened()) {
                objectMain.getSettings().toggleEffects();
                objectMain.getSettings().getEffects().setHappened(false);
            }

            if (objectMain.getSettings().getMusic().getHappened()) {
                objectMain.getSettings().toggleMusics();
                objectMain.getSettings().getMusic().setHappened(false);
            }


            if (objectMain.getSettings().getLanguage().getHappened()) {
                objectMain.getSettings().changeLanguage();
                objectMain.getSettings().getLanguage().setHappened(false);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.BACK)
                    || objectMain.getSettings().getBackButton().getHappened()) {
                closeMenu();
            }
        }
        if (tutorButton.getHappened() || !firstLaunch) {
            if (languageSelected) {
                runTutorial();
            } else {
                objectMain.getFontBig().draw(batch, "Suomi          English",370, 470);
                objectMain.getUIStage().addActor(objectMain.getSettings().getSuomi());
                objectMain.getUIStage().addActor(objectMain.getSettings().getEnglish());
                if (objectMain.getSettings().getSuomi().getHappened()) {
                    objectMain.getSettings().setEng(false);
                    languageSelected = true;
                } else if (objectMain.getSettings().getEnglish().getHappened()) {
                    objectMain.getSettings().setEng(true);
                    languageSelected = true;
                }
            }
        }
        batch.end();
    }

    private void runTutorial() {
        tutOpen=true;
        //english tutorial
        if (objectMain.getSettings().getEng()) {
            objectMain.getUIStage().addActor(tutorial);
            if (tutorial.getHappened()) {
                tutorial.nextTextureEng();
                tutorial.setHappened(false);
            }
        } else { // finnish tutorial
            objectMain.getUIStage().addActor(tutorial);
            if (tutorial.getHappened()) {
                tutorial.nextTextureFin();
                tutorial.setHappened(false);
            }
        }
        tutorialTexts();
        if (tutorial.getCount()==8) {
            firstLaunch = true;
            MemoryWriter.writeFirstLaunch(firstLaunch);
            closeMenu();
        }
    }

    private void tutorialTexts() {
        if (tutorial.getCount()==1) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto1"),400, 550);
        } else if (tutorial.getCount()==2) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto2"),400, 550);
        } else if (tutorial.getCount()==3) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto3"),400, 550);
        } else if (tutorial.getCount()==4) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto4"),400, 550);
        } else if (tutorial.getCount()==5) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto5"),400, 550);
        } else if (tutorial.getCount()==6) {
            objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("tuto6"),400, 550);
        }

        objectMain.getFontSmall().draw(batch, objectMain.getBundle().get("move"),300, 50);
    }

    private void closeMenu() {
        RequestSound.playButtonClick();
        setButton.setHappened(false);
        startButton.setHappened(false);
        objectMain.getSettings().setHappened(false);
        objectMain.getSettings().getMenu().setHappened(false);
        objectMain.getSettings().getBackButton().setHappened(false);
        objectMain.getSettings().getLanguage().setHappened(false);
        tutorButton.setHappened(false);
        tutorial.resetCount();
        objectMain.getUIStage().clear();
        tutOpen = false;
        menuOpen = false;
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
