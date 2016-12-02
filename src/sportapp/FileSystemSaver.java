/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class FileSystemSaver extends JFrame {
    private JFrame windowDialog;
    private JList fileList;
    private JScrollPane masterList;
    private JButton saveHere;
    private JTextField varFileName;
    private JLabel whereToSave;
    private JPanel mainPanel;
    private Border solidBlack = new LineBorder(Color.DARK_GRAY);
    
    private String[] fileMass = {"qqq", "www", "eee", "rrr", "ttt", "yyy"};
    private String currentDir;
    private File fileSystemReader;
    private int margin = 15, textHeight = 30, windowWidth = 600, windowHeight = 400, listHeight = 250, fileNameWidth = 450, buttonWidth = 100;
    private Dimension winSize = new Dimension(windowWidth, windowHeight);
    
    public FileSystemSaver()
    {
        super("Превед!");
        this.setSize(winSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setLayout(null);
        //mainPanel.setSize(winSize);
        
        whereToSave = new JLabel("Куда сохранять будем?");
        mainPanel.add(whereToSave);
        whereToSave.setSize((windowWidth - margin - margin - 5), textHeight);
        whereToSave.setLocation(margin, margin);
        whereToSave.setVerticalAlignment(JLabel.CENTER);
        whereToSave.setHorizontalAlignment(JLabel.CENTER);
        whereToSave.setBorder(solidBlack);
        mainPanel.add(whereToSave);
        
        fileList = new JList(fileMass);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileList.setLayoutOrientation(JList.VERTICAL);
        
        masterList = new JScrollPane(fileList);
        mainPanel.add(masterList);
        masterList.setSize((windowWidth - margin - margin - 5), listHeight);
        masterList.setLocation(margin, (margin + margin + textHeight));
        
        
        varFileName = new JTextField();
        mainPanel.add(varFileName);
        varFileName.setSize(fileNameWidth, textHeight);
        varFileName.setLocation(margin, (textHeight + margin + margin + margin + listHeight));
        
        saveHere = new JButton("Сохранить");
        mainPanel.add(saveHere);
        saveHere.setSize(buttonWidth, textHeight);
        saveHere.setLocation((margin + fileNameWidth + margin), (textHeight + margin + margin + margin + listHeight));
        
    }
}