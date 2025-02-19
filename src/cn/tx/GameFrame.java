package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
    HeroPlane heroPlane;
//    HeroPlane heroPlane1;
    final Vector<Bullet> bullets = new Vector<>();
    Vector<EnemyPlane> enemies = new Vector<>();
    GameFrame frame;

    public GameFrame() {
        frame = this;
        heroPlane = new HeroPlane();
        new Thread(heroPlane).start();
//        heroPlane1 = new HeroPlane(200, 600, 20, 20);
//        new Thread(heroPlane1).start();
        this.setSize(480, 1000);
        this.setTitle("雷霆战机");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //绘制地图
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        // 生成敌机
        new Thread(new Runnable() {
            Random r = new Random();
            @Override
            public void run() {
                while(true) {
                    EnemyPlane enemy = new EnemyPlane(frame, r.nextInt(430), 0);
                    new Thread(enemy).start();
                    enemies.add(enemy);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
    public void paint(Graphics g){
        //System.out.println("绘制画板");
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics bi = image.getGraphics();

        bi.drawImage(new ImageIcon("sucai/plane/MAP02_01.png").getImage(), 0, 0 ,null);
        bi.drawImage(heroPlane.img, heroPlane.x, heroPlane.y, heroPlane.width, heroPlane.height, null);
//        bi.drawImage(heroPlane1.img, heroPlane1.x, heroPlane1.y, heroPlane1.width, heroPlane1.height, null);
        synchronized (bullets) {
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                if (bullet.y > 0) {
                    bi.drawImage(bullet.img, bullet.x, bullet.y-=bullet.speed, null);
                }
                else {
                    bullets.remove(i);
                    //i--;
                }
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            EnemyPlane enemy = enemies.get(i);
            if (enemy.y < 1000) {
                bi.drawImage(enemy.img, enemy.x, enemy.y+=enemy.speed, enemy.width, enemy.height, null);
            }
            else {
                enemies.remove(enemy);
            }
        }

        g.drawImage(image, 10, 0, null);
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
//        Player player1 = new Player(frame.heroPlane1);
        frame.addKeyListener(player);
//        frame.addKeyListener(player1);
    }
}
