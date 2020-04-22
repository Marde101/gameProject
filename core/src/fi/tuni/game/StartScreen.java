package fi.tuni.game;

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
    private Main objectMain;
    private OrthographicCamera camera;
    private Texture background;
    private ButtonBackground startButton;
    private ButtonBackground setButton;
    private boolean menuOpen = false;

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
        objectMain.getUIStage().addActor(setButton);
        objectMain.getUIStage().addActor(startButton);

        if (startButton.getHappened()) {
            RequestSound.playButtonClick();
            startButton.setHappened(false);
            objectMain.switchScene();
        }

        //texts
        batch.begin();
        if (!menuOpen) {
            objectMain.getFontBig().draw(batch, "Start", WINDOW_WIDTH*100/2-60, 410);
            objectMain.getFontBig().draw(batch, "Settings", WINDOW_WIDTH*100/2-95, 280);
        }

        if (setButton.getHappened()) {
            if (!menuOpen) {
                RequestSound.playButtonClick();
            }
            menuOpen = true;
            objectMain.getUIStage().addActor(objectMain.getSettings().getMenu());
            objectMain.getUIStage().addActor(objectMain.getSettings().getLanguage());
            objectMain.getFontSmall().draw(batch, "Language", 330, 420);
            objectMain.getUIStage().addActor(objectMain.getSettings().getEffects());
            objectMain.getFontSmall().draw(batch, "Effects", 650, 380);
            objectMain.getUIStage().addActor(objectMain.getSettings().getMusic());
            objectMain.getFontSmall().draw(batch, "Music", 650, 260);
            objectMain.getUIStage().addActor(objectMain.getSettings().getBackButton());
            objectMain.getFontBig().draw(batch, "BACK",575, 125);

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
        batch.end();
    }

    private void closeMenu() {
        RequestSound.playButtonClick();
        setButton.setHappened(false);
        startButton.setHappened(false);
        objectMain.getSettings().setHappened(false);
        objectMain.getSettings().getMenu().setHappened(false);
        objectMain.getSettings().getBackButton().setHappened(false);
        objectMain.getSettings().getLanguage().setHappened(false);
        objectMain.getUIStage().clear();
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
