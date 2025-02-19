package cn.tx;

import javax.swing.*;
import java.awt.*;

public class HeroPlane implements Runnable{
    int x = 190, y = 600;
    int width = 40, height = 40;
    Image img = new ImageIcon("sucai/plane/10011.png").getImage();
    Boolean up = false, down = false, left = false, right = false;
    int speed = 4;

    public HeroPlane() {
    }

    public HeroPlane(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public HeroPlane(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    @Override
    public void run() {
        System.out.println("hello");
        while (true) {
            if (this.y >= speed && up) {
                y -= speed;
            }
            if (this.y <= 1000-speed && down){
                y += speed;
            }
            if (this.x >= speed && left) {
                x -= speed;
            }
            if (this.x <= 440 - speed && right) {
                x += speed;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
