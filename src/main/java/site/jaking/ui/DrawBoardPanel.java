package site.jaking.ui;

import site.jaking.properties.GlobalProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawBoardPanel extends JPanel {

    private PicturePanel picturePanel;

    public PicturePanel getPicturePanel() {
        return picturePanel;
    }

    public DrawBoardPanel() {
        //1.设置绝对布局
        super.setBounds(0, 0,
                GlobalProperties.DRAW_BOARD_PANEL_WIDTH,
                GlobalProperties.DRAW_BOARD_PANEL_HEIGHT
        );
        super.setBackground(new Color(43,43,43));
        super.setBorder(BorderFactory.createTitledBorder("图像"));
        this.setLayout(new BorderLayout());

        //2.添加组件
        //2.1 坐标显示组件

        JTextField textField = new JTextField("X:250 Y:250");
        textField.setBackground(new Color(43,43,43));
        textField.setEditable(false);
//        textField.setBounds(0,0,700,30);
        super.add(textField);
        super.add(textField,BorderLayout.SOUTH);

        //2.2 画布
        this.picturePanel = new PicturePanel();
//        picturePanel.setBounds(0,50,600,600);


        picturePanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point point = e.getPoint();
                textField.setText("X="+point.getX()+" Y="+point.getY());
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                textField.setText("X="+point.getX()+" Y="+point.getY());
            }
        });
        picturePanel.setBackground(Color.WHITE);
        super.add(picturePanel,BorderLayout.CENTER);

    }









}
