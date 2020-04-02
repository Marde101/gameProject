package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class Menu extends Actor {
    private Texture texture;
    public boolean happened = false;

    public Menu() {
        texture = new Texture(Gdx.files.internal("menu.png"));
        setWidth(8f);
        setHeight(5f);
        float posX = (12.8f-getWidth()) / 2;
        float posY = (6.4f-getHeight()) / 2;
        setBounds(posX,posY-0.5f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                Menu.this.addAction(parallel);
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
