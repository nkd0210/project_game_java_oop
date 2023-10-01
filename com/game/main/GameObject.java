package com.game.main;

import java.awt.*;

public abstract class GameObject { //a default game object with characteristics
    protected int x,y; // only classes that inherits the class can access the var
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id){//constructor
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    protected abstract void render(Graphics g);
    public abstract Rectangle getBounds();


    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }

}
