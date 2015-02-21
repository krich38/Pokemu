package org.pokemu.screen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.pokemu.Pokemon;
import org.pokemu.io.SpriteFactory;
import org.pokemu.listener.MenuScreenKeyListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class OptionScreen extends Screen {
    private final GraphicsContext context;
    private final MenuScreenKeyListener listener;
    private Image image;

    public OptionScreen(Pokemon pokemon) {
        super(pokemon);
        context = super.getGraphicsContext2D();
        listener = new MenuScreenKeyListener(pokemon);
        setOnKeyReleased(listener);
        setFocusTraversable(true);
        try {
            image = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Continue.png")));
            context.drawImage(image, 0, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void paint() {
        context.drawImage(image, 0, 0);
        context.drawImage(SpriteFactory.arrow, 13, ((listener.selectionFlag() * 32) + 20));
    }
}
