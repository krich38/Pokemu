package org.pokemu.io;

import org.pokemu.Pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Map Loader.
 *          <p>
 *          The map is stored as a square.
 *          <p>
 *          TODO: Implement an OO management system, this system is terrible but I ran out of time on the project
 */
public final class MapLoader {
    /**
     * IDs of all tiles that the player cannot pass through
     */
    public static final int[] IMPASSABLE = new int[] {3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 40, 41, 42, 48, 49, 50, 51, 52, 56, 57, 58, 59, 60, 61, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 251, 252, 253, 296, 471, 477, 478, 479, 486, 494, 495, 501, 503, 509, 808, 809, 810, 811, 812, 813, 816, 817, 818, 819, 820, 821, 824, 825, 861, 864, 870, 871, 872, 880, 888, 890, 896, 914, 922, 728, 729, 730, 731, 732, 733, 736, 737, 738, 739, 740, 741, 742, 743, 746, 770, 771, 772, 773, 774, 775, 798, 762, 764, 765, 769, 515, 512, 520, 521, 528, 529};
    /**
     * The amount of x tiles
     */
    public static int mapTilesX;
    /**
     * The amount of y tiles
     */
    public static int mapTilesY;
    public static int[] currentMap0 = new int[12500];
    public static int[] currentMap1 = new int[12500];

    public static boolean loadMap() {
        try {
            File map = new File(Pokemon.WORKING_DIRECTORY + "\\data\\Pokemu.map");
            BufferedReader reader = new BufferedReader(new FileReader(map));
            String line = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(line);
            int width = Integer.parseInt(tokens.nextToken());
            int height = Integer.parseInt(tokens.nextToken());
            mapTilesX = width;
            mapTilesY = height;
            tokens.nextToken();
            line = reader.readLine();
            tokens = new StringTokenizer(line);
            if (tokens.nextToken().equalsIgnoreCase("colorization")) {
                /*
                 * Skip RGB encoding, it's not needed for us :)
                 */
                tokens.nextToken();
                tokens.nextToken();
                tokens.nextToken();
                tokens.nextToken();
                tokens.nextToken();
            }
            while (!line.equals(".")) {
                line = reader.readLine();
            }
            for (int layers = 0; layers < 2; layers++) {
                line = reader.readLine();
                tokens = new StringTokenizer(line);
                for (int y = 0; y < (width * height); y++) {
                    String code = tokens.nextToken();
                    if (layers == 0) {
                        currentMap0[y] = Integer.parseInt(code);
                    } else if (layers == 1) {
                        currentMap1[y] = Integer.parseInt(code);
                    }
                }
            }
            reader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return true;
    }
}
