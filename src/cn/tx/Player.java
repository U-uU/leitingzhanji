package cn.tx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter {

    GameFrame frame;
    public Player(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // System.out.println(keyCode);
        switch (keyCode) {
            case 37, 65:
                frame.heroPlane.left = true;
                break;
            case 38, 87:
                frame.heroPlane.up = true;
                break;
            case 39, 68:
                frame.heroPlane.right = true;
                break;
            case 40, 83:
                frame.heroPlane.down = true;
                break;
            case 32:
                addBullet();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37, 65:
                frame.heroPlane.left = false;
                break;
            case 38, 87:
                frame.heroPlane.up = false;
                break;
            case 39, 68:
                frame.heroPlane.right = false;
                break;
            case 40, 83:
                frame.heroPlane.down = false;
                break;

        }
    }

    public void addBullet(){
        frame.bullets.add(new Bullet(frame.heroPlane.x+9, frame.heroPlane.y-10));
    }
}
