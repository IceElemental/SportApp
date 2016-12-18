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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author VMS
 */
public class TrainingManager extends JPanel {
    private static JButton createTraining, selectTraining, deleteTraining, backToMainMenu, cancelAndBack;
    private static JLabel selectedTraining;
    private static JPanel profileTraining;
    private static int margin = 15, componentHeight = 30, componentWidth = 200, width = 200, height = 250, paddingLeft = 60, paddingTop = 40;
    private static int intTrainingType, repeatCount = 0;
    private static LineBorder lineBorder = new LineBorder(Color.DARK_GRAY);
    private static String trainingName;
//    private static final String exSeparator = " _!_!_ ";
    private static ArrayList<String> trainingList = new ArrayList<>();
    private static Object[] bufTrainingList;
    
//    protected static final String FREE_WEIGHT = "Тренировка с отягощением";
//    protected static final String NO_WEIGHT = "Тренировка без отягощения";
//    protected static final String TIME_TRAINING = "Тренировка на время";
    private static Object[] exerciseList;
    
    private static File bufferTraining;   
    private static TrainingButtonListener trainingButtonListener = new TrainingButtonListener();
    
    private static void buildExerciseList() {
            exerciseList = SportApp.getExerciseList();  
    }
        
    private static void buildTrainingList() {
        if (trainingList.size() != 0 )
        {
            trainingList.clear();
        }
        for (File buf : MasterWindow.getTrainingDir().listFiles())
        {
            trainingList.add((buf.toString()).substring ( (buf.toString().lastIndexOf(System.getProperty("file.separator")) +1 ), buf.toString().length() ));
        }
        bufTrainingList = trainingList.toArray();
    }
    
    protected TrainingManager()
    {
        super();
        buildExerciseList();
        resetValues();
        
        trainingName = "не выбрано";
        
        profileTraining = this;
        profileTraining.setLayout(null);
        profileTraining.setPreferredSize(new Dimension(width,height));
        
        selectedTraining = new JLabel(trainingName);
        profileTraining.add(selectedTraining);
        selectedTraining.setSize(componentWidth, componentHeight);
        selectedTraining.setLocation(paddingLeft + margin, paddingTop + margin);
        selectedTraining.setBorder(lineBorder);
        selectedTraining.setHorizontalAlignment(JLabel.CENTER);
        
        
        selectTraining = new JButton("Выбрать тренировку");
        profileTraining.add(selectTraining);
        selectTraining.setSize(componentWidth, componentHeight);
        selectTraining.setLocation(paddingLeft + margin, paddingTop + margin*2 + componentHeight);
        selectTraining.setBorder(lineBorder);
        selectTraining.setHorizontalAlignment(JButton.CENTER);
        selectTraining.addActionListener(trainingButtonListener);
        
        createTraining = new JButton("Создать тренировку");
        profileTraining.add(createTraining);
        createTraining.setSize(componentWidth, componentHeight);
        createTraining.setLocation(paddingLeft + margin, paddingTop + margin*3 + componentHeight*2);
        createTraining.setBorder(lineBorder);
        createTraining.setHorizontalAlignment(JButton.CENTER);
        createTraining.addActionListener(trainingButtonListener);
        
        deleteTraining = new JButton("Удалить тренировку");
        profileTraining.add(deleteTraining);
        deleteTraining.setSize(componentWidth, componentHeight);
        deleteTraining.setLocation(paddingLeft + margin, paddingTop + margin*4 + componentHeight*3);
        deleteTraining.setBorder(lineBorder);
        deleteTraining.setHorizontalAlignment(JButton.CENTER);
        deleteTraining.addActionListener(trainingButtonListener);
    }
    
    protected static String getCurrentTraining()
    {
        return selectedTraining.getText();
    }
    
