package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

    public StartScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_HEIGHT*100);
        background = new Texture(Gdx.files.internal("menuBG.png"));
        startButton = new ButtonBackground(5.6f);
        setButton = new ButtonBackground(4.3f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
            objectMain.switchScene();
        }
        if (setButton.getHappened()) {
            //open setting
        }
        //texts
        batch.begin();
        objectMain.getFont().draw(batch, "Start", WINDOW_WIDTH*100/2-60, 410);
        objectMain.getFont().draw(batch, "Settings", WINDOW_WIDTH*100/2-95, 280);
        batch.end();
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
