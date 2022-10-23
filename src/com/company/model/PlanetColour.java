package com.company.model;

public enum PlanetColour {
    UNKNOWN("-------"),
    BLUE("blue" || "Blue"),
    VIOLET ("violet" || "Violet"),
    RED ("red" || "Red"),
    YELLOW ("yellow" || "Yellow"),
    ORANGE  ("orange" || "Orange"),

    String planet_Colour;

    private PlanetColour(String planet_colour) {
        planetColour = planet_colour;
    }

    @Override
    public String toString() {
        return planetColour;
    }

}
