package io.github.darkwaterkiller;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameObject {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName(){return this.name;}

    public void writeToFile(String filename) {
        String json = gson.toJson(this);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(json);
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.printf("Could not save JSON data to file '%s'\n", filename);
        }
         
    }

}
