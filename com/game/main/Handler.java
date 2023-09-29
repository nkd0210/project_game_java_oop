package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {//loop through all objects in the game, individually update and render them to the screen
    LinkedList<GameObject> ObjectList = new LinkedList<GameObject>();

    public void tickHandler(){
        for(int i=0; i< ObjectList.size(); i++){
            GameObject tempObject = ObjectList.get(i);
            tempObject.tick();//tick method from GameObject class
        }
    }

    public void renderHandler(Graphics u){
        for (int i = 0; i<ObjectList.size(); i++){
            GameObject tempObject = ObjectList.get(i);
            tempObject.render(u);
        }
    }

    public void addObj(GameObject obj){
        this.ObjectList.add(obj);
    }

    public void removeObj(GameObject obj){
        this.ObjectList.remove(obj);
    }
}
