package com.game.main;

import java.awt.*;

public class Trail extends GameObject{
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width,height;
    private float life;
    public Trail(int x, int y, ID id, Color color,int width, int height, float life, Handler handler){
        super(x,y,id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }
    private AlphaComposite makeTrans(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }
    public void tick() {
        if(alpha>life){
            alpha -= (life - 0.00001f);
        }
        else handler.removeObj(this);
    }

    protected void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTrans(alpha));
        g.setColor(color);
        g.fillRect(x,y,width,height);

        g2d.setComposite(makeTrans(1));
    }

    public Rectangle getBounds() {
        return null;
    }
}
