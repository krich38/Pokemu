package org.pokemu.object;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Represents a Non Playing Character
 */
public final class NPC extends Entity {
    private final String text;

    public NPC(int x, int y, String text) {
        super(x, y);
        this.text = text;
    }

    /**
     * The text this NPC says
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}
