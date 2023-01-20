package com.yuanbao.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x = 3;
    int y = 3;
    int count = 0;
    int shape = 0;
    String kind = "";

    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0},
    };

    int[][] win1 = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,15},
            {13,14,12,0},
    };

    String path = "image\\animal\\animal3\\";

    JMenuItem rePlayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");



    JMenuItem accountItem = new JMenuItem("公众号");




    public GameJFrame(){
        init();

        initLMenuBar();
        initData();

        initImage();

        this.setVisible(true);
    }


    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
                data[i / 4][i % 4] = tempArr[i];
            }else{
                data[i / 4][i % 4] = tempArr[i];
            }

        }

    }

    private void initImage() {
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);


        }
        JLabel str = new JLabel("步数:" + count);
        str.setBounds(50,30,100,20);
        this.getContentPane().add(str);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + num+".jpg"));
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);

            }
        }

        JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
        backGround.setBounds(40,40,508,560);
        this.getContentPane().add(backGround);
        this.getContentPane().repaint();

    }

    private void initLMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu switchItem = new JMenu("更换图片");


        rePlayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        switchItem.add(girlItem);
        switchItem.add(animalItem);
        switchItem.add(sportItem);
        functionJMenu.add(switchItem);
        functionJMenu.add(rePlayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void init() {
        this.setSize(603,680);
        this.setTitle("拼图单机版v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addKeyListener(this);
    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]){
                    if (data[i][j] != win1[i][j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
            backGround.setBounds(40,40,508,560);
            this.getContentPane().add(backGround);

            this.getContentPane().repaint();

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()){
            return;
        }

        int code = e.getKeyCode();

        if (code == 37){
            if (y < 3) {
                System.out.println("向左");
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                count++;
                initImage();
            }
        }else if (code == 38){
            if (x < 3) {
                System.out.println("向上");
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                count++;
                initImage();
            }
        }else if (code == 39){
            if (y > 0) {
                System.out.println("向右");
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                count++;
                initImage();
            }
        }else if (code == 40){
            if (x > 0) {
                System.out.println("向下");
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                count++;
                initImage();
            }
        }else if (code == 65){
            initImage();
        }else if (code == 89){
            data = win;
            initImage();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        Object obj = e.getSource();
        if (obj == rePlayItem){
            if (!(victory())) {
                System.out.println("重新游戏");
                initData();

                count = 0;

                initImage();
            }
        }else if (obj == reLoginItem){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }else if (obj == closeItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if (obj == accountItem){
            System.out.println("关于");

            JDialog jd = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            jLabel.setBounds(0,0,600,600);
            jd.getContentPane().add(jLabel);
            jd.setSize(700,700);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            jd.setModal(true);
            jd.setVisible(true);
        }else if (obj == girlItem){
            shape = r.nextInt(13);
            path = "image\\girl\\girl"+shape+"\\";
            initData();

            count = 0;

            initImage();
        }else if (obj == animalItem) {
            shape = r.nextInt(8);
            path = "image\\animal\\animal" + shape + "\\";
            initData();

            count = 0;

            initImage();
        }else if (obj == sportItem) {
            shape = r.nextInt(10);
            path = "image\\sport\\sport" + shape + "\\";
            initData();

            count = 0;

            initImage();
        }
    }
}
