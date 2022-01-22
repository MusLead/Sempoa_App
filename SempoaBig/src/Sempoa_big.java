import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sempoa_big implements java.awt.event.ActionListener, KeyListener {
    /**
     * Abbacus Questions Generator
     * Aplikasi Soal Sempoa Terpadu
     * 
     * created by: Agha Muhammad Aslam
     * Date: 20.01.2022
     * 
     * 1. Update: 20.01.2022:
     * Interface sederhana dengan Enter untuk submit hasil. 
     * Jawaban juga dapat di review dengan baik oleh user setelah 
     * mereka mengerjakannya
     * 
     * @param args
     * @throws Exception
    */

    private static int minNum = 23, maxNum = 9000;
    private static String quest,fileName, optionsState = "+";
    private static boolean state = false;
    private static PrintWriter pw;
    private static JLabel questLabel, successLabel, headerLabel, conLabel;
    private static JTextField ansField;
    private static JButton optionButton,startButton;
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();

    public static void main(String[] args) throws Exception {

        fileName = "appTraining " + currentTime() + ".txt";
        pw = new PrintWriter(fileName);
        pw.println(currentTime());
        
        defaultTemplateAgha(10);

        frame.setVisible(true);
        headerLabel.setVisible(false);
        conLabel.setVisible(false);
    }
    
    /**
     * Default Template with JFx component!
     * 20.01.2022
     * well it has been remodivied, so that it looks nice
     * hehehehe sorry...
     */
    private static void defaultTemplateAgha(int r){
        frame.setSize(400+200, 200+100); // panjang dan lebar aplikasi
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(panel);
        panel.setLayout(null);        

        questLabel = new JLabel("Start? Press Enter");
        questLabel.setBounds(110, 15+30, 500+r, 25+r);
        questLabel.setFont(new Font("Calibri", Font.PLAIN,11+r));
        panel.add(questLabel);

        headerLabel = new JLabel("calculate this problem on your abacus:");
        headerLabel.setBounds(20, 5, 1800+r, 25+r);
        headerLabel.setFont(new Font("Calibri", Font.PLAIN,11+r));
        panel.add(headerLabel);
         
        ansField = new JTextField(); // tempat mengisi jawaban
        ansField.setBounds(100, 50+30, 165+r, 25+r);
        panel.add(ansField);
        ansField.setFont(new Font("Calibri", Font.PLAIN,11+r));
        ansField.addKeyListener(new Sempoa_big());
        
        optionButton = new JButton(optionsState);
        optionButton.setBounds(5, 50+30, 80+r, 25+r);
        optionButton.addActionListener(new Sempoa_big());
        optionButton.setFont(new Font("Calibri", Font.PLAIN,11+r));
        panel.add(optionButton);

        startButton = new JButton("start");
        startButton.setBounds(100, 80+40, 80+r, 25+r);
        startButton.setFont(new Font("Calibri", Font.PLAIN,11+r));
        startButton.addActionListener(new Sempoa_big());
        panel.add(startButton);
        
        successLabel = new JLabel("");
        successLabel.setBounds(100, 80+30, 300+r+100, 25+r);
        successLabel.setFont(new Font("Calibri", Font.PLAIN,11+r));
        panel.add(successLabel);

        conLabel = new JLabel("continue? press enter, 'no' to exit");
        conLabel.setBounds(100, 100+40, 300+r+100, 25+r);
        conLabel.setFont(new Font("Calibri", Font.PLAIN,11+r));
        panel.add(conLabel);

    }



    static String optionState_result = "+"; // dia akses di questions, untuk menyesuikan apa yang diinginkan
    
    /**
     * this method return the result from the question that has been
     * generated
     * @return results of the random equations
     */
    private static String questions(){
        return questions(minNum, maxNum);
    }

    private static String questions(int minNum, int maxNum){
        Random rand = new Random();
        int a = rand.nextInt(minNum, maxNum), b = rand.nextInt(minNum, maxNum);
        int aFinal = a < 0 ? -1 * a : a, bFinal = b < 0 ? -1 * b : b;
        quest = optionState_result.equals("+") ? (aFinal + " + " + bFinal) : (aFinal < bFinal ? bFinal + " - " + aFinal : aFinal + " - " + bFinal);    
        System.out.println(quest);
        questLabel.setText(quest);
        int result = optionState_result.equals("+") ? (aFinal + bFinal) : (aFinal < bFinal ? bFinal - aFinal : aFinal - bFinal);
        return result + "";
    }

    public static String currentTime() {
        Date date = new Date();
        String currDate = new SimpleDateFormat("(dd|MM|yyyy HH:mm:ss)").format(date);
        return currDate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // BUTTON Action

        if (e.getSource().equals(optionButton)) {
            System.out.println("option button is clicked");
            if (state | questLabel.getText().equals("Start? Press Enter")) {
                String optStateNow = optionButton.getText();
                String optState_soll = optStateNow.equals("-")?"+":"-";
                optionButton.setText(optState_soll);
                optionState_result = optState_soll; // untuk membuat latihan soal sesuai dengan pilihan
                System.out.println("optionState has been changed");
            } else {
                System.out.println("still on going, optionState will not be changed");
                successLabel.setText("answer it first, then change the mode!");
            }
        } else if (e.getSource().equals(startButton)) {
            System.out.println("1. condition: start or restart");
            answer = questions();
            successLabel.setText("");
            startButton.setVisible(false);
            headerLabel.setVisible(true);
            state = false; // agar ketika ansField kosong, tidak auto reload soal!
        } 
        
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    String answer = "";
    String userAnswer = "nothing", questStr = "nothing";
    @Override
    public void keyReleased(KeyEvent e) {
        // KEYBOARD Action

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
            System.exit(0);
        } else if(e.getKeyCode() == 10 & !ansField.getText().equals(userAnswer) &
        (questLabel.getText().equals("Start? Press Enter")
        | state // kalau misalkan salah jawab dan ingin lanjut maka bisa di beri soal baru!
        | questLabel.getText().equals("continue? press enter"))) {
            System.out.println("1. condition: start or restart");
            answer = questions();
            successLabel.setText("");
            ansField.setText("");
            headerLabel.setVisible(true);
            conLabel.setVisible(false);
            startButton.setVisible(false);
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

    /**
     * showing the condition after the answer has been submitted by the user
     */
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
