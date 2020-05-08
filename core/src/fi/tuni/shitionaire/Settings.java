package fi.tuni.shitionaire;

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
    private boolean eng;
    private boolean effectToggle;
    private boolean musicToggle;


    private ButtonBackground suomi;
    private ButtonBackground enlgish;


    public Settings() {
        texture = new Texture(Gdx.files.internal("menuButton.png"));

        english = new Texture(Gdx.files.internal("englishFlag.png"));
        finnish = new Texture(Gdx.files.internal("finnishFlag.png"));

        soundOn = new Texture(Gdx.files.internal("soundOn.png"));
        soundOff = new Texture(Gdx.files.internal("soundOff.png"));

        musicOn = new Texture(Gdx.files.internal("soundOn.png"));
        musicOff = new Texture(Gdx.files.internal("soundOff.png"));

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
        language = new ButtonBackground(finnish, 3.2f, 2.7f);
        suomi = new ButtonBackground(finnish, 3.6f, 3f);
        enlgish = new ButtonBackground(english, 6.6f, 3f);
        menu = new Menu();
        backButton = new BackButton();
        fetchSettings();
    }

    public void changeLanguage() {
        if (eng) {
            language.setTexture(finnish);
            eng = false;
        } else if (!eng) {
            language.setTexture(english);
            eng = true;
        }
        MemoryWriter.writeLang(eng);
    }

    public void toggleMusics() {
        if (!musicToggle) {
            RequestSound.setMusicVolume(0f);
            music.setTexture(musicOff);
            musicToggle = true;
        } else {
            RequestSound.setMusicVolume(0.5f);
            music.setTexture(musicOn);
            musicToggle = false;
        }
        MemoryWriter.writeVolume("Music", musicToggle);
    }

    public void toggleEffects() {
        if (!effectToggle) {
            RequestSound.setEffectVolume(0f);
            effects.setTexture(soundOff);
            effectToggle = true;
        } else {
            RequestSound.setEffectVolume(1f);
            effects.setTexture(soundOn);
            effectToggle = false;
        }
        MemoryWriter.writeVolume("Effect", effectToggle);

    }

    private void fetchSettings() {
        musicToggle = fi.tuni.shitionaire.MemoryReader.readVolume("Music");
        effectToggle = fi.tuni.shitionaire.MemoryReader.readVolume("Effect");
        eng = MemoryReader.readLang();
        if (!eng) {
            language.setTexture(finnish);
        } else {
            language.setTexture(english);
        }
        if (!musicToggle) {
            RequestSound.setMusicVolume(0.5f);
            music.setTexture(musicOn);
        } else {
            RequestSound.setMusicVolume(0f);
            music.setTexture(musicOff);
        }
        if (!effectToggle) {
            RequestSound.setEffectVolume(1f);
            effects.setTexture(soundOn);
        } else {
            RequestSound.setEffectVolume(0f);
            effects.setTexture(soundOff);
        }
        MemoryWriter.writeVolume("Music", musicToggle);
        MemoryWriter.writeVolume("Effect", effectToggle);
        MemoryWriter.writeLang(eng);
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
    public void setEng(boolean x) {
        eng = x;
        MemoryWriter.writeLang(eng);
    }
    public boolean getEng() {
        return eng;
    }
    public ButtonBackground getSuomi() {
        return suomi;
    }
    public ButtonBackground getEnglish() {
        return enlgish;
    }
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
