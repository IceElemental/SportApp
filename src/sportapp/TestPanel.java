/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class TestPanel extends JPanel {
        public TestPanel(String text)
        {
            this.setSize(300,400);
            this.setBorder(new LineBorder(Color.DARK_GRAY));
            this.setLayout(null);
            JLabel text1 = new JLabel(text);
            this.add(text1);
            text1.setSize(100, 40);
            text1.setLocation(115,15);
        }
}
