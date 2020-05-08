package fi.tuni.shitionaire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class RequestSound {

    static private Sound buttonClick = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));;
    static private Sound balanceSound = Gdx.audio.newSound(Gdx.files.internal("balanceSound.mp3"));;
    static private Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));;
    static private float musicVolume = 0.5f;
    static private float effectVolume = 0.5f;

    static public void playBackgroundMusic() {
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
        backgroundMusic.play();
    }

    static public void playButtonClick() {
        buttonClick.play(effectVolume);
    }

    static public void playBalanceSound() {
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
