package pl.rapid.myapplication;

import java.io.Serializable;

public class Animal implements Serializable {

    private int _id;
    private String species;
    private String color;
    private float size;
    private String description;

    public Animal() {
    }

    public Animal(String species, String color, float size, String description) {
        this.species = species;
        this.color = color;
        this.size = size;
        this.description = description;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "_id=" + _id +
                ", species='" + species + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", description='" + description + '\'' +
                '}';
    }
}
