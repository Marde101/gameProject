package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class Toilet extends Clickable {
    private Texture texture;
    private boolean happened = false;
    final private float standardWidth = 0.66f;
    final private float standardHeight = 0.94f;
    private int counter = 1;
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
                ParallelAction parallel = new ParallelAction();
                Toilet.this.addAction(parallel);
                return true;
            }
        });
    }

    public void bounce() {
        float width = standardWidth + counter/100;
        float height = standardHeight + counter/100;
        counter++;
        if (counter > 30) {
            counter = 1;
        }
        setWidth(width);
        setHeight(height);
        setBounds(posX, posY, getWidth(), getHeight());
    }

    public void setToiletTexture(Texture t) {
        texture = t;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
