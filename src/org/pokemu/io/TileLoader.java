package org.pokemu.io;

import javafx.scene.image.Image;
import org.pokemu.Pokemon;
import org.pokemu.object.StaticTile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Loads tile sprites
 *          <p>
 *          TODO: Design a better system for this. Currently only using a basic array, ordering a tile sprite by it's index in the array
 */
public final class TileLoader {
    public static final int TILES = 1112;
    private static final Image[] tileset = new Image[TILES];
    public static final StaticTile[] STATICS = new StaticTile[12500];
    /**
     * Height of a tile on map
     */
    public static int TILE_HEIGHT_PIXELS = 32;
    /**
     * Width of a tile on map
     */
    public static int TILE_WIDTH_PIXELS = 32;

    public static boolean loadTiles() throws FileNotFoundException {
        File file = new File(Pokemon.WORKING_DIRECTORY + "data\\gfx\\tiles\\");
        try {
            File[] fileArray = file.listFiles();
            if (fileArray != null) {
                Arrays.sort(fileArray, new Comparator<File>() {
                    public int compare(File f1, File f2) {
                        try {
                            int i1 = Integer.parseInt(f1.getName().replace(".png", ""));
                            int i2 = Integer.parseInt(f2.getName().replace(".png", ""));
                            return i1 - i2;
                        } catch (NumberFormatException e) {
                            throw new AssertionError(e);
                        }
                    }
                });
                for (int i = 0; i < fileArray.length; i++) {
                    File f = fileArray[i];
                    Image x = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "data\\gfx\\tiles\\" + f.getName())));
                    tileset[i] = x;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Image getTile(int i) {
        return tileset[i];
    }
}
