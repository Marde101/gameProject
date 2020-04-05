package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class Field extends Clickable  {
    private Texture texture;
    private boolean happened = false;

    public Field(float x, float y) {
        texture = new Texture(Gdx.files.internal("textureField.png"));
        setWidth(4.266f);
        setHeight(3.2f);
        float posX = x;
        float posY = y;
        setBounds(posX, posY, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                ParallelAction parallel = new ParallelAction();
                Field.this.addAction(parallel);
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
