package com.game.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Random r = new Random();
    Handler handler;
    public Player(int x,int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
    }
    private void collision(){
        for(int i=0; i<handler.ObjectList.size();i++){
            GameObject temp = handler.ObjectList.get(i);
            if(temp.getID()== ID.BasicEnemy || temp.getID()== ID.FastEnemy || temp.getID()==ID.SmartEnemy){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.HEALTH-=2;
                }
            }
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    };

    public void tick() {
        x+= velX;
        y+= velY;
        x=Game.clamp(x,Game.WIDTH-32,0);
        y=Game.clamp(y,Game.HEIGHT-60,0);

        collision();

    }


    protected void render(Graphics g) {
        if(id==ID.Player){
            g.setColor(Color.white);
        }
        else if(id==ID.Player2){
            g.setColor(Color.blue);
        }

        g.fillRect(x,y,32,32);
    }
}
