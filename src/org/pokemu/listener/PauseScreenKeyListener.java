package org.pokemu.listener;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.pokemu.Pokemon;
import org.pokemu.io.SpriteFactory;
import org.pokemu.object.PokemonTrainer;
import org.pokemu.screen.GameScreen;
import org.pokemu.screen.Screen;
import org.pokemu.screen.SettingsScreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class PauseScreenKeyListener implements EventHandler<KeyEvent> {
    private final PokemonTrainer trainer;
    private final Pokemon pokemon;
    private int selectionFlag;
    private final GameScreen screen;

    public PauseScreenKeyListener(Screen screen) {
        this.screen = (GameScreen) screen;
        selectionFlag = 0;
        pokemon = screen.getPokemon();
        trainer = pokemon.getTrainer();
    }

    @Override
    public void handle(KeyEvent event) {
        if (pokemon.isPaused()) {
            screen.repaintGame();
            final GraphicsContext g = screen.getGraphicsContext2D();
            g.drawImage(screen.getCurrentMenuSprite(), 0, 0);
            if (event.getCode() == KeyCode.UP) {
                if (!(selectionFlag == 0)) {
                    selectionFlag--;
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                if (!(selectionFlag == 7)) {
                    selectionFlag++;
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                screen.unpause();
            } else if (event.getCode() == KeyCode.ENTER) {
                switch (selectionFlag) {
                    case 0:
                        try {
                            final Image poke = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Pokedex.png")));
                            screen.setCurrentMenuSprite(poke);
                            selectionFlag = -1;
                            g.drawImage(poke, 0, 0);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        trainer.save();
                        screen.unpause();
                        break;
                    case 6:
                        pokemon.setScreen(new SettingsScreen(pokemon));
                        break;
                    case 7:
                        screen.unpause();
                        break;
                }
            }
            if (selectionFlag != -1) {
                g.drawImage(SpriteFactory.arrow, 335, (selectionFlag * 32) + 20);
            }
        }
    }
}
