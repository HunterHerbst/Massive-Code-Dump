package io.github.darkwaterkiller;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RangedWeapon extends Item {
    
    private static final Gson g = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner filereader;

    private int damage, rof, accuracy, weight, value, curCap, maxCap;

    public RangedWeapon(String name, int damage, int rof, int accuracy, int weight, int value, int curCap, int maxCap) {
        super(name);
        this.damage = damage;
        this.rof = rof;
        this.accuracy = accuracy;
        this.weight = weight;
        this.value = value;
        this.curCap = curCap;
        this.maxCap = maxCap;
    }

    public RangedWeapon RangedWeaponFromFile(String filename) {
        try {
            //open file and make scanner read from it
            filereader = new Scanner(new File(filename));
            //file data into a string until EOF
            String inputJson = "";
            while(filereader.hasNext())
                inputJson += filereader.nextLine();
            //close up the scanner stream and set to null
            filereader.close();
            filereader = null;
            //return the read json object
            return g.fromJson(inputJson, RangedWeapon.class);
        } catch(Exception e) {
            //print stacktrace, post an error to console, and return a null object
            e.printStackTrace();
            System.err.printf("Could not load config from file '%s'\n", filename);
            return null;
        }
    }

    // /**
    //  * Write the object to a file with the given name
    //  * @param filename The name of the file to write the object to
    //  * @return returns true if the file writing was successful, false indicates failure to write file.
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
    //         System.err.printf("Could not write ranged weapon '%s' to '%s'.\n", this.getName(), filename);
    //         return false;
    //     }
    // }

    public int getDamage(){return this.damage;}
    public int getROF(){return this.rof;}
    public int getAccuracy(){return this.accuracy;}
    public int getWeight(){return this.weight;}
    public int getValue(){return this.value;}
    public int getCurCap(){return this.curCap;}
    public int getMaxCap(){return this.maxCap;}

    @Override
    public String toString() {
        return this.getName()
        + ":\n\tDamage:\t" + this.damage
        + "\n\tRoF:\t" + this.rof
        + "\n\tAccuracy:\t" + this.accuracy
        + "\n\tWeight:\t" + this.weight
        + "\n\tValue:\t" + this.value
        + "\n\tAmmo:\t" + this.curCap + "/" + this.maxCap;
    }
}
