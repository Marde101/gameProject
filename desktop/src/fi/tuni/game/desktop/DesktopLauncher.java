package fi.tuni.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tuni.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		// windows size is (pixels * tiles)
        config.width = 1280;
        config.height = 640;
        //config.width = 1800;
        //config.height = 900;

		new LwjglApplication(new Main(), config);
	}
}
