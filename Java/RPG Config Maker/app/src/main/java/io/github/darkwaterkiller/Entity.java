package io.github.darkwaterkiller;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Entity {
    
    private static final Gson g = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner filereader;

    private final String name;
    private Item[] inventory;

    public Entity(String name) {
        this.name = name;
        this.inventory = new Item[0];
    }

    public Entity EntityFromFile(String filename) {
        try {
            //open file and make scanner read from it
            filereader = new Scanner(new File(filename));
            //file data into a string until EOF
            String inputJson = "";
            while(filereader.hasNext())
                inputJson += filereader.nextLine();
            //close up the scanner stream and set it to null
            filereader.close();
            filereader = null;
            //return the json object
            return g.fromJson(inputJson, Entity.class);
        } catch(Exception e) {
            //print the stacktrace, post an error to console, and return a null object
            e.printStackTrace();
            System.err.printf("Could not load config from file '%s'\n", filename);
            return null;
        }
    }

    public boolean writeToFile(String filename) {
        try {
            //write object data to a file
            g.toJson(this, new FileWriter(filename));
            //return success state
            return true;
        } catch(Exception e) {
            //print the stacktrace, post an error to console, and return failure state
            e.printStackTrace();
            System.err.printf("Could not write entity '%s' to '%s'.\n", this.name, filename);
            return false;
        }
    }

    public String getName(){return this.name;}
    public Item[] getInventory(){return this.inventory;}

    //TODO implement inventory management

    @Override
    public String toString() {
        return "Name:\t" + this.name;
    }
}
