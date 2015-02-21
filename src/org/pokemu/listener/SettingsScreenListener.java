package org.pokemu.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class SettingsScreenListener implements EventHandler<KeyEvent> {
    private int selectionFlag;

    public SettingsScreenListener() {
        selectionFlag = 0;
    }

    @Override
    public final void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (!(selectionFlag == 0)) {
                selectionFlag--;
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!(selectionFlag == 5)) {
                selectionFlag++;
            }
        }
    }

    public final int selectionFlag() {
        return selectionFlag;
    }
}
