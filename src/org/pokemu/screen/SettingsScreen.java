package org.pokemu.screen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.pokemu.Pokemon;
import org.pokemu.io.SpriteFactory;
import org.pokemu.listener.SettingsScreenListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class SettingsScreen extends Screen {
    private final SettingsScreenListener listener;

    public SettingsScreen(Pokemon pokemon) {
        super(pokemon);
        final GraphicsContext g = getGraphicsContext2D();
        listener = new SettingsScreenListener();
        setFocusTraversable(true);
        setOnKeyPressed(listener);
        g.clearRect(0, 0, Pokemon.WIDTH, Pokemon.HEIGHT);
        try {
            Image bg = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Option.png")));
            setCurrentMenuSprite(bg);
            g.drawImage(bg, 0, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void paint() {
        GraphicsContext g = getGraphicsContext2D();
        g.drawImage(getCurrentMenuSprite(), 0, 0);
        g.drawImage(SpriteFactory.arrow, 22, (listener.selectionFlag() * 32) + 85);
    }
}
