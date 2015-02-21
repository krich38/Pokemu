package org.pokemu.screen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.pokemu.Pokemon;
import org.pokemu.io.SpriteFactory;
import org.pokemu.listener.GameScreenKeyListener;
import org.pokemu.listener.PauseScreenKeyListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Base level screen.
 */
public abstract class Screen extends Canvas {
    private Image currentMenuSprite;
    private final Pokemon pokemon;

    public Screen(Pokemon pokemon) {
        super(Pokemon.WIDTH, Pokemon.HEIGHT);
        this.pokemon = pokemon;
    }

    /**
     * Paint whatever screen we are currently on
     */
    public abstract void paint();

    public final void drawMessage(String text) {
        GraphicsContext g = getGraphicsContext2D();
        g.drawImage(SpriteFactory.message, 0, 0);
        g.strokeText(text, 25, 255);
    }

    public final void pause() {
        try {
            if (this instanceof GameScreen) {
                setOnKeyPressed(new PauseScreenKeyListener(this));
                currentMenuSprite = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Menu.png")));
                getGraphicsContext2D().drawImage(currentMenuSprite, 0, 0);
                getGraphicsContext2D().drawImage(SpriteFactory.arrow, 335, 20);
            } else if (this instanceof OptionScreen) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Image getCurrentMenuSprite() {
        return currentMenuSprite;
    }

    public final void unpause() {
        setOnKeyPressed(new GameScreenKeyListener(pokemon));
        pokemon.unpause();
    }

    public final void setCurrentMenuSprite(Image currentMenuSprite) {
        this.currentMenuSprite = currentMenuSprite;
    }

    public final Pokemon getPokemon() {
        return pokemon;
    }
}
