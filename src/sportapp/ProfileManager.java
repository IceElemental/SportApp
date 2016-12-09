/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportapp;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class ProfileManager extends JPanel{
    private static JButton createProfile, selectProfile, deleteProfile, backToMainMenu, cancelAndBack;
    private static JLabel selectedProfile;
    private static JPanel profilePanel;
    private static int margin = 15, componentHeight = 30, componentWidth = 200, width = 200, height = 250, paddingLeft = 60, paddingTop = 40;
    private static LineBorder lineBorder = new LineBorder(Color.DARK_GRAY);
    private static ArrayList<String> profileList = new ArrayList<>();
    private static File bufferProfile;
    static
    {
        for (File buf : SportApp.getProfileDir().listFiles())
        {
            if (buf.isDirectory())
            {
                profileList.add(buf.toString());
            }
        }
    }
    
    protected ProfileManager()
    {
        super();
        System.out.println(profileList);
        profilePanel = this;
        profilePanel.setLayout(null);
        profilePanel.setPreferredSize(new Dimension(width,height));
        
        selectedProfile = new JLabel("Kurva profile");
        profilePanel.add(selectedProfile);
        selectedProfile.setSize(componentWidth, componentHeight);
        selectedProfile.setLocation(paddingLeft + margin, paddingTop + margin);
        selectedProfile.setBorder(lineBorder);
        selectedProfile.setHorizontalAlignment(JLabel.CENTER);
        
        selectProfile = new JButton("Выбрать профиль");
        profilePanel.add(selectProfile);
        selectProfile.setSize(componentWidth, componentHeight);
        selectProfile.setLocation(paddingLeft + margin, paddingTop + margin*2 + componentHeight);
        selectProfile.setBorder(lineBorder);
        selectProfile.setHorizontalAlignment(JButton.CENTER);
        
        createProfile = new JButton("Создать профиль");
        profilePanel.add(createProfile);
        createProfile.setSize(componentWidth, componentHeight);
        createProfile.setLocation(paddingLeft + margin, paddingTop + margin*3 + componentHeight*2);
        createProfile.setBorder(lineBorder);
        createProfile.setHorizontalAlignment(JButton.CENTER);
        
        deleteProfile = new JButton("Удалить профиль");
        profilePanel.add(deleteProfile);
        deleteProfile.setSize(componentWidth, componentHeight);
        deleteProfile.setLocation(paddingLeft + margin, paddingTop + margin*4 + componentHeight*3);
        deleteProfile.setBorder(lineBorder);
        deleteProfile.setHorizontalAlignment(JButton.CENTER);
    }
    
    protected static String getCurrentProfile()
    {
        return selectedProfile.getText();
    }
    
}