    public static class TrainingButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == selectTraining)
            {
                buildTrainingList();
                trainingName = (String)JOptionPane.showInputDialog(null, "Выберите тренировку", "Тренировки", JOptionPane.QUESTION_MESSAGE, null, bufTrainingList, null);
                if (trainingName != null) 
                {
                    selectedTraining.setText(trainingName);
                } 
            }
            if (e.getSource() == createTraining)
            {
                String resultExercise = "";
                String inputTrainingName = JOptionPane.showInputDialog(null, "Введите имя новой тренировки");
                if (inputTrainingName != null)
                {
                    bufferTraining = new File((MasterWindow.getTrainingDir().toString()).concat(System.getProperty("file.separator").concat(inputTrainingName)));
                    if (bufferTraining.exists())
                    {
                        JOptionPane.showMessageDialog(null, "Такая Тренировка уже существует");
                    }
                    else {
                        int exerciseCount = Integer.parseInt(JOptionPane.showInputDialog(null, "Сколько упражнений будет в тренировке?"));
                        
                        for (int i = 0; i < exerciseCount; i++) 
                        {
                            repeatCount = 0;
                            String currEx = "Введите имя " + (i + 1) + " упражнения";
                            String exerciseName = JOptionPane.showInputDialog(null, currEx);
                            if (exerciseName != null)
                            {                                
                                String trainingType = (String) JOptionPane.showInputDialog(null, "Выберите тип тренировки", "Тренировки", JOptionPane.QUESTION_MESSAGE, null, exerciseList, null);
                                if (trainingType != null) {
                                    if (trainingType.equals(SportApp.FREE_WEIGHT)) {
                                        intTrainingType = 1;
                                    } else if (trainingType.equals(SportApp.NO_WEIGHT)) {
                                        intTrainingType = 2;
                                    } else {
                                        intTrainingType = 3;
                                        repeatCount = 1;
                                    }
                                    if (repeatCount != 1) {
                                        repeatCount = Integer.parseInt(JOptionPane.showInputDialog(null, "Введите число повторов в упражнении", "Количество повторений", JOptionPane.QUESTION_MESSAGE));
                                    }
                                    
                                    if ((exerciseName != null) && (trainingType != null) && (repeatCount != 0)) 
                                    {
                                        if (i != (exerciseCount - 1) )
                                        {
                                            resultExercise += exerciseName.concat(SportApp.exSeparator).concat(String.valueOf(intTrainingType)).concat(SportApp.exSeparator).concat(String.valueOf(repeatCount).concat(System.getProperty("line.separator")));
                                        }
                                        else
                                        {
                                            resultExercise += exerciseName.concat(SportApp.exSeparator).concat(String.valueOf(intTrainingType)).concat(SportApp.exSeparator).concat(String.valueOf(repeatCount));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    try 
                    {
                        bufferTraining.createNewFile();
                        FileOutputStream fileWriter = new FileOutputStream(bufferTraining);
                        fileWriter.write(resultExercise.getBytes());
                        fileWriter.flush();
                        fileWriter.close();
                        JOptionPane.showMessageDialog(null, "Тренировка успешно создана");
//                        System.out.println(resultExercise);
                        selectedTraining.setText(inputTrainingName);
                    } catch (IOException ex) {
                    }
                }
            }
            if (e.getSource() == deleteTraining)
            {
                buildTrainingList();
                trainingName = (String)JOptionPane.showInputDialog(null, "Выберите тренировку", "Тренировки", JOptionPane.QUESTION_MESSAGE, null, bufTrainingList, null);
                if (trainingName != null) 
                {
                    File killTraining = new File(MasterWindow.getTrainingDir().toString().concat(System.getProperty("file.separator")).concat(trainingName));
                    try
                    {
                        killTraining.delete();
                        selectedTraining.setText("не выбрано");
                    }
                    catch (Exception ex)
                    {
                        
                    }
                } 
            }
        }
    }
    private static void resetValues()
    {
        intTrainingType = 0;
        repeatCount = 0;
    }
}
