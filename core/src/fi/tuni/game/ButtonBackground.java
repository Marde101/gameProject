package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class ButtonBackground extends Clickable {
    private Texture texture;
    private boolean happened = false;

    public ButtonBackground(float x, float y) {
        texture = new Texture(Gdx.files.internal("menuButtonPlain.png"));
        setWidth(3f);
        setHeight(1f);
        float posX = x;
        float posY = y;
        setBounds(posX,posY-2.2f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                happened = true;
                return true;
            }
        });
    }

    public ButtonBackground(float x, float y, Texture t) {
        texture = t;
        setWidth(2.7f);
        setHeight(0.96f);
        float posX = x;
        float posY = y;
        setBounds(posX,posY-2.2f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                ButtonBackground.this.addAction(parallel);
                return true;
            }
        });
    }

    //used for flags
    public ButtonBackground(Texture t) {
        texture = t;
        setWidth(2f);
        setHeight(1f);
        float posX = (12.8f-getWidth()) / 2;
        float posY = (6.4f-getHeight()) / 2;
        setBounds(posX-2.2f,posY, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                ButtonBackground.this.addAction(parallel);
                return true;
            }
        });
    }

    //sound and volume
    public ButtonBackground(float posX, float posY, float width, float height, Texture t) {
        texture = t;
        setWidth(width);
        setHeight(height);
        setBounds(posX,posY, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                ButtonBackground.this.addAction(parallel);
                return true;
            }
        });
    }

    public void setTexture(Texture t) {
        texture = t;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }


}
