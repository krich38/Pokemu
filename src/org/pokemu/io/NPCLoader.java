package org.pokemu.io;

import org.pokemu.Pokemon;
import org.pokemu.object.FaceDirection;
import org.pokemu.object.NPC;
import org.pokemu.object.NPCEncapsulation;
import org.pokemu.object.PokemonTrainer;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public final class NPCLoader {
    public static List<NPC> npcs = new ArrayList<>();

    public static boolean loadNPCs() throws IllegalAccessException, InstantiationException, FileNotFoundException {
        final File f = new File(Pokemon.WORKING_DIRECTORY + "data\\npc");
        final Yaml yaml = new Yaml();
        for (File x : f.listFiles()) {
            final NPCEncapsulation n = yaml.loadAs(new FileInputStream(x), NPCEncapsulation.class);
            final NPC npc = new NPC(n.x, n.y, n.text);
            npc.setName(n.name);
            switch (SpriteType.valueOf(n.sprite)) {
                case BOY:
                    npc.setSprite(SpriteFactory.boy);
                    break;
                case LASS:
                    npc.setSprite(SpriteFactory.lass);
                    break;
                case GUIDE:
                    npc.setSprite(SpriteFactory.guidegent);
                    break;
                case FATMAN:
                    npc.setSprite(SpriteFactory.fatman);
                    break;
            }
            npcs.add(npc);
        }
        return true;
    }

    public static NPC getNearbyNPC(PokemonTrainer trainer) {
        for (NPC other : npcs) {
            if ((other.getY() + 1) == trainer.getY()) {
                if ((other.getY()) == trainer.getX()) {
                    return other;
                }
            }
            if ((other.getY() - 1) == trainer.getY()) {
                if ((other.getX()) == trainer.getX()) {
                    return other;
                }
            }
            if ((other.getX() + 1) == trainer.getX()) {
                if ((other.getY()) == trainer.getY()) {
                    return other;
                }
            }
            if ((other.getX() - 1) == trainer.getX()) {
                if ((other.getY()) == trainer.getY()) {
                    return other;
                }
            }
            int x = other.getX();
            int y = other.getY();
            if (x == trainer.getX() && (y + 1 == trainer.getY())) {
                return other;
            }
        }
        return null;
    }

    public static FaceDirection getDirectionOf(PokemonTrainer trainer, NPC other) {
        if ((other.getY() - 1) == trainer.getY()) {
            if ((other.getX()) == trainer.getX()) {
                return FaceDirection.SOUTH;
            }
        }
        if ((other.getX() + 1) == trainer.getX()) {
            if ((other.getY()) == trainer.getY()) {
                return FaceDirection.WEST;
            }
        }
        if ((other.getX() - 1) == trainer.getX()) {
            if ((other.getY()) == trainer.getY()) {
                return FaceDirection.EAST;
            }
        }
        int x = other.getX();
        int y = other.getY();
        if (x == trainer.getX() && (y + 1 == trainer.getY())) {
            return FaceDirection.NORTH;
        }
        return null;
    }

    enum SpriteType {
        BOY, LASS, GUIDE, FATMAN
    }
}

