package org.pokemu.object;

import javafx.scene.image.Image;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Base level entity representation
 */
public abstract class Entity {
    private int xCoord;
    private int yCoord;
    private String name;
    protected int currentX;
    protected int currentY;
    private Image sprite;
    private State state;
    private int offsetY;
    private int offsetX;
    private FaceDirection facing;
    private boolean updateFacing;

    public Entity(int x, int y) {
        xCoord = x;
        yCoord = y;
        state = State.STATIONARY;
    }

    public final int getX() {
        return xCoord;
    }

    public final int getY() {
        return yCoord;
    }

    public final void setY(int y) {
        this.yCoord = y;
    }

    public final void setX(int x) {
        this.xCoord = x;
    }

    public void move() {
    }

    public final String getName() {
        return name;
    }

    public final Image getSprite() {
        return sprite;
    }

    public final void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final boolean isWalking() {
        return !(state == State.STATIONARY);
    }

    public final void setState(State state) {
        this.state = state;
    }

    public final State getState() {
        return state;
    }

    public final void setFacing(FaceDirection facing) {
        this.facing = facing;
        updateFacing = true;
    }

    public final boolean updateFacing() {
        if (updateFacing) {
            updateFacing = false;
            return true;
        }
        return false;
    }

    public final FaceDirection getFacing() {
        return facing;
    }

    public final void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public final void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public final int getOffsetY() {
        return offsetY;
    }

    public final void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public final int getOffsetX() {
        return offsetX;
    }

    public final void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public enum State {
        STATIONARY, WALKING_UP, WALKING_RIGHT, WALKING_DOWN, WALKING_LEFT,
    }
}