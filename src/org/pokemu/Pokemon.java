package org.pokemu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.pokemu.io.CacheLoader;
import org.pokemu.io.MapLoader;
import org.pokemu.io.NPCLoader;
import org.pokemu.io.TileLoader;
import org.pokemu.object.PokemonTrainer;
import org.pokemu.object.SaveFileEncapsulation;
import org.pokemu.screen.InitialScreen;
import org.pokemu.screen.Screen;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Game engine for Pokemon Silver
 */
public final class Pokemon extends Application {
    public static final double WIDTH = 480, HEIGHT = 320;
    public static String WORKING_DIRECTORY;
    private Screen screen;
    private StackPane root;
    private Stage stage;
    private PokemonTrainer trainer;
    private Timeline timeline;
    private boolean paused;

    public static void main(String[] args) {
        Pokemon poke = new Pokemon();
        if (poke.load()) {
            Application.launch(args);
        }
    }

    private boolean load() {
        boolean tiles = false;
        boolean map = false;
        boolean npcs = false;
        if (CacheLoader.loadCache()) {
            try {
                tiles = TileLoader.loadTiles();
                map = MapLoader.loadMap();
                npcs = NPCLoader.loadNPCs();
            } catch (FileNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return tiles && map && npcs;
    }

    @Override
    public final void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        stage.setTitle("Pokemu");
        stage.setResizable(false);
        stage.centerOnScreen();
        root = new StackPane();
        root.setStyle("-fx-background-color: black;");
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        screen = new InitialScreen(this);
        timeline = new Timeline(new KeyFrame(Duration.millis(350), event -> getCurrentScreen().paint()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        root.getChildren().add(screen);
        stage.show();
    }

    public final StackPane getRoot() {
        return root;
    }

    public final Screen getCurrentScreen() {
        return screen;
    }

    /**
     * Change screen
     *
     * @param screen Our new screen reference
     */
    public final void setScreen(Screen screen) {
        this.screen = screen;
        root.getChildren().clear();
        root.getChildren().add(screen);
        root.requestLayout();
    }

    /**
     * Load game and begin playing
     *
     * @param cont Whether or not the user has selected to continue a saved game
     */
    public final void loadGame(boolean cont) {
        if (!cont) {
            trainer = new PokemonTrainer("Gold");
        } else {
            try {
                Yaml yaml = new Yaml();
                InputStream in = new FileInputStream(new File(WORKING_DIRECTORY + "\\data\\saves\\gold.yaml"));
                SaveFileEncapsulation s = yaml.loadAs(in, SaveFileEncapsulation.class);
                PokemonTrainer p = new PokemonTrainer(s.name, s.x, s.y);
                p.setCurrentX(s.x - 7);
                p.setCurrentY(s.y - 4);
                trainer = p;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        timeline = new Timeline(new KeyFrame(Duration.millis(20), new GameWorker(this)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public final PokemonTrainer getTrainer() {
        return trainer;
    }

    public final Stage getStage() {
        return stage;
    }

    public final void pause() {
        paused = true;
        getCurrentScreen().pause();
        timeline.pause();
    }

    public final boolean isPaused() {
        return paused;
    }

    public final void unpause() {
        paused = false;
        timeline.play();
    }
}