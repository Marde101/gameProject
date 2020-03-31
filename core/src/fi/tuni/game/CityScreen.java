package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CityScreen implements Screen {


    private final float WINDOW_WIDTH = 12.8f;
    private final float WINDOW_HEIGHT = 6.4f;
    private SpriteBatch batch;
    private Main objectMain;
    private OrthographicCamera camera;
    private OrthographicCamera fontCamera;
    private TiledMap cityTiledMap;
    private TiledMapRenderer cityTiledMapRenderer;

    private Texture balanceBackground;

    public CityScreen(Main x) {
        batch = x.getBatch();
        objectMain = x;
        camera = new OrthographicCamera();
        fontCamera = new OrthographicCamera();
        fontCamera.setToOrtho(false,
                WINDOW_WIDTH*100,
                WINDOW_HEIGHT*100);
        // Show always area of our world 8.00 x 4.80
        camera.setToOrtho(false,         // y points up
                WINDOW_WIDTH,            // width
                WINDOW_HEIGHT);          // height
        cityTiledMap = new TmxMapLoader().load("siti.tmx");
        cityTiledMapRenderer = new OrthogonalTiledMapRenderer(cityTiledMap, 1 / 100f);

        balanceBackground = new Texture("coin.png");



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cityTiledMapRenderer.setView(camera);
        cityTiledMapRenderer.render();

        if (Gdx.input.justTouched()) {
            checkClick(Gdx.input.getX(), Gdx.input.getY());
        }

        //rahamäärä
        batch.setProjectionMatrix(fontCamera.combined);
        batch.begin();
        objectMain.getFont().draw(batch, Currency.getStringBalance(), 825, 615);
        batch.draw(balanceBackground, 740, 555);
        batch.end();

        //scenenvaihto nappi
        objectMain.getStage().act(Gdx.graphics.getDeltaTime());
        objectMain.getStage().draw();

        //toiminnallisuus nappiin
        Gdx.input.setInputProcessor(objectMain.getStage());
        if (objectMain.getSceneSwitch().getHappened()) {
            objectMain.switchScene();
            objectMain.getSceneSwitch().setHappened(false);
        }



    }

    private void checkClick(float posX, float posY) {

        // Let's get the collectable rectangles layer
        MapLayer objectLayer = (MapLayer)cityTiledMap.getLayers().get("object");

        //TiledMapTileLayer collectableCells = (TiledMapTileLayer) cityTiledMap.getLayers().get("huussi");
        //System.out.println(collectableXPos + " " + collectableYPos);
        //collectableCells.getCell(collectableXPos, collectableYPos).setTile(null);
        //collectableCells.getCell(collectableXPos, collectableYPos + 1).setTile(null);

        // All the rectangles of the layer
        MapObjects mapObjects = objectLayer.getObjects();
        // Cast it to RectangleObjects array
        Array<RectangleMapObject> rectangleObjects = mapObjects.getByType(RectangleMapObject.class);

        float width;
        float height;
        posY = WINDOW_HEIGHT*100-posY;

        // Iterate all the rectangles
        for (RectangleMapObject rectangleObject : rectangleObjects) {
            Rectangle tmp = rectangleObject.getRectangle();

            width = tmp.getX() + tmp.getWidth();
            height = tmp.getY() + tmp.getHeight();

            if (tmp.getX() < posX && width > posX
                        && tmp.getY() < posY && height > posY) {
                System.out.println("hit");
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
