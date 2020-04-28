package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Tutorial extends Clickable {


    private Texture tuto1 = new Texture(Gdx.files.internal("huussinAvaus.png"));
    // finnish
    private Texture tuto2f = new Texture(Gdx.files.internal("openKuivis.png"));
    private Texture tuto3f = new Texture(Gdx.files.internal("kakpisCollect.png"));
    private Texture tuto4f = new Texture(Gdx.files.internal("switchScenes.png"));
    private Texture tuto5f = new Texture(Gdx.files.internal("fieldClick.png"));
    private Texture tuto6f = new Texture(Gdx.files.internal("pissaaPeltoon.png"));
    private Texture tuto7f = new Texture(Gdx.files.internal("fieldFilled.png"));
    // enlgish
    private Texture tuto2e = new Texture(Gdx.files.internal("openToilet.png"));
    private Texture tuto3e = new Texture(Gdx.files.internal("peepooCollect.png"));
    private Texture tuto4e = new Texture(Gdx.files.internal("switchScenes.png"));
    private Texture tuto5e = new Texture(Gdx.files.internal("fieldClick.png"));
    private Texture tuto6e = new Texture(Gdx.files.internal("peeToField.png"));
    private Texture tuto7e = new Texture(Gdx.files.internal("fieldFilled.png"));

    private boolean happened;
    private Texture current;
    private int count;

    public Tutorial() {
        current = tuto1;
        count = 1;
        setWidth(12.8f);
        setHeight(6.4f);
        setBounds(0f,0f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
    }

    public void nextTextureFin() {
        if(current==tuto1) {
            current = tuto2f;
        } else if (current==tuto2f) {
            current = tuto3f;
        }  else if (current==tuto3f) {
            current = tuto4f;
        } else if (current==tuto4f) {
            current = tuto5f;
        } else if (current==tuto5f) {
            current = tuto6f;
        } else if (current==tuto6f) {
            current = tuto7f;
        }
        count++;
    }

    public void nextTextureEng() {
        if(current==tuto1) {
            current = tuto2e;
        } else if (current==tuto2e) {
            current = tuto3e;
        }  else if (current==tuto3e) {
            current = tuto4e;
        } else if (current==tuto4e) {
            current = tuto5e;
        } else if (current==tuto5e) {
            current = tuto6e;
        } else if (current==tuto6e) {
            current = tuto7e;
        }
        count++;
    }

    public int getCount() {
        return count;
    }
    public void resetCount() {
        current=tuto1;
        count = 1;
    }

    public boolean getHappened() {
        return happened;
    }

    public void setHappened(boolean x) {
        happened = x;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }
}
