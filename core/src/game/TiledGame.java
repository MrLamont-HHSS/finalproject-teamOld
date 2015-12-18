package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.itsjafer.model.World;
import com.itsjafer.screens.View;

public class TiledGame extends ApplicationAdapter {

    private View view;
    private World world;
    private InputManager inputManager;
    
    @Override
    public void create() {
        GameLoader.loadGame();
        
        view = new View();
        world = new World();
        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
//        batch = new SpriteBatch();
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(16, 12, camera);
//
//        camera.update();
//
//        map = new TmxMapLoader().load("map.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map, 1f / PPU, batch);
//
//        // get the dimensions of the map
//        int mapWidth = map.getProperties().get("width", Integer.class);
//        int mapHeight = map.getProperties().get("height", Integer.class);
//        int tileWidth = map.getProperties().get("tilewidth", Integer.class) / PPU;
//        int tileHeight = map.getProperties().get("tileheight", Integer.class) / PPU;
//
//        levelWidth = mapWidth;
//        levelHeight = mapHeight;
//        
//        // create the collision rectangles for our map based on the map layer
//        collisionBlocks = new Array<Rectangle>();
//        // get collision layer
//        TiledMapTileLayer solidBlocks = (TiledMapTileLayer) map.getLayers().get("Collision");
//
//        // loop through all of the cells, find a tile and make a rectangle
//        for (int x = 0; x < mapWidth; x++) {
//            for (int y = 0; y < mapHeight; y++) {
//                if (solidBlocks.getCell(x, y) != null) {
//                    Rectangle r = new Rectangle(x, y, tileWidth, tileHeight);
//                    collisionBlocks.add(r);
//                }
//            }
//        }
    }

    @Override
    public void render() {
        
        world.update();
        view.render(world);
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
