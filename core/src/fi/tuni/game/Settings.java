package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Settings extends Clickable {
    private Texture texture;
    private boolean happened = false;
    private Menu menu;
    private BackButton backButton;
    private ButtonBackground language;
    private Texture finnish;
    private Texture english;
    private boolean fin = true;


    public Settings() {
        texture = new Texture(Gdx.files.internal("settingsButton.png"));
        finnish = new Texture(Gdx.files.internal("englishFlag.png"));
        english = new Texture(Gdx.files.internal("finnishFlag.png"));
        setWidth(0.8f);
        setHeight(0.8f);
        setBounds(0.2f,5.5f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
        language = new ButtonBackground(finnish);
        menu = new Menu();
        backButton = new BackButton();
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Menu getMenu() {
        return menu;
    }
    public BackButton getBackButton() {
        return backButton;
    }
    public ButtonBackground getLanguage() {
        return language;
    }
    public void changeLanguage() {
        if (fin) {
            language.setTexture(english);
            fin = false;
        } else {
            language.setTexture(finnish);
            fin = true;
        }
    }
}
