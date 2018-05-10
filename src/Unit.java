import res.ImageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Unit implements KeyListener, Runnable {
    boolean kup = false;
    boolean kdown = false;
    boolean kleft = false;
    boolean kright = false;

    public Image unit = ImageResource.UNIT_IMAGE.getImageIcon().getImage();
    int x, y;

    Thread th;

    Unit(){
        init();
        start();
    }

    public void init(){
        x = 100; y = 100;
    }
    public void start(){
        addKeyListener(this);
        th = new Thread(this);
        th.start();
    }
    public void run(){
        try{
            while(true){
                KeyProcess();
                Thread.sleep(20);
            }
        }catch (Exception e){}
    }
    public void keyPressed(KeyEvent e){

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                kup = true;
                break;
            case KeyEvent.VK_DOWN :
                kdown = true;
                break;
            case KeyEvent.VK_LEFT :
                kleft = true;
                break;
            case KeyEvent.VK_RIGHT :
                kright = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e){


        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                kup = false;
                break;
            case KeyEvent.VK_DOWN :
                kdown = false;
                break;
            case KeyEvent.VK_LEFT :
                kleft = false;
                break;
            case KeyEvent.VK_RIGHT :
                kright = false;
                break;
        }
    }
    public void keyTyped(KeyEvent e){}

    public void KeyProcess(){

        if(kup)  y -= 5;
        if(kdown) y += 5;
        if(kleft)  x -= 5;
        if(kright) x += 5;

    }
}
