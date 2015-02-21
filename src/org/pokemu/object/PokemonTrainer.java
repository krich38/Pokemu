package org.pokemu.object;

import org.pokemu.Pokemon;
import org.pokemu.io.SpriteFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Our main player!
 */
public final class PokemonTrainer extends Entity {
    private boolean repaint;
    private NPC engageNPC;

    public PokemonTrainer(String name) {
        super(6, 67);
        setCurrentX(-1);
        setCurrentY(63);
        setName(name);
        setSprite(SpriteFactory.DOWN);
    }

    public PokemonTrainer(String name, int x, int y) {
        super(x, y);
        setName(name);
        setSprite(SpriteFactory.DOWN);
    }

    public final void move() {
        int xCoord = getX();
        int yCoord = getY();
        if (xCoord == 85 && yCoord == 99) {
            currentX -= 43;
            currentY += 11;
            setX(42);
            setY(110);
            repaint = true;
            return;
        }
        if (xCoord == 79 && yCoord == 99) {
            currentX -= 58;
            currentY += 11;
            setX(21);
            setY(110);
            repaint = true;
            return;
        }
        if (xCoord == 73 && yCoord == 103) {
            currentX -= 33;
            currentY -= 10;
            setX(40);
            setY(93);
            repaint = true;
            return;
        }
        if (xCoord == 40 && yCoord == 94) {
            currentX += 33;
            currentY += 10;
            setX(73);
            setY(104);
            repaint = true;
            return;
        }
        if (xCoord == 81 && yCoord == 105) {
            currentX -= 54;
            currentY -= 36;
            setX(27);
            setY(69);
            repaint = true;
            return;
        }
        if (xCoord == 27 && yCoord == 70) {
            currentX += 54;
            currentY += 36;
            setX(81);
            setY(106);
            repaint = true;
            return;
        }
        if (xCoord == 73 && yCoord == 81) {
            currentX -= 34;
            currentY -= 4;
            setX(39);
            setY(77);
            repaint = true;
            return;
        }
        if (xCoord == 39 && yCoord == 78) {
            currentX += 34;
            currentY += 4;
            setX(73);
            setY(82);
            repaint = true;
            return;
        }
        if (xCoord == 37 && yCoord == 31) {
            currentX += 5;
            currentY += 79;
            setX(42);
            setY(110);
            repaint = true;
            return;
        }
        if (xCoord == 15 && yCoord == 23) {
            currentX += 6;
            currentY += 87;
            setX(21);
            setY(110);
            repaint = true;
            return;
        }
        if (xCoord == 27 && yCoord == 35) {
            currentX -= 21;
            currentY += 75;
            setX(6);
            setY(110);
            repaint = true;
            return;
        }
        if (xCoord == 6 && yCoord == 111) {
            currentX += 21;
            currentY -= 75;
            setX(27);
            setY(36);
            repaint = true;
            return;
        }
        if (xCoord == 24 && yCoord == 23) {
            currentX -= 17;
            currentY += 70;
            setX(7);
            setY(93);
            repaint = true;
            return;
        }
        if (xCoord == 7 && yCoord == 94) {
            currentX += 17;
            currentY -= 70;
            setX(24);
            setY(24);
            repaint = true;
            return;
        }
        if (xCoord == 36 && yCoord == 23) {
            currentX -= 12;
            currentY += 70;
            setX(24);
            setY(93);
            repaint = true;
            return;
        }
        if (xCoord == 24 && yCoord == 94) {
            currentX += 12;
            currentY -= 70;
            setX(36);
            setY(24);
            repaint = true;
            return;
        }
        if (xCoord == 50 && yCoord == 31) {
            currentX -= 6;
            currentY -= 0;
            setX(44);
            setY(31);
            repaint = true;
            return;
        }
        if (xCoord == 50 && yCoord == 30) {
            currentX -= 6;
            currentY -= 0;
            setX(44);
            setY(30);
            repaint = true;
            return;
        }
        if (xCoord == 45 && yCoord == 31) {
            currentX += 6;
            currentY += 0;
            setX(51);
            setY(31);
            repaint = true;
            return;
        }
        if (xCoord == 45 && yCoord == 30) {
            currentX += 6;
            currentY += 0;
            setX(51);
            setY(30);
            repaint = true;
            return;
        }
        if (xCoord == 11 && yCoord == 64) {
            currentX += 18;
            currentY += 0;
            setX(29);
            setY(64);
            repaint = true;
            setSprite(SpriteFactory.DOWN);
            return;
        }
        if (xCoord == 29 && yCoord == 63) {
            currentX -= 18;
            currentY += 2;
            setX(11);
            setY(65);
            repaint = true;
            setSprite(SpriteFactory.DOWN);
            return;
        }
    }

    public final boolean isRepaint() {
        return repaint;
    }

    public final void setRepaint(boolean repaint) {
        this.repaint = repaint;
    }

    public final void save() {
        try {
            Yaml yaml = new Yaml();
            FileWriter out = new FileWriter(new File(Pokemon.WORKING_DIRECTORY + "\\data\\saves\\gold.yaml"));
            SaveFileEncapsulation s = new SaveFileEncapsulation();
            s.x = getX();
            s.y = getY();
            yaml.dump(s, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void setEngageNPC(NPC engageNPC) {
        this.engageNPC = engageNPC;
    }

    public final boolean isEngagedNPC() {
        return engageNPC != null;
    }

    public final NPC getEngaged() {
        return engageNPC;
    }
}
