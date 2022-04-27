package com.miciaha.rendezvous.utilities;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {
    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main ;

    public static void setMainScene(Scene Main){
        main = Main;
    }

    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public static void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name, double x, double y){
        main.getWindow().setHeight(y);
        main.getWindow().setWidth(x);
        main.setRoot(screenMap.get(name));
    }

}
