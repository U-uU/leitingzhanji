package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class EnemyPlane implements Runnable {
    public GameFrame gf;
    int x, y;
    int width = 40, height =  40;
    int speed = 2;
    Image img = new ImageIcon("sucai/plane/10021.png").getImage();

    public EnemyPlane() {
    }

    public EnemyPlane(GameFrame gf, int x, int y) {
        this.gf = gf;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (true) {
            if (hit()) {
                System.out.println("被击毁");
                this.speed = 0;
                this.img = new ImageIcon("sucai/plane/300350.png").getImage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gf.enemies.remove(this);
                break;
            }
            if (this.y > 1000) {
                break;
            }
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hit() {
        Rectangle enemyRect = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle rect = null;
        synchronized (gf.bullets) {
            Iterator<Bullet> iterator = gf.bullets.iterator(); while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                rect = new Rectangle(bullet.x - 1, bullet.y - 1, bullet.width + 2, bullet.height + 2);

                if (enemyRect.intersects(rect)) {
                    iterator.remove();
                    return true;
                }
            }
        }
//        synchronized (gf.bullets) {
//            for (int i = gf.bullets.size() - 1; i >= 0; i--) {
//                if (gf.bullets.size() == 0) {
//                    System.out.println(i + "\twsm\t" + gf.bullets.size());
//                    //没有子弹，for时候有，进来没有了，取不到子弹？？？？？
//                }
//                if (i >= 0) {
//                    Bullet bullet = gf.bullets.get(i);
//                    Rectangle rect = new Rectangle(bullet.x - 1, bullet.y - 1, bullet.width + 2, bullet.height + 2);
//                    if (enemyRect.intersects(rect)) {
//                        gf.bullets.remove(i);
//                        return true;
//                    }
//                }
//            }

        return false;
    }
}
