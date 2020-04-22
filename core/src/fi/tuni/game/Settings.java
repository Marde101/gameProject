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
    private ButtonBackground effects;
    private ButtonBackground music;
    private Texture finnish;
    private Texture english;
    private Texture soundOn;
    private Texture musicOn;
    private Texture soundOff;
    private Texture musicOff;
    private boolean fin;
    private boolean effectToggle;
    private boolean musicToggle;

    public Settings() {
        texture = new Texture(Gdx.files.internal("settingsButton.png"));

        finnish = new Texture(Gdx.files.internal("englishFlag.png"));
        english = new Texture(Gdx.files.internal("finnishFlag.png"));

        soundOn = new Texture(Gdx.files.internal("englishFlag.png"));
        soundOff = new Texture(Gdx.files.internal("finnishFlag.png"));

        musicOn = new Texture(Gdx.files.internal("englishFlag.png"));
        musicOff = new Texture(Gdx.files.internal("finnishFlag.png"));

        setWidth(0.8f);
        setHeight(0.8f);
        setBounds(0.2f,5.5f, getWidth(), getHeight());
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                happened = true;
                return true;
            }
        });
        effects = new ButtonBackground(8f, 3.2f, 1f, 1f, soundOn);
        music = new ButtonBackground(8f, 2f, 1f, 1f, musicOn);
        language = new ButtonBackground(finnish);
        menu = new Menu();
        backButton = new BackButton();
        fin = MemoryReader.readLang();
        fetchVolumes();
    }

    public void changeLanguage() {
        if (fin) {
            language.setTexture(english);
            fin = false;
        } else {
            language.setTexture(musicOn);
            fin = true;
        }
        MemoryWriter.writeLang(fin);
    }

    public void toggleMusics() {
        if (musicToggle) {
            RequestSound.setMusicVolume(0f);
            music.setTexture(musicOff);
            musicToggle = false;
        } else {
            RequestSound.setMusicVolume(0.5f);
            music.setTexture(musicOn);
            musicToggle = true;
        }
        MemoryWriter.writeVolume("Music", musicToggle);
    }

    public void toggleEffects() {
        if (effectToggle) {
            RequestSound.setEffectVolume(0f);
            effects.setTexture(soundOff);
            effectToggle = false;
        } else {
            RequestSound.setEffectVolume(0.5f);
            effects.setTexture(soundOn);
            effectToggle = true;
        }
        MemoryWriter.writeVolume("Effect", effectToggle);

    }

    private void fetchVolumes() {
        musicToggle = MemoryReader.readVolume("Music");
        effectToggle = MemoryReader.readVolume("Effect");
        if (musicToggle) {
            RequestSound.setMusicVolume(0.5f);
            music.setTexture(musicOn);
        } else {
            RequestSound.setMusicVolume(0f);
            music.setTexture(musicOff);
        }
        if (effectToggle) {
            RequestSound.setEffectVolume(0.5f);
            effects.setTexture(soundOn);
        } else {
            RequestSound.setEffectVolume(0f);
            effects.setTexture(soundOff);
        }
    }

    public ButtonBackground getEffects(){
        return effects;
    }
    public ButtonBackground getMusic(){
        return music;
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

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
