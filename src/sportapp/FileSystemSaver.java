/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author VMS
 */
public class FileSystemSaver extends JFrame {
    private JFrame windowDialog;
    private JComboBox rootChoser;
    private JList fileList;
    private JScrollPane masterList;
    private JButton saveHere, upperDir;
    private JTextField varFileName;
    private JLabel whereToSave;
    private JPanel mainPanel;
    private Border solidBlack = new LineBorder(Color.DARK_GRAY);
    
    private static String currentRoot;
    private static int currentRootIndex;
    private static File currDir;
    private static File[] sysRoots = File.listRoots();
    private String[] rootMass;
    private String[] fileMass;// = {"qqq", "www", "eee", "rrr", "ttt", "yyy"};
    private String currentDir = System.getProperty("user.dir");
    private static String s = System.getProperty("file.separator");
    private File fileSystemIFace = new File(currentDir);
    private int margin = 15, textHeight = 30, windowWidth = 600, windowHeight = 400, upperDirHeight = 20, listHeight = 230, fileNameWidth = 450, buttonWidth = 100, rootChoserWidth = 60;
    private Dimension winSize = new Dimension(windowWidth, windowHeight);
    
    
    public FileSystemSaver()
    {
        super("Превед!");
        this.setSize(winSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        makeFileList();
        makeRootList();
                
        mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setLayout(null);
        
        whereToSave = new JLabel("Куда сохранять будем?");
        mainPanel.add(whereToSave);
        whereToSave.setSize((windowWidth - margin - margin - 5), textHeight);
        whereToSave.setLocation(margin, margin);
        whereToSave.setVerticalAlignment(JLabel.CENTER);
        whereToSave.setHorizontalAlignment(JLabel.CENTER);
        whereToSave.setBorder(solidBlack);
        mainPanel.add(whereToSave);
        
        upperDir = new JButton("..");
        mainPanel.add(upperDir);
        upperDir.setSize((windowWidth - margin - margin - 5), upperDirHeight);
        upperDir.setLocation(margin, (margin + margin + textHeight));
        upperDir.setHorizontalAlignment(JButton.LEFT);
        upperDir.setMargin(new Insets(1,1,1,1));
        
        fileList = new JList(fileMass);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileList.setLayoutOrientation(JList.VERTICAL);
        
        masterList = new JScrollPane(fileList);
        mainPanel.add(masterList);
        masterList.setSize((windowWidth - margin - margin - 5), listHeight);
        masterList.setLocation(margin, (margin + margin + upperDirHeight + textHeight));
                
        varFileName = new JTextField();
        mainPanel.add(varFileName);
        varFileName.setSize(fileNameWidth-rootChoserWidth-margin, textHeight);
        varFileName.setLocation(margin+margin+rootChoserWidth, (textHeight + margin + margin + margin + upperDirHeight + listHeight));
        
        rootChoser = new JComboBox(rootMass);
        mainPanel.add(rootChoser);
        rootChoser.setSize(rootChoserWidth, textHeight);
        rootChoser.setLocation(margin, (textHeight + margin + margin + upperDirHeight + margin + listHeight));
        rootChoser.setSelectedIndex(currentRootIndex);
        
        saveHere = new JButton("Сохранить");
        mainPanel.add(saveHere);
        saveHere.setSize(buttonWidth, textHeight);
        saveHere.setLocation((margin + fileNameWidth + margin), (textHeight + margin + margin + upperDirHeight + margin + listHeight));
        
        upperDir.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if ( (new File(currentDir).getParent()) == null )
                {
                    
                }
                else
                {
                    currentDir = new File(currentDir).getParent();
                    fileSystemIFace = new File(currentDir);
                    makeFileList();
                    fileList.setListData(fileMass);
                }
            }
            
        }
        );
        ActionListener rootListener = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                if (currentDir.startsWith(item)) { }
                else 
                { 
                    try {
                        currentDir = item;                        
                        fileSystemIFace = new File(currentDir);
                        makeFileList();
                        fileList.setListData(fileMass);
                    } 
                    catch (NullPointerException noDisk) 
                    {
                        JOptionPane.showMessageDialog(null, "Диск " + item + " недоступен");
                    } 
                    catch (SecurityException secErr)
                    {
                        JOptionPane.showMessageDialog(null, "Доступ запрещён");
                    }
                }
            }
        };
        rootChoser.addActionListener(rootListener);
        
        MouseListener mouseClicker = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) 
            {
                try {
                    if (e.getClickCount() == 2) {
                        String bufferFilename = fileList.getSelectedValue().toString();
                        if (fileMass.length == 0) {
                            
                        } else if (!(new File(currentDir + s + bufferFilename)).isDirectory()) {
                            varFileName.setText(bufferFilename);
                        }
                        if ((new File(currentDir + s + bufferFilename)).isDirectory()) {
                            varFileName.setText("");
                            currentDir = currentDir + s + bufferFilename;
                            fileSystemIFace = new File(currentDir);
                            makeFileList();
                            fileList.setListData(fileMass);
                        };
                    }
                } 
                catch (NullPointerException except) { } 
                catch (SecurityException secErr)
                {
                    JOptionPane.showMessageDialog(null, "Доступ запрещён");
                }
            }
        };
        fileList.addMouseListener(mouseClicker);
    }
    private static String[] fileListSorter(String[] fileMass, File currDir)
    {
        int allCount = fileMass.length, fileCount = 0, dirCount = 0;
        String[] resList = new String[fileMass.length];
        String buff = "";
        boolean sorterFlag = true;
        
        for (int i = 0; i < allCount; i++)
        {
            File currentFile = new File(currDir + s + fileMass[i]);
            if (currentFile.isDirectory()) 
            { 
                resList[dirCount] = fileMass[i];
                dirCount++;
            }
            else 
            {
                resList[fileMass.length-fileCount-1] = fileMass[i];
                fileCount++; 
            }
        }
        
        if (dirCount > 1) 
        {
            while (sorterFlag) {
                sorterFlag = false;
                for (int i = 0; i < dirCount - 1; i++) {
                    if (resList[i].compareTo(resList[i + 1]) > 0) {
                        buff = resList[i];
                        resList[i] = resList[i + 1];
                        resList[i + 1] = buff;
                        sorterFlag = true;
                    }
                }
            }
        }
        sorterFlag = true;
        
        if (fileCount > 1)
        {
        
            while (sorterFlag)
            {
                sorterFlag = false;
                for (int i = dirCount; i < resList.length-1; i++)
                {
                    if (resList[i].compareTo(resList[i+1]) > 0)
                    {
                        buff = resList[i];
                        resList[i] = resList[i+1];
                        resList[i+1] = buff;
                        sorterFlag = true;
                    }
                }
            }
        }
        return resList;
    }

    private void makeFileList() {
        fileMass = fileSystemIFace.list();
        fileMass = fileListSorter(fileMass, fileSystemIFace);
    }

    private void makeRootList() {
        rootMass = new String[sysRoots.length];
        currDir = new File(currentDir);
        while (!(currDir.getParent() == null))
        {
            currentRoot = currDir.getParent();
            currDir = new File(currDir.getParent());
        }
        for (int i = 0; i < sysRoots.length; i++)
        {
            rootMass[i] = sysRoots[i].toString();
            if (rootMass[i].equals(currentRoot)) { currentRootIndex = i; }
        }
    }
}