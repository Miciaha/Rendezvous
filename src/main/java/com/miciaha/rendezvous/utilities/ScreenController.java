package com.miciaha.rendezvous.utilities;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * The type Screen controller.
 */
public class ScreenController {
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main;

    /**
     * Set main scene.
     *
     * @param Main the main
     */
    public static void setMainScene(Scene Main) {
        main = Main;
    }

    /**
     * Add screen.
     *
     * @param name the name
     * @param pane the pane
     */
    public static void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    /**
     * Remove screen.
     *
     * @param name the name
     */
    public static void removeScreen(String name) {
        screenMap.remove(name);
    }

    /**
     * Activate.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    public static void activate(String name, double x, double y) {
        main.getWindow().setHeight(y);
        main.getWindow().setWidth(x);
        main.setRoot(screenMap.get(name));
    }

}
