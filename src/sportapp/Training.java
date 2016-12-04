/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author VMS
 */
public class Training extends JFrame {
    private JPanel trainPanel, fffuPanel;
    private JButton close;    
    
    public Training()
    {
        super("Вротмненоги");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
