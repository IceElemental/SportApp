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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private static String profileName;
    private static Object[] bufProfileList;
    
    static
    {
        buildProfileList();
    }

    private static void buildProfileList() {
        if (profileList.size() != 0 )
        {
            profileList.clear();
        }
        for (File buf : SportApp.getProfileDir().listFiles())
        {
            if (buf.isDirectory())
            {
                profileList.add((buf.toString()).substring ( (buf.toString().lastIndexOf(System.getProperty("file.separator")) +1 ), buf.toString().length() ));
            }
        }
        bufProfileList = profileList.toArray();
    }
    
    protected ProfileManager()
    {
        super();
        
        profileName = SportApp.BLANK_NAME;
        
        ProfileButtonListener profButtonListener = new ProfileButtonListener();
        
//        System.out.println(profileList);
        profilePanel = this;
        profilePanel.setLayout(null);
        profilePanel.setPreferredSize(new Dimension(width,height));
        
        selectedProfile = new JLabel(profileName);
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
        selectProfile.addActionListener(profButtonListener);
        
        createProfile = new JButton("Создать профиль");
        profilePanel.add(createProfile);
        createProfile.setSize(componentWidth, componentHeight);
        createProfile.setLocation(paddingLeft + margin, paddingTop + margin*3 + componentHeight*2);
        createProfile.setBorder(lineBorder);
        createProfile.setHorizontalAlignment(JButton.CENTER);
        createProfile.addActionListener(profButtonListener);
        
        deleteProfile = new JButton("Удалить профиль");
        profilePanel.add(deleteProfile);
        deleteProfile.setSize(componentWidth, componentHeight);
        deleteProfile.setLocation(paddingLeft + margin, paddingTop + margin*4 + componentHeight*3);
        deleteProfile.setBorder(lineBorder);
        deleteProfile.setHorizontalAlignment(JButton.CENTER);
        deleteProfile.addActionListener(profButtonListener);
    }
    
    protected static String getCurrentProfile()
    {
        return selectedProfile.getText();
    }
    
    private static class ProfileButtonListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == selectProfile)
            {
                buildProfileList();
                profileName = (String)JOptionPane.showInputDialog(null, "Выберите профиль", "Профили", JOptionPane.QUESTION_MESSAGE, null, bufProfileList, null);
                if (profileName != null) 
                {
                    selectedProfile.setText(profileName);
                }
            }
            if (e.getSource() == createProfile)
            {
                String inputProfileName = JOptionPane.showInputDialog(null, "Введите имя нового профиля");
                if (inputProfileName != null)
                {
                    bufferProfile = new File((SportApp.getProfileDir().toString()).concat(System.getProperty("file.separator").concat(inputProfileName)));
                    if (!bufferProfile.exists())
                    {
                        bufferProfile.mkdir();
                        JOptionPane.showMessageDialog(null, "Пользователь успешно создан");
                        selectedProfile.setText(inputProfileName);
                    }
                    else { JOptionPane.showMessageDialog(null, "Такой пользователь уже существует");}
                }
            }
            if (e.getSource() == deleteProfile)
            {
                buildProfileList();
                profileName = (String)JOptionPane.showInputDialog(null, "Выберите профиль", "Профили", JOptionPane.QUESTION_MESSAGE, null, bufProfileList, null);
                
                if (profileName != null )
                {
                    bufferProfile = new File((SportApp.getProfileDir().toString()).concat(System.getProperty("file.separator")).concat(profileName));
                    deleteTheProfile(bufferProfile);
                    bufferProfile.delete();
                    selectedProfile.setText(SportApp.BLANK_NAME);
                }
            }
        }
    }
    private static void deleteTheProfile(File selectedProfile)
    {
        File[] fileList = selectedProfile.listFiles();
        for (File buf : fileList)
        {
            if (buf.isDirectory())
            {
                deleteTheProfile(buf);
            }
            buf.delete();
        }
    }
}
