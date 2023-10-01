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
    private HUD hud;
    public Menu(Game game, Handler handler, HUD hud){

        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == STATE.Menu) {
            // play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Select;
                return;
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

        if(game.gameState == STATE.Select) {
            // normal button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Game;
                handler.addObj(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
                handler.clearEnemys();
                handler.addObj(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
                game.diff = 0;
            }
            // hard button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = STATE.Game;
                handler.addObj(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
                handler.clearEnemys();
                handler.addObj(new HardEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
                game.diff = 1;
            }

            // back button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }

        // back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 360, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }

        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 360, 200, 64)){
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
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
            g.setColor(Color.CYAN);
            g.drawString("Welcome to our game", 65, 70);

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
            g.drawString("This is our advice : ", 150,130);

            g.setFont(fnt3);
            g.drawString(" + Use WASD to move player.", 100,180);
            g.drawString(" + Be careful with enemies.", 100,210);
            g.drawString(" + You need to get 200 points to pass each level.", 100,240);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
        else if( game.gameState == STATE.End){
            Font fnt  = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game over !", 175, 70);

            g.setFont(fnt3);
            g.drawString("Your score:  " + hud.getScore(), 240,210);

            g.setFont(fnt3);
            g.drawRect(225, 350, 180, 58);
            g.drawString("Try Again ", 270, 385);
        } else if(game.gameState == STATE.Select){
            Font fnt  = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.CYAN);
            g.drawString("Select Difficulty", 65, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);

            g.setFont(fnt2);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }

    }
}
