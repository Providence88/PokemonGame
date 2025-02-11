import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {

    private JFrame mainFrame;
    private JLabel greetingsLbl;
    private JLabel alignmentLbl;

    public MainView() {
        this.mainFrame = new JFrame("My Simple GUI");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(280, 200);

        this.mainFrame.setVisible(true);

        initializeGreetingElements();
        this.mainFrame.setVisible(true);

        initializeAlignmentElements();
        initializeSwappingElements();
        this.mainFrame.setVisible(true);
    }

    private void initializeGreetingElements() {
        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Name: ");

        JTextField greetingNameTf = new JTextField();
        greetingNameTf.setColumns(10);
        
        JButton greetingBtn = new JButton("Submit");
        greetingBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            greetingsLbl.setText("Hello " + greetingNameTf.getText() + "!");
            }
        });

        this.greetingsLbl = new JLabel(" ");
        this.mainFrame.add(greetingsPromptLbl);
        this.mainFrame.add(greetingNameTf);
        this.mainFrame.add(greetingBtn);
        this.mainFrame.add(greetingsLbl);
    }

    private void initializeAlignmentElements() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.red);

        JButton btn1 = new JButton("Left");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alignmentLbl.setText("Left");
                alignmentLbl.setHorizontalAlignment(JLabel.LEFT);
            }
        });

        JButton btn2 = new JButton("Center");
        btn2.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                alignmentLbl.setText("Center");
                alignmentLbl.setHorizontalAlignment(JLabel.CENTER);
            }
        });

        JButton btn3 = new JButton("Right");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alignmentLbl.setText("Right");
                alignmentLbl.setHorizontalAlignment(JLabel.RIGHT);
            }
        });

        this.alignmentLbl = new JLabel(" ");

        panel.add(btn1, BorderLayout.WEST);
        panel.add(btn2, BorderLayout.CENTER);
        panel.add(btn3, BorderLayout.EAST);
        panel.add(alignmentLbl, BorderLayout.SOUTH);

        this.mainFrame.add(panel);
    }

    private void initializeSwappingElements() {
        JButton btn = new JButton("Yin");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if(btn.getText().equals("Yin")) {
                    btn.setText("Yang");
                } else if(btn.getText().equals("Yang")) {
                    btn.setText("Yin");
                }
            }
        });

        this.mainFrame.add(btn);
    }
}
