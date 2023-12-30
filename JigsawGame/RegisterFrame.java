package JigsawGame;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame(){
        initJFrame();
    }

    private void initJFrame() {
        this.setSize(488,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setTitle("拼图注册");
        this.setLocationRelativeTo(null);
    }
}
