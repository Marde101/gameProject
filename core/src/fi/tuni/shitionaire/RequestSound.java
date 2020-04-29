package fi.tuni.shitionaire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class RequestSound {

    static private Sound buttonClick;
    static private Sound balanceSound;
    static private Music backgroundMusic;
    static private float musicVolume = 0.5f;
    static private float effectVolume = 0.5f;

    static public void playBackgroundMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
        backgroundMusic.play();
    }

    static public void playButtonClick() {
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        buttonClick.play(effectVolume);
    }

    static public void playBalanceSound() {
        balanceSound = Gdx.audio.newSound(Gdx.files.internal("balanceSound.mp3"));
        balanceSound.play(effectVolume);
    }

    static public void setMusicVolume(float vol) {
        musicVolume = vol;
        backgroundMusic.setVolume(vol);
    }

    static public void setEffectVolume(float vol) {
        effectVolume = vol;
    }
}
