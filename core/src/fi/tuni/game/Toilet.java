package fi.tuni.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Toilet extends Clickable {
    private Texture texture;
    private boolean happened = false;
    final private float standardWidth = 0.66f;
    final private float standardHeight = 0.94f;
    float posX;
    float posY;

    public Toilet(float x, float y) {
        setWidth(standardWidth);
        setHeight(standardHeight);
        posX = x;
        posY = y;
        setBounds(posX, posY, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
    }

    public void setToiletTexture(Texture t) {
        texture = t;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
