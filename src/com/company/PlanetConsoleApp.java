/**
 * Nazwa: Planety
 * Autor: Valeriia Tykhoniuk (266319)
 * Data utworzenia: 11.10.2022
 */

package com.company;

import com.company.model.Planet;
import com.company.model.PlanetColour;
import com.company.model.PlanetException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class PlanetConsoleApp {
    public static final String MENU = """
            1) New planet\s
            2) Delete planet
            3) Change information of the planet
            4) Read data from dokument
            5) Write data to the document
            6) End program""";

    public static final String CHANGEMENU = """
                1) Choose name\s
                2) Choose mass
                3) Choose radius
                4) Choose the count of satellites
                5) Choose the colour of planet
                6) None
                """;

    private static List<Planet> planets;

    private Planet currentPlanet = null;

    private static ConsoleUserDialog UI = new ConsoleUserDialog();

    public static void main(String[] args) {
        //initializePlanets();
        new PlanetConsoleApp().appLoop();
    }

    private void appLoop() {
        UI.printMessage("Nazwa: Planety. Autor: Valeriia Tykhoniuk (266319). Data utworzenia: 11.10.2022");

        while (true) {
            UI.clearConsole();
            showCurrentPlanet();

            try {
                UI.printMessage(MENU);

                switch (UI.enterInt(" What do you choose? -> ")) {
                    case 1:
                        currentPlanet = createNewPlanet();
                        break;
                    case 2:
                        currentPlanet = null;
                        UI.printMessage("Planet has been deleted");
                        break;
                    case 3:
                        if (currentPlanet == null) throw new PlanetException("Planet hasn't been chosen");
                        changePlanetData(currentPlanet);
                        break;
                    case 4:
                        String fileName = UI.enterString("Name of the file: ");
                        currentPlanet = Planet.readFromFile(fileName);
                        UI.printInfoMessage("The information was given: " + fileName);
                        break;
                    case 5:
                        String file_name = UI.enterString("Name of the file: ");
                        Planet.writeToTheDocument(file_name, currentPlanet);
                        UI.printInfoMessage("The information was written to the: " + file_name);
                        break;
                    case 6:
                        UI.printInfoMessage("\nThanks for using this program");
                        System.exit(0);
                }
            } catch (PlanetException e) {
                UI.printErrorMessage(e.getMessage());
            }

        }

    }


    private void showCurrentPlanet() {
        showPlanet(currentPlanet);
    }

    private static void showPlanet(Planet planet) {
        if (planet != null) {
            UI.printMessage("Current Planet: ");
            UI.printMessage(planet.toString());
        } else
            UI.printMessage("No planet");
    }

/*
    private static void initializePlanets() {
        Planet mars = null;
        try {
            mars = new Planet("Mars", PlanetColour.RED, 639, 1000, 5);
            Planet venus = new Planet("Venus", PlanetColour.ORANGE, 639, 1000, 5);
            planets = new ArrayList<>();
            planets.add(mars);
            planets.add(venus);
        } catch (PlanetException e) {
            e.printStackTrace();
        }

    }
 */

    public static Planet createNewPlanet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the planet name");
        String name = scanner.nextLine();
        System.out.println("Enter the planet mass");
        int mass = scanner.nextInt();
        System.out.println("Enter the planet radius");
        float radius = scanner.nextFloat();
        System.out.println("Enter the count of satellites");
        int satellites = scanner.nextInt();
        UI.printMessage("Available colours:" + Arrays.deepToString(PlanetColour.values()));
        String planet_colour = UI.enterString("Write the colour: ");
        Planet planet = null;
        try {
            planet = new Planet(name, mass, radius, satellites);
            planet.setColour(PlanetColour.valueOf(planet_colour));

        } catch (PlanetException e) {
            UI.printErrorMessage(e.getMessage());
        }
        return planet;
    }

    static void changePlanetData(Planet planet)
    {
        while (true) {
            UI.clearConsole();
            showPlanet(planet);

            try {
                switch (UI.enterInt(CHANGEMENU + "==>> ")) {
                    case 1:
                        planet.setName(UI.enterString("Write the new name of planet: "));
                        break;
                    case 2:
                        planet.setMass(UI.enterInt("Write the new mass of planet: "));
                        break;
                    case 3:
                        planet.setRadius(UI.enterFloat("Write the new radius of planet: "));
                        break;
                    case 4:
                        planet.setSatellitesCount(UI.enterInt("Write the new count of satellites of planet: "));
                    case 5:
                        UI.printMessage("Available colours:" + Arrays.deepToString(PlanetColour.values()));
                        //planet.setColour(UI.enterString("Write the colour: ")); //todo
                        break;
                    case 6: return;
                }
            } catch (PlanetException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
    }

}
