package JigsawGame;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame(){
        initJFrame();

    }


    private void initJFrame() {
        this.setSize(488,430);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setTitle("拼图登录");
        this.setLocationRelativeTo(null);
    }
}
