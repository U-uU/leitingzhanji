package cn.tx;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    int x, y;
    int width = 20, height = 20;
    int speed = 10;
    Image img = new ImageIcon("sucai/plane/300340.png").getImage();

    public Bullet() {
    }

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
