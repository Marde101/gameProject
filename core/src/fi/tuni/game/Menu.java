package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Menu extends Clickable {
    private Texture texture;

    public Menu() {
        texture = new Texture(Gdx.files.internal("menu.png"));
        setWidth(8f);
        setHeight(5f);
        float posX = (12.8f-getWidth()) / 2;
        float posY = (6.4f-getHeight()) / 2;
        setBounds(posX,posY-0.5f, getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
