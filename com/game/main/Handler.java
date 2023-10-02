package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class    Handler {//loop through all objects in the game, individually update and render them to the screen
    LinkedList<GameObject> ObjectList = new LinkedList<GameObject>();

    public int spd = 5;

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

    public void clearEnemys() {
        for(int i=0;i< ObjectList.size();i++){
            GameObject tempObject = ObjectList.get(i);

            if(tempObject.getID() == ID.Player) {
                ObjectList.clear();
                if(Game.gameState != Game.STATE.End)
                    addObj(new Player((int)tempObject.getX(),(int) tempObject.getY(), ID.Player, this));
            }
        }
    }

    public void addObj(GameObject obj){
        this.ObjectList.add(obj);
    }

    public void removeObj(GameObject obj){
        this.ObjectList.remove(obj);
    }
}
