package org.pokemu.object;

import javafx.scene.image.Image;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Represents an impassable static tile
 */
public final class StaticTile extends Entity {
    public StaticTile(int x, int y, Image s) {
        super(x, y);
        setSprite(s);
    }

    public final int getCurrentX() {
        return super.getX();
    }

    public final int getCurrentY() {
        return super.getY();
    }
}