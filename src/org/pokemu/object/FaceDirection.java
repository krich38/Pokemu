package org.pokemu.object;

import javafx.scene.image.Image;
import org.pokemu.io.SpriteFactory;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          What direction the player is facing.
 */
public enum FaceDirection {
    NORTH(SpriteFactory.UP),
    EAST(SpriteFactory.RIGHT),
    SOUTH(SpriteFactory.DOWN),
    WEST(SpriteFactory.LEFT);
    private final Image sprite;

    private FaceDirection(Image sprite) {
        this.sprite = sprite;
    }

    public final Image getSprite() {
        return sprite;
    }
}
