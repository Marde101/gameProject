package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Settings extends Clickable {
    private Texture texture;
    private boolean happened = false;

    public Settings() {
        texture = new Texture(Gdx.files.internal("settingsButton.png"));
        setWidth(0.8f);
        setHeight(0.8f);
        float posX = (12.8f-getWidth()) / 2;
        float posY = (6.4f-getHeight()) / 2;
        setBounds(0.2f,5.5f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
