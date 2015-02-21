package org.pokemu.screen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import org.pokemu.Pokemon;
import org.pokemu.io.MapLoader;
import org.pokemu.io.NPCLoader;
import org.pokemu.io.TileLoader;
import org.pokemu.listener.GameScreenKeyListener;
import org.pokemu.object.NPC;
import org.pokemu.object.PokemonTrainer;
import org.pokemu.object.StaticTile;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class GameScreen extends Screen {
    private static final Affine AFFINE = new Affine();
    private final PokemonTrainer trainer;
    private final Stage stage;
    public static final int PLAYER_SCREEN_X = 224; //Multiple of 32
    public static final int PLAYER_SCREEN_Y = 118; //-10 because height is 42, not 32.
    private int x_coor = 0;
    private int y_coor = 0;
    private int tile_number = 0;
    private boolean tilesLoaded;
    private final Pokemon pokemon;

    public GameScreen(Pokemon pokemon) {
        super(pokemon);
        this.pokemon = pokemon;
        stage = pokemon.getStage();
        setFocusTraversable(true);
        setOnKeyPressed(new GameScreenKeyListener(pokemon));
        trainer = pokemon.getTrainer();
    }

    @Override
    public final void paint() {
        GraphicsContext g = getGraphicsContext2D();
        if (trainer.isRepaint()) {
            g.clearRect(0, 0, Pokemon.WIDTH, Pokemon.HEIGHT);
            trainer.setRepaint(false);
        }
        if (!pokemon.isPaused()) {
            repaintGame();
        }
    }

    public final void repaintGame() {
        GraphicsContext g = getGraphicsContext2D();
        g.translate((trainer.getOffsetX() - (trainer.getCurrentX() * 32)), (trainer.getOffsetY() - (trainer.getCurrentY() * 32)));
        for (int y = 1; y <= MapLoader.mapTilesY; y++) {
            for (int x = 1; x <= MapLoader.mapTilesX; x++) {
                //Layer 0
                if (MapLoader.currentMap0[tile_number] != 0) {
                    g.drawImage(TileLoader.getTile(MapLoader.currentMap0[tile_number] - 1), x_coor, y_coor);
                }
                //Layer 1
                if (MapLoader.currentMap1[tile_number] != 0) {
                    g.drawImage(TileLoader.getTile(MapLoader.currentMap1[tile_number] - 1), x_coor, y_coor);
                }
                //Impassible Tiles
                if (!tilesLoaded) {
                    for (int i = 0; i < MapLoader.IMPASSABLE.length; i++) {
                        if (MapLoader.currentMap0[tile_number] == MapLoader.IMPASSABLE[i] || MapLoader.currentMap1[tile_number] == MapLoader.IMPASSABLE[i]) {
                            TileLoader.STATICS[tile_number] = new StaticTile(x_coor / 32, y_coor / 32, null);
                        }
                    }
                }
                x_coor = x_coor + 32;
                tile_number = tile_number + 1;
            }
            x_coor = 0;
            y_coor = y_coor + 32;
        }
        tilesLoaded = true;

        /*
         * Paint our NPCs
         */
        for (NPC n : NPCLoader.npcs) {
            g.drawImage(n.getSprite(), n.getX() * TileLoader.TILE_WIDTH_PIXELS, n.getY() * TileLoader.TILE_HEIGHT_PIXELS - 10);
        }
        tile_number = 0;
        x_coor = 0;
        y_coor = 0;

        /*
         * Draw our player sprite
         */
        g.setTransform(AFFINE);
        g.drawImage(trainer.getSprite(), PLAYER_SCREEN_X, PLAYER_SCREEN_Y);
        if (trainer.isEngagedNPC()) {
            drawMessage(trainer.getEngaged().getText());
        }
        stage.setTitle("Pokemu (" + trainer.getX() + "," + trainer.getY() + ")");
    }
}





