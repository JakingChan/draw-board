package site.jaking.ui;

import site.jaking.properties.GlobalProperties;
import site.jaking.util.LineNumberHeaderView;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class InstructionPanel extends JPanel {

    private PicturePanel picturePanel;

    public InstructionPanel(PicturePanel picturePanel) {
        this.picturePanel = picturePanel;
        //1.设置绝对布局
        super.setBounds(
                0,
                GlobalProperties.DRAW_BOARD_PANEL_HEIGHT,
                GlobalProperties.INSTRUCTION_PANEL_WIDTH,
                GlobalProperties.INSTRUCTION_PANEL_HEIGHT);

        this.setLayout(new BorderLayout());
        super.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        super.setBorder(BorderFactory.createTitledBorder("控制台"));
        super.setBackground(new Color(43, 43, 43));

        //2.添加组件
        console();
    }


    public void console() {
        JTextField input;
        JTextArea history;
        input = new JTextField();
        input.setFocusTraversalKeysEnabled(false);
        super.add(input, BorderLayout.NORTH);

        JScrollPane resultPanel = new JScrollPane(history = new JTextArea(10, 10));
        ((DefaultCaret) history.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        history.setEditable(false);
        resultPanel.setRowHeaderView(new LineNumberHeaderView());
        this.add(resultPanel);

        input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));


        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // 命令框中按下回车键的事件
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = input.getText();
                    input.setText("");
                    if (text.equalsIgnoreCase("clean")) {
                        history.setText("");
                    } else if (text.equalsIgnoreCase("empty")) {
                        picturePanel.empty();
                        history.setText("");
                    } else if (text.contains("line")) {
                        List<Integer> indexs = Arrays.stream(text.split(" ")[1].split(",")).mapToInt((s) -> {
                            return Integer.valueOf(s);
                        }).boxed().collect(Collectors.toList());
                        picturePanel.drawLine(indexs);

                        String text1 = history.getText();
                        history.setText(text + "\n" + text1);
                    } else {
                        String text1 = history.getText();
                        history.setText(text + "\n" + text1);
                    }
                }
            }
        });
    }
}
