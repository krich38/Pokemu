package org.pokemu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.pokemu.io.SpriteFactory;
import org.pokemu.object.Entity;
import org.pokemu.object.FaceDirection;
import org.pokemu.object.PokemonTrainer;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Our game worker thread. This updates the game every 20 miliseconds, and is the main engine.
 */
public final class GameWorker implements EventHandler<ActionEvent> {
    private final Pokemon pokemon;
    private int walkingPixles;
    private boolean spriteFlag;

    public GameWorker(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public final void handle(ActionEvent event) {
        pokemon.getCurrentScreen().paint();
        final PokemonTrainer trainer = pokemon.getTrainer();
        
        /*
         * Update our sprites as the player is walking.
         * Walking motion is handled here
         */
        switch (trainer.getState()) {
            case STATIONARY:
                break;
            case WALKING_UP:
                trainer.setFacing(FaceDirection.NORTH);
                walkingPixles++;
                trainer.setOffsetY(trainer.getOffsetY() + 2);
                if (walkingPixles >= 0 && walkingPixles < 4) {
                    trainer.setSprite(SpriteFactory.UP);
                } else if (walkingPixles > 4 && walkingPixles < 8) {
                    trainer.setSprite(SpriteFactory.UP);
                } else if (walkingPixles > 8 && walkingPixles < 12) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.UP1);
                    } else {
                        trainer.setSprite(SpriteFactory.UP2);
                    }
                } else if (walkingPixles >= 12 && walkingPixles < 15) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.UP1);
                    } else {
                        trainer.setSprite(SpriteFactory.UP2);
                    }
                } else {
                    trainer.setSprite(SpriteFactory.UP);
                }
                break;
            case WALKING_RIGHT:
                trainer.setFacing(FaceDirection.EAST);
                walkingPixles++;
                trainer.setOffsetX(trainer.getOffsetX() - 2);
                if (walkingPixles >= 0 && walkingPixles < 4) {
                    trainer.setSprite(SpriteFactory.RIGHT);
                } else if (walkingPixles > 4 && walkingPixles < 8) {
                    trainer.setSprite(SpriteFactory.RIGHT);
                } else if (walkingPixles > 8 && walkingPixles < 12) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.RIGHT1);
                    } else {
                        trainer.setSprite(SpriteFactory.RIGHT2);
                    }
                } else if (walkingPixles >= 12 && walkingPixles < 15) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.RIGHT1);
                    } else {
                        trainer.setSprite(SpriteFactory.RIGHT2);
                    }
                } else {
                    trainer.setSprite(SpriteFactory.RIGHT);
                }
                break;
            case WALKING_DOWN:
                trainer.setFacing(FaceDirection.SOUTH);
                walkingPixles++;
                trainer.setOffsetY(trainer.getOffsetY() - 2);
                if (walkingPixles >= 0 && walkingPixles < 4) {
                    trainer.setSprite(SpriteFactory.DOWN);
                } else if (walkingPixles > 4 && walkingPixles < 8) {
                    trainer.setSprite(SpriteFactory.DOWN);
                } else if (walkingPixles > 8 && walkingPixles < 12) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.DOWN1);
                    } else {
                        trainer.setSprite(SpriteFactory.DOWN2);
                    }
                } else if (walkingPixles >= 12 && walkingPixles < 15) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.DOWN1);
                    } else {
                        trainer.setSprite(SpriteFactory.DOWN2);
                    }
                } else {
                    trainer.setSprite(SpriteFactory.DOWN);
                }
                break;
            case WALKING_LEFT:
                trainer.setFacing(FaceDirection.WEST);
                walkingPixles++;
                trainer.setOffsetX(trainer.getOffsetX() + 2);
                if (walkingPixles >= 0 && walkingPixles < 4) {
                    trainer.setSprite(SpriteFactory.LEFT);
                } else if (walkingPixles > 4 && walkingPixles < 8) {
                    trainer.setSprite(SpriteFactory.LEFT);
                } else if (walkingPixles > 8 && walkingPixles < 12) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.LEFT1);
                    } else {
                        trainer.setSprite(SpriteFactory.LEFT2);
                    }
                } else if (walkingPixles >= 12 && walkingPixles < 15) {
                    if (!spriteFlag) {
                        trainer.setSprite(SpriteFactory.LEFT1);
                    } else {
                        trainer.setSprite(SpriteFactory.LEFT2);
                    }
                } else {
                    trainer.setSprite(SpriteFactory.LEFT);
                }
                break;
        }
        if (walkingPixles >= 16) {
            walkingPixles = 0;
            /*
             * Update our camera offset
             */
            switch (trainer.getState()) {
                case STATIONARY:
                    break;
                case WALKING_UP:
                    trainer.setY(trainer.getY() - 1);
                    break;
                case WALKING_RIGHT:
                    trainer.setX(trainer.getX() + 1);
                    break;
                case WALKING_DOWN:
                    trainer.setY(trainer.getY() + 1);
                    break;
                case WALKING_LEFT:
                    trainer.setX(trainer.getX() - 1);
                    break;
            }
            spriteFlag = !spriteFlag;
            trainer.move();
            trainer.setState(Entity.State.STATIONARY);
        }
        
        /*
         * Update direction player is facing
         */
        if (!trainer.isWalking() && trainer.updateFacing()) {
            trainer.setSprite(trainer.getFacing().getSprite());
        }
    }
}
