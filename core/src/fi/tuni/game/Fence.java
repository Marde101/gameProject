package fi.tuni.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Fence extends Clickable {
    private Texture texture;

    public Fence() {
        texture = new Texture("fenceFix.png");
        setWidth(12.8f);
        setHeight(6.4f);
        setBounds(0f,0f, getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
