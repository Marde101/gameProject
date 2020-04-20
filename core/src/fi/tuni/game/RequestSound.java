package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class RequestSound {

    static private Sound buttonClick;
    static private Sound balanceSound;
    static private Music backgroundMusic;

    static public void playBackgroundMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();
    }

    static public void playButtonClick() {
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        buttonClick.play(0.1f);
    }

    static public void playBalanceSound() {
        balanceSound = Gdx.audio.newSound(Gdx.files.internal("balanceSound.mp3"));
        balanceSound.play(0.1f);
    }
}
