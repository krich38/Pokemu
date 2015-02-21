package org.pokemu.screen;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import org.pokemu.Pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class InitialScreen extends Screen {
    private final StackPane root;
    private final Canvas enterCanvas;
    private boolean paintEnter;

    public InitialScreen(Pokemon pokemon) throws FileNotFoundException {
        super(pokemon);
        final GraphicsContext context = getGraphicsContext2D();
        context.drawImage(new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\Pic_2.png"))), 0, 0);
        root = pokemon.getRoot();
        paintEnter = true;


        /*
         * Build a canvas with our 'press enter' logo on it
         * and flash it
         */
        final Canvas enterCanvas = new Canvas(Pokemon.WIDTH, Pokemon.HEIGHT);
        enterCanvas.getGraphicsContext2D().drawImage(new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\Start.png"))), 0, 260);
        this.enterCanvas = enterCanvas;
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    root.getChildren().clear();
                    OptionScreen screen = new OptionScreen(pokemon);
                    root.getChildren().clear();
                    root.getChildren().add(screen);
                    pokemon.setScreen(screen);
                }
            }
        });
        setFocusTraversable(true);
    }

    public final void paint() {
        if (paintEnter) {
            root.getChildren().add(enterCanvas);
        } else {
            root.getChildren().remove(1);
        }
        paintEnter = !paintEnter;
    }
}