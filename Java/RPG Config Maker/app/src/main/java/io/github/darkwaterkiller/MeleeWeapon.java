package io.github.darkwaterkiller;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MeleeWeapon extends Item {

    private static final Gson g = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner filereader;

    private final int damage, speed, weight, value;

    public MeleeWeapon(String name, int damage, int speed, int weight, int value) {
        super(name);
        this.damage = damage;
        this.speed = speed;
        this.weight = weight;
        this.value = value;
    }  

    public MeleeWeapon MeleeWeaponFromFile(String filename) {
        try {
            //open file and make scanner read from it
            filereader = new Scanner(new File(filename));
            //shove file data into a string until EOF
            String inputJson = "";
            while(filereader.hasNext())
                inputJson += filereader.nextLine();
            //close up the scanner stream and set to null
            filereader.close();
            filereader = null;
            //return the read json object
            return g.fromJson(inputJson, MeleeWeapon.class);
        } catch(Exception e) {
            //print the stacktrace, post an error to console, and return a null object
            e.printStackTrace();
            System.err.printf("Could not load config from file '%s'\n", filename);
            return null;
        }
    }

    // /**
    //  * Write the object to file with the given name
    //  * @param filename The name of the file to write the object to
    //  * @return returns true if file writing was successful, false indicates failure to write file.
    //  */
    // public boolean writeToFile(String filename) {
    //     try {
    //         //write object data to a file
    //         g.toJson(this, new FileWriter(filename));
    //         //return success state
    //         return true;
    //     } catch(Exception e) {
    //         //print the stacktrace, post an error to console, and return failure state
    //         e.printStackTrace();
    //         System.err.printf("Could not write melee weapon '%s' to '%s'.\n", this.getName(), filename);
    //         return false;
    //     }
    // }

    public int getDamage(){return this.damage;}
    public int getSpeed(){return this.speed;}
    public int getWeight(){return this.weight;}
    public int getValue(){return this.value;}

    @Override
    public String toString() {
        return this.getName()
        + ":\n\tDamage:\t" + this.damage 
        + "\n\tSpeed:\t" + this.speed
        + "\n\tWeight:\t" + this.weight
        + "\n\tValue:\t" + this.value;
    }
}
