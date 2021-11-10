package io.github.darkwaterkiller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Entity extends GameObject {
    
    private static final Gson g = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner filereader;

    private ArrayList<GameObject> inventory;

    public Entity(String name) {
        super(name);
        this.inventory = new ArrayList<GameObject>();
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

    public String getName(){return this.getName();}
    //no current need for inventory methods, simply just
    //getInventory then do stuff with that
    public ArrayList<GameObject> getInventory(){return this.inventory;}

    @Override
    public String toString() {
        return "Name:\t" + this.getName();
    }
}
