package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class Toilet extends Actor {
    private Texture texture;
    public boolean happened = false;

    public Toilet(float x, float y) {
        texture = new Texture(Gdx.files.internal("sinihus.png"));
        setWidth(0.67f);
        setHeight(0.95f);
        float posX = x;
        float posY = y;
        setBounds(posX,posX, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                Toilet.this.addAction(parallel);
                return true;
            }
        });
    }

    public boolean getHappened() {
        return happened;
    }

    public void setHappened(boolean x) {
        happened = x;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}