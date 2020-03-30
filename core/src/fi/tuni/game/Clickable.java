package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class Clickable extends Actor {
    private Texture texture;
    public boolean happened = false;

    public Clickable() {
        texture = new Texture(Gdx.files.internal("sceneSwitch.png"));
        setWidth(0.8f);
        setHeight(0.8f);
        setBounds(11.9f,5.5f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("here");
                happened = true;
                ParallelAction parallel = new ParallelAction();
                Clickable.this.addAction(parallel);
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
