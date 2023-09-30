package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();
    public Menu(Game game, Handler handler){

        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == STATE.Menu) {
            // play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Game;
                handler.addObj(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));

                handler.addObj(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
            }

            // help button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = STATE.Help;
            }

            // quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);
            }
        }

        // back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 360, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }


    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width ){
            if(my > y && my < y + height){
                return true;
            }
            else return false;
        }else return false;
    }

    public void tick() {

    }
    public void render(Graphics g){

        if(game.gameState == STATE.Menu){
            Font fnt  = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.setFont(fnt2);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        }
        else if( game.gameState == STATE.Help){
            Font fnt  = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt2);
            g.drawString("This is advice from Nguyen Kim Dung: ", 40,130);

            g.setFont(fnt3);
            g.drawString(" + Use WASD to move player.", 100,180);
            g.drawString(" + Be careful with enemies.", 100,210);
            g.drawString(" + You need to get 200 points to pass each level.", 100,240);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }

    }
}
