package site.jaking.ui;

import site.jaking.properties.GlobalProperties;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DrawBoardPanel drawBoardPanel;
    private InstructionPanel instructionPanel;

    public MainFrame() throws HeadlessException {
        //1. 设置基础属性
        super.setLayout(null);
        super.setTitle("画板");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBackground(new Color(60, 63, 65));
        super.getContentPane().setBackground(new Color(43, 43, 43));
        super.setResizable(false);
//        super.setIconImage(new ImageIcon());

        //2. 设置宽高
        super.setBounds(0,0, GlobalProperties.DEFAULT_WIDTH,GlobalProperties.DEFAULT_HEIGHT);

        //3. 显示画板
        this.drawBoardPanel = new DrawBoardPanel();
        super.getContentPane().add(drawBoardPanel);

        //4. 输入框
        this.instructionPanel = new InstructionPanel(drawBoardPanel.getPicturePanel());
        super.getContentPane().add(instructionPanel);

        //5. 显示窗口
        super.setVisible(true);
    }

    public void autoSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (screenSize.getHeight() / 1.2);
        int width = (int) (screenSize.getWidth() / 2.5);
    }

}
