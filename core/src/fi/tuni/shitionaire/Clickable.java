package fi.tuni.shitionaire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Clickable extends Actor {
    private Texture texture;
    private boolean happened = false;

    public Clickable() {
        texture = new Texture(Gdx.files.internal("sceneSwitch.png"));
        setWidth(1f);
        setHeight(1f);
        setBounds(11.7f,5.3f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
    }

    public boolean getHappened() {
        return this.happened;
    }

    public void setHappened(boolean x) {
        this.happened = x;
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
