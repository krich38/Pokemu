package org.pokemu.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.pokemu.Pokemon;
import org.pokemu.screen.GameScreen;
import org.pokemu.screen.SettingsScreen;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class MenuScreenKeyListener implements EventHandler<KeyEvent> {
    private final Pokemon pokemon;
    private int selectionFlag;

    public MenuScreenKeyListener(Pokemon pokemon) {
        this.pokemon = pokemon;
        selectionFlag = 0;
    }

    @Override
    public final void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (!(selectionFlag == 0)) {
                selectionFlag--;
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!(selectionFlag == 2)) {
                selectionFlag++;
            }
        } else if (event.getCode() == KeyCode.ENTER) {
            if (selectionFlag == 2) {
                pokemon.setScreen(new SettingsScreen(pokemon));
            } else {
                pokemon.loadGame(selectionFlag == 0);
                pokemon.setScreen(new GameScreen(pokemon));
            }
        }
    }

    public final int selectionFlag() {
        return selectionFlag;
    }
}
