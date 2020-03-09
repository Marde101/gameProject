package fi.tuni.game;

        import com.badlogic.gdx.ApplicationAdapter;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.maps.tiled.TiledMap;
        import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
        import com.badlogic.gdx.maps.tiled.TmxMapLoader;
        import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;

    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;

    int TILES_AMOUNT_WIDTH  = 40;
    int TILES_AMOUNT_HEIGHT = 20;

    int TILE_WIDTH          = 32;
    int TILE_HEIGHT         = 32;

    int TILE_HEIGHT_PIXELS = TILES_AMOUNT_HEIGHT * TILE_HEIGHT;
    int TILE_WIDTH_PIXELS  = TILES_AMOUNT_WIDTH  * TILE_WIDTH;

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;


    @Override
    public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        // Show always area of our world 8.00 x 4.80
        camera.setToOrtho(false,         // y points up
                WINDOW_WIDTH,            // width
                WINDOW_HEIGHT);          // height
        tiledMap = new TmxMapLoader().load("uus.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / 100f);

    }

    @Override
    public void render () {
        batch.setProjectionMatrix(camera.combined);
        clearScreen();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
        batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose () {
        batch.dispose();
        tiledMap.dispose();
    }
}
