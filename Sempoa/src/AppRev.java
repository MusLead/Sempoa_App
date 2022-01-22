import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppRev implements java.awt.event.ActionListener, KeyListener {
    /**
     * Lakukan sebuah Update:
     * 
     * buat UI dengan GUI!
     * Tutorial: Pelajari Objek orientierte Programming terlebih dahulu
     * terus coba dibuat!
     * https://www.youtube.com/watch?v=iE8tZ0hn2Ws
     * 
     * 20.01.2022
     * OptionState masih bermasalah! coba teliti lagi !!!! DONE 20.01.2022
     * 
     * Apa gak bisa semua kelasnya aku taruh sini, tanpa harus membuat
     * kelas yang baru???? untuk Ketlistener dan juga button????
     * 
     * Aku sempat mencoba untuk frame.AddKeylistener, tapi gak berhasil
     * penyebabnya belum jelas!
     * @param args
     * @throws Exception
     */

    private static String quest,fileName, optionsState = "+";
    private static boolean cekQuest;
    private static boolean state = false;
    private static PrintWriter pw;
    private static JLabel questLabel, answLabel, successLabel, headerLabel, conLabel;
    private static JTextField ansField;
    private static JButton cekAnswButton,optionButton,startButton;

    public static void main(String[] args) throws Exception {

        fileName = "appTraining " + currentTime() + ".txt";
        pw = new PrintWriter(fileName);
        pw.println(currentTime());
        
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(400, 200); // panjang dan lebar aplikasi
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(panel);
        panel.setLayout(null);        

        questLabel = new JLabel("Start? Press Enter");
        questLabel.setBounds(110, 25, 500, 25);
        panel.add(questLabel);

        headerLabel = new JLabel("calculate this problem on your abacus:");
        headerLabel.setBounds(20, 5, 1800, 25);
        panel.add(headerLabel);
        
        
        ansField = new JTextField(); // tempat mengisi jawaban
        ansField.setBounds(100, 50, 165, 25);
        panel.add(ansField);
        ansField.addKeyListener(new AppRev());
        
        optionButton = new JButton(optionsState);
        optionButton.setBounds(10, 50, 80, 25);
        optionButton.addActionListener(new AppRev());
        panel.add(optionButton);

        startButton = new JButton("start");
        startButton.setBounds(100, 80, 80, 25);
        startButton.addActionListener(new AppRev());
        panel.add(startButton);
        
        successLabel = new JLabel("");
        successLabel.setBounds(100, 80, 300, 25);
        panel.add(successLabel);

        conLabel = new JLabel("continue? press enter, 'no' to exit");
        conLabel.setBounds(100, 100, 300, 25);
        panel.add(conLabel);

        //TODO: coba buat size font yang sesuai dengan aplikasi aku!
        //questLabel.setFont(new Font("Courier", Font.BOLD,75));
        frame.setVisible(true);
        headerLabel.setVisible(false);
        conLabel.setVisible(false);


    
    }

    private static String addQuestion(int minNum, int maxNum) {
        Random rand = new Random();
        int a = rand.nextInt(minNum, maxNum), b = rand.nextInt(minNum, maxNum);
        int aFinal = a < 0 ? -1 * a : a, bFinal = b < 0 ? -1 * b : b;
        quest = aFinal < bFinal ? bFinal + " + " + aFinal : aFinal + " + " + bFinal + "";
        System.out.println(quest);
        questLabel.setText(quest);
        int answerAdd = aFinal + bFinal;
        return answerAdd + "";

    }
    
    private static String subsQuestion(int minNum, int maxNum) {
        Random rand = new Random();
        int a = rand.nextInt(minNum, maxNum), b = rand.nextInt(minNum, maxNum);
        int aFinal = a < 0 ? -1 * a : a, bFinal = b < 0 ? -1 * b : b;
        quest = aFinal < bFinal ? bFinal + " - " + aFinal : aFinal + " - " + bFinal + "";
        System.out.println(quest);
        questLabel.setText(quest);
        int answerAdd = aFinal < bFinal ? bFinal - aFinal : aFinal - bFinal;
        return answerAdd + "";
    }
    
    static String optionState_result = "+";
    private static String questionState(){
        /** hanya bisa diakses dengan button, begitulah aku mendesainnya */
        int minNum = 23, maxNum = 9000;
        return optionState_result.equals("+") ? addQuestion(minNum, maxNum) : subsQuestion(minNum, maxNum);
    }

    public static String currentTime() {
        Date date = new Date();
        String currDate = new SimpleDateFormat("(dd|MM|yyyy HH:mm:ss)").format(date);
        return currDate;
    }

    boolean isOptionStateChanged = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(optionButton)) {
            // successLabel.setText("changes state");
            System.out.println("option button is clicked");
            if (state | questLabel.getText().equals("Start? Press Enter")) {
                String optStateNow = optionButton.getText();
                String optState_soll = optStateNow.equals("-")?"+":"-";
                optionButton.setText(optState_soll);
                optionState_result = optState_soll; // untuk membuat latihan soal sesuai dengan pilihan
                System.out.println("optionState has been changed");
                isOptionStateChanged = true;
            } else {
                System.out.println("still on going, optionState will not be changed");
                successLabel.setText("answer it first, then change the mode!");
            }
        } else if (e.getSource().equals(startButton)) {
            System.out.println("1. condition: start or restart");
            answer = addQuestion(25, 9000);
            successLabel.setText("");
            startButton.setVisible(false);
            headerLabel.setVisible(true);
            state = false; // agar ketika ansField kosong, tidak auto reload soal!
        } 
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
    }

    String answer = "";
    String userAnswer = "nothing", questStr = "nothing";
    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("keycode: " + e.getKeyCode());
        // checking if the inputs are only numbers!
        char[] textInputs = ansField.getText().toCharArray();
        boolean isNumbers = true;
        for (int i = 0; i < textInputs.length; i++) {
            if(textInputs[i]<48 || textInputs[i]>57){
                isNumbers = false;
                break;
            }
        }

        if (e.getKeyCode() == 10 & ansField.getText().equals("no") & state) {
            System.out.println("3. condition: exit");
            successLabel.setText("press exit button on the top left!");
            pw.println(currentTime());
            pw.close();
            System.exit(1);
        } else if (
        e.getKeyCode() == 10 & !ansField.getText().equals(userAnswer) &
        ( questLabel.getText().equals("Start? Press Enter")
        | state // kalau misalkan salah jawab dan ingin lanjut maka bisa di beri soal baru!
        | questLabel.getText().equals("continue? press enter")
        | isOptionStateChanged 
        )) {
            System.out.println("1. condition: start or restart");
            // answer = addQuestion(25, 9000);
            // answer = subsQuestion(25, 9000);
            answer = questionState();
            successLabel.setText("");
            ansField.setText("");
            headerLabel.setVisible(true);
            conLabel.setVisible(false);
            startButton.setVisible(false);
            isOptionStateChanged = false;
            state = false; // agar ketika ansField kosong, tidak auto reload soal!
        } else if (ansField.getText().equals("") || !isNumbers){
            System.out.println("2. condition: do nothing");
            return;
        } else if (e.getKeyCode() == 10 & answer.equals(ansField.getText()) & !state) {
            System.out.println("4. condition: correct answer");
            answerCondition();
        } else if (e.getKeyCode() == 10 & !answer.equals(ansField.getText()) & !state){
            System.out.println("5. condition: wrong answer");
            answerCondition();
        }
    }

    void answerCondition() {
            userAnswer = ansField.getText();
            questStr = questLabel.getText() + " = " + answer;
            questLabel.setText(questStr);
            conLabel.setVisible(true);
            boolean isCorrect = answer.equals(ansField.getText());
            String successLabelCondition = isCorrect ? "correct!" : "wrong, your answer: " + userAnswer;
            successLabel.setText(successLabelCondition);
            ansField.setText("");
            state = true;
            pw.println(questLabel.getText() + " | " + successLabelCondition);
    }

}
