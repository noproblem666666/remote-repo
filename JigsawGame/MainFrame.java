package JigsawGame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener, ActionListener {
    private final int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};     //放在成员位置，方便其他方法调用
    private int[][] num1 = new int[4][4];
    int x = 0;             //x y记录空白块在二位数组中的位置
    int y = 0;
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    String path = "image\\animal\\animal3\\";
    int step = 0;
    JMenuItem rePlay = new JMenuItem("重新游戏");
    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem exitGame = new JMenuItem("退出游戏");
    JMenuItem account = new JMenuItem("公众号");
    public MainFrame() {
        initJFrame();
        initJMenu();
        initFigure();
        initImage();
        this.setVisible(true);    //建议写在最后，否则可能会有某些组件加载不出来
    }

    private void initFigure() {

        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(num.length);
            int temp = num[index];
            num[index] = num[i];
            num[i] = temp;
        }
        for (int i = 0; i < 16; i++) {
            if (num[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            num1[i / 4][i % 4] = num[i];    //如果0不赋值，可能重新打乱时会丢失


        }

    }

    private void initImage() {        //先加载的图片会在上方，后加载的图片会在下面!!!
        this.getContentPane().removeAll();   //每次更新图片时都需要把之前的图片都清理掉

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon imageIcon = new ImageIcon(path + num1[i][j] + ".jpg");
                JLabel jLabel = new JLabel(imageIcon);
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);

            }
        }

        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();       //刷新一下界面
    }

    private void initJFrame() {
        this.setSize(603, 680);

        this.setTitle("拼图游戏");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
    }

    private void initJMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");

        functionMenu.add(rePlay);
        functionMenu.add(reLogin);
        functionMenu.add(exitGame);

        aboutMenu.add(account);

        rePlay.addActionListener(this);
        reLogin.addActionListener(this);
        exitGame.addActionListener(this);
        account.addActionListener(this);

        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint(); //别忘了加这个，不然界面不会刷新
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()) {
            return;
        }

        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 37) {
            if (y == 3) {
                return;
            }
            num1[x][y] = num1[x][y + 1];
            num1[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38) {
            if (x == 3) {
                return;
            }
            num1[x][y] = num1[x + 1][y];
            num1[x + 1][y] = 0;
            x++;
            step++;
            initImage();

        } else if (code == 39) {
            if (y == 0) {
                return;
            }
            num1[x][y] = num1[x][y - 1];
            num1[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            if (x == 0) {
                return;
            }
            num1[x][y] = num1[x - 1][y];
            num1[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            num1 = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < num1.length; i++) {
            for (int i1 = 0; i1 < num1[i].length; i1++) {
                if (num1[i][i1] != win[i][i1])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if(object==rePlay){
            initFigure();  //再次打乱图片
            step = 0;
            initImage();
        }else if(object == reLogin){
            this.setVisible(false);  //关闭当前界面
            new LoginFrame();        //打开登录界面
        }else if(object == exitGame){
            System.exit(0);
        }else if(object == account){
            JDialog jDialog =new JDialog();
            JLabel jLabel =new JLabel(new ImageIcon("image/about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);   //让弹框居中
            jDialog.setModal(true); //弹框不关闭则无法操作下面的界面
            jDialog.setVisible(true);
        }
    }
}
