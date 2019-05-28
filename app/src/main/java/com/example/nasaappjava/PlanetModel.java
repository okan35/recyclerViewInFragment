package com.example.nasaappjava;

public class PlanetModel {
    String planetName;
    String imagePath;

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public PlanetModel(String planetName, String imagePath) {
        this.planetName = planetName;
        this.imagePath = imagePath;
    }
}
