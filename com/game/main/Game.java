package com.game.main;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements  Runnable{
    private static final long serialVersionUID = -2379650440236445399L;//don't care
    Thread thread;//single threaded game
    private boolean running = false;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9; //12:9 res 640x480
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    public enum  STATE {
        Menu,
        Help,
        Game,
        End
    };


    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Game title", this);

        spawner = new Spawn(handler, hud);

        r = new Random();

        if(gameState == STATE.Game){
            handler.addObj(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player, handler));

            handler.addObj(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
        }else{
            for(int i=0; i<20; i++){
                handler.addObj(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }


//        for(int i=0; i<=2;i++){
//            handler.addObj(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.BasicEnemy,handler));
//        }

    }


    public synchronized void start(){//this starts up the thread
        if(running){
            return;
        }//failsafe method, stops the game from running more than 1 at a time

        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        if(!running){
            return;
        }//failsafe

        try{
            thread.join();//kills the thread
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){//creates a funct like void Update() in unity, basically a game loop that updates the game frame every millisecond
        /* the whole shithole down here is basically an optimized game update algorithm*/

        this.requestFocus();//so you don't have to click the screen in order to get key input
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; //10^9
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();//----------
                delta--;
            }
            if(running)
                render();//----------
            frames++;

            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }


        stop();
    }
    private void tick(){
        handler.tickHandler();
        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0) {
                HUD.HEALTH = 100;
                gameState = STATE.End;
                handler.clearEnemys();

            }

        }else if(gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }

    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();//paintbrush named g
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.renderHandler(g);

        if(gameState == STATE.Game){
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

        bs.show();
        g.dispose();
    }

    public static int clamp(float var, float max, float min){
        if(var>=max){
            return (int) (var=max);
        }
        else if(var<=min){
            return (int) (var=min);
        }
        else{
            return (int) var;
        }
    }


    public static void main(String args[]){
        new Game();
    }

}
