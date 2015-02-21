package org.pokemu.io;

import javafx.scene.image.Image;
import org.pokemu.Pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          Simple sprite loading.
 *          <p>
 *          TODO: Consider using a SpriteMap. Not sure if it would better but definately worth considering.
 *          TODO: Amendment, maybe consider using a lazy loading system?
 */
public final class SpriteFactory {
    public static Image DOWN;
    public static Image UP;
    public static Image LEFT;
    public static Image RIGHT;
    public static Image UP2;
    public static Image DOWN1, DOWN2, RIGHT1, RIGHT2, LEFT1, LEFT2;
    public static Image UP1;
    public static Image baldman;
    public static Image beauty;
    public static Image youngster;
    public static Image shopkeep;
    public static Image oak;
    public static Image picknicker;
    public static Image nurse;
    public static Image mrpokemon;
    public static Image mom;
    public static Image lass;
    public static Image guidegent;
    public static Image fisher;
    public static Image fatman;
    public static Image falkner;
    public static Image camperl;
    public static Image camperd;
    public static Image bugcatcher;
    public static Image boy;
    public static Image birdkeeperr;
    public static Image birdkeeperl;
    public static Image bill;
    public static Image arrow;
    public static Image message;

    static {
        try {
            DOWN = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Down.png")));
            DOWN1 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Down1.png")));
            DOWN2 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Down1.png")));
            RIGHT = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Right.png")));
            RIGHT1 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Right1.png")));
            RIGHT2 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Right2.png")));
            LEFT = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Left.png")));
            LEFT1 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Left1.png")));
            LEFT2 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Left2.png")));
            UP = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Up.png")));
            UP1 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Up1.png")));
            UP2 = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Up2.png")));
            baldman = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Baldman.png")));
            beauty = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Beauty.png")));
            bill = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Bill.png")));
            birdkeeperl = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\BirdKeeperL.png")));
            birdkeeperr = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\BirdKeeperR.png")));
            boy = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Boy.png")));
            bugcatcher = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\BugCatcher.png")));
            camperd = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\CamperD.png")));
            camperl = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\CamperL.png")));
            falkner = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Falkner.png")));
            fatman = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\FatMan.png")));
            fisher = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Fisher.png")));
            guidegent = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\GuideGent.png")));
            lass = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Lass.png")));
            mom = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Mom.png")));
            mrpokemon = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\MrPokemon.png")));
            nurse = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Nurse.png")));
            picknicker = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Picknicker.png")));
            oak = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\ProfOak.png")));
            shopkeep = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\ShopKeep.png")));
            youngster = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\sprites\\Youngster.png")));
            message = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Message_Text.png")));
            arrow = new Image(new FileInputStream(new File(Pokemon.WORKING_DIRECTORY + "\\data\\gfx\\pic\\Arrow.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
