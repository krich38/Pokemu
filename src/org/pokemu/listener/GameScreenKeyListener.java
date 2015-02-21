package org.pokemu.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.pokemu.Pokemon;
import org.pokemu.io.MapLoader;
import org.pokemu.io.NPCLoader;
import org.pokemu.io.SpriteFactory;
import org.pokemu.io.TileLoader;
import org.pokemu.object.Entity;
import org.pokemu.object.NPC;
import org.pokemu.object.PokemonTrainer;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class GameScreenKeyListener implements EventHandler<KeyEvent> {
    private final PokemonTrainer trainer;
    private final Pokemon pokemon;

    public GameScreenKeyListener(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.trainer = pokemon.getTrainer();
    }

    @Override
    public final void handle(KeyEvent event) {
        if (!trainer.isWalking() && !pokemon.isPaused()) {
            switch (event.getCode()) {
                case RIGHT:
                    if (checkDirection(Entity.State.WALKING_RIGHT)) {
                        trainer.setState(Entity.State.WALKING_RIGHT);
                        if (trainer.isEngagedNPC()) {
                            trainer.setEngageNPC(null);
                        }
                    } else {
                        trainer.setSprite(SpriteFactory.RIGHT);
                    }
                    break;
                case UP:
                    if (checkDirection(Entity.State.WALKING_UP)) {
                        trainer.setState(Entity.State.WALKING_UP);
                        if (trainer.isEngagedNPC()) {
                            trainer.setEngageNPC(null);
                        }
                    } else {
                        trainer.setSprite(SpriteFactory.UP);
                    }
                    break;
                case LEFT:
                    if (checkDirection(Entity.State.WALKING_LEFT)) {
                        trainer.setState(Entity.State.WALKING_LEFT);
                        if (trainer.isEngagedNPC()) {
                            trainer.setEngageNPC(null);
                        }
                    } else {
                        trainer.setSprite(SpriteFactory.LEFT);
                    }
                    break;
                case DOWN:
                    if (checkDirection(Entity.State.WALKING_DOWN)) {
                        trainer.setState(Entity.State.WALKING_DOWN);
                        if (trainer.isEngagedNPC()) {
                            trainer.setEngageNPC(null);
                        }
                    } else {
                        trainer.setSprite(SpriteFactory.DOWN);
                    }
                    break;
                case Z:
                    if (!trainer.isEngagedNPC()) {
                        final NPC npc = NPCLoader.getNearbyNPC(trainer);
                        if (npc != null) {
                            trainer.setEngageNPC(npc);
                            trainer.setFacing(NPCLoader.getDirectionOf(trainer, npc));
                        }
                    } else {
                        trainer.setEngageNPC(null);
                    }
                    break;
                case ENTER:
                    pokemon.pause();
            }
        }
    }

    private boolean checkDirection(Entity.State direction) {
        switch (direction) {
            case WALKING_UP:
                if (trainer.getX() >= MapLoader.mapTilesX - 1) {
                    return false;
                }
                for (int i = 0; i < TileLoader.STATICS.length; i++) {
                    final int newY = trainer.getY() - 1;
                    if (TileLoader.STATICS[i] != null) {
                        if (TileLoader.STATICS[i].getY() == newY && trainer.getX() == TileLoader.STATICS[i].getX()) {
                            return false;
                        }
                    }
                }
                for (NPC n : NPCLoader.npcs) {
                    if ((n.getY() + 1) == trainer.getY() && (n.getX()) == trainer.getX()) {
                        return false;
                    }
                }
                break;
            case WALKING_RIGHT:
                if (trainer.getX() >= MapLoader.mapTilesX - 1) {
                    return false;
                }
                for (int i = 0; i < TileLoader.STATICS.length; i++) {
                    final int newX = trainer.getX() + 1;
                    if (TileLoader.STATICS[i] != null) {
                        if (TileLoader.STATICS[i].getY() == trainer.getY() && newX == TileLoader.STATICS[i].getX()) {
                            return false;
                        }
                    }
                }
                for (NPC n : NPCLoader.npcs) {
                    if ((n.getX() - 1) == trainer.getX() && (n.getY()) == trainer.getY()) {
                        return false;
                    }
                }
                break;
            case WALKING_DOWN:
                if (trainer.getX() >= MapLoader.mapTilesX - 1) {
                    return false;
                }
                for (int i = 0; i < TileLoader.STATICS.length; i++) {
                    final int newY = trainer.getY() + 1;
                    if (TileLoader.STATICS[i] != null) {
                        if (TileLoader.STATICS[i].getY() == newY && trainer.getX() == TileLoader.STATICS[i].getX()) {
                            return false;
                        }
                    }
                }
                for (NPC n : NPCLoader.npcs) {
                    if ((n.getY() - 1) == trainer.getY() && (n.getX()) == trainer.getX()) {
                        return false;
                    }
                }
                break;
            case WALKING_LEFT:
                if (trainer.getX() >= MapLoader.mapTilesX - 1) {
                    return false;
                }
                for (int i = 0; i < TileLoader.STATICS.length; i++) {
                    final int newX = trainer.getX() - 1;
                    if (TileLoader.STATICS[i] != null) {
                        if (TileLoader.STATICS[i].getY() == trainer.getY() && newX == TileLoader.STATICS[i].getX()) {
                            return false;
                        }
                    }
                }
                for (NPC n : NPCLoader.npcs) {
                    if ((n.getX() + 1) == trainer.getX() && (n.getY()) == trainer.getY()) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }
}
