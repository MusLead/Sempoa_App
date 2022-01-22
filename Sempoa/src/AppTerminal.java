import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

public class AppTerminal {
    /**
     * Lakukan sebuah Update:
     * 
     * buat UI dengan GUI!
     * Tutorial: Pelajari Objek orientierte Programming terlebih dahulu
     * terus coba dibuat!
     * https://www.youtube.com/watch?v=iE8tZ0hn2Ws
     * 
     *  DONE 20.01.2022
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {

    
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly
            System.out.print("addition or substraction? (+/-)");
            String mainOpt = sc.nextLine();

            if (mainOpt.equals("-")) {
                System.out.println("Do you want to training with random numbers? (y/n)");
                String option = sc.nextLine();
                if (option.equals("y")) {
                    substractionRandom(35, 3000);
                } else {
                    substractionTraining();
                }
            } else if (mainOpt.equals("+")) {
                additionRandom(35, 3000);
            } else {
                System.out.println("not valid");
            }
            System.out.println("\nTraining Done! Good Job");
        }
    }

    private static void substractionRandom(int minNum, int maxNum) {
        System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly
        Random rand = new Random();
        try (Scanner sc = new Scanner(System.in)) {
            PrintWriter pw;
            try {
                // System.out.println("name the file: ");
                // pw = extracted(sc);
                pw = new PrintWriter("subs. " + currentTime() + ".txt");
                pw.println(currentTime());
                System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly

                System.out.println("calculate this problem on your abacus:");

                int a = rand.nextInt(minNum, maxNum), b = rand.nextInt(minNum, maxNum);
                int aFinal = a < 0 ? -1 * a : a, bFinal = b < 0 ? -1 * b : b;
                System.out.println(aFinal < bFinal ? bFinal + " - " + aFinal : aFinal + " - " + bFinal + "");

                int hasil = sc.nextInt();
                String cekHasil = hasil == (aFinal < bFinal ? bFinal - aFinal : aFinal - bFinal) ? "correct"
                        : "try again: " + (aFinal < bFinal ? (bFinal - aFinal) : (aFinal - bFinal));
                System.out.println(cekHasil + "\n");
                int counter = 1;
                pw.println(counter + ". " + aFinal + " - " + bFinal + " = " + hasil + " | " + cekHasil + "\n");

                System.out.print("continue? (y/n)");
                String optionGame = sc.next();

                while (optionGame.equals("y")) {

                    if (aFinal == 0 || bFinal == 0 || bFinal - aFinal == 0 || aFinal - bFinal == 0) {
                        // jika ada suatu soal yang jelas untuk dihitung, tidak perlu di tunjukan!
                        a = rand.nextInt(minNum, maxNum);
                        b = rand.nextInt(minNum, maxNum);
                        aFinal = a < 0 ? -1 * a : a;
                        bFinal = b < 0 ? -1 * b : b;

                    } else { // menunjukan soal yang penting!

                        // Generating value and Input
                        a = rand.nextInt(minNum, maxNum);
                        b = rand.nextInt(minNum, maxNum);
                        aFinal = a < 0 ? -1 * a : a;
                        bFinal = b < 0 ? -1 * b : b;
                        System.out
                                .println(aFinal < bFinal ? bFinal + " - " + aFinal + "" : aFinal + " - " + bFinal + "");
                        hasil = sc.nextInt();

                        // Calculating the Result
                        String cekHasilLoop = (hasil == (aFinal < bFinal ? bFinal - aFinal : aFinal - bFinal)
                                ? "correct"
                                : "try again: " + (aFinal < bFinal ? (bFinal - aFinal) : (aFinal - bFinal)));
                        System.out.println(cekHasilLoop + "\n");
                        pw.println(++counter + ". " + aFinal + " - " + bFinal + " = " + hasil + " | " + cekHasilLoop
                                + "\n");
                        System.out.print("continue? (y/n)");
                        optionGame = sc.next();
                    }

                }
                pw.println(currentTime());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (NullPointerException n) {
            System.err.println("not a valid input");
        }

    }

    private static void additionRandom(int minNum, int maxNum) {
        System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly
        Random rand = new Random();
        try (Scanner sc = new Scanner(System.in)) {
            PrintWriter pw;
            try {
                // System.out.println("name the file: ");
                // pw = extracted(sc);
                pw = new PrintWriter("add." + currentTime() + ".txt");
                pw.println(currentTime());
                System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly

                System.out.println("calculate this problem on your abacus:");

                int a = rand.nextInt(minNum, maxNum), b = rand.nextInt(minNum, maxNum);
                int aFinal = a < 0 ? -1 * a : a, bFinal = b < 0 ? -1 * b : b;
                System.out.println(aFinal < bFinal ? bFinal + " + " + aFinal : aFinal + " + " + bFinal + "");

                int hasil = sc.nextInt();
                String cekHasil = hasil == (aFinal < bFinal ? bFinal + aFinal : aFinal + bFinal) ? "correct"
                        : "try again: " + (aFinal < bFinal ? (bFinal + aFinal) : (aFinal + bFinal));
                System.out.println(cekHasil + "\n");
                int counter = 1;
                pw.println(counter + ". " + aFinal + " + " + bFinal + " = " + hasil + " | " + cekHasil + "\n");

                System.out.print("continue? (y/n)");
                String optionGame = sc.next();

                while (optionGame.equals("y")) {

                    if (aFinal == 0 || bFinal == 0 || bFinal - aFinal == 0 || aFinal - bFinal == 0) {
                        // jika ada suatu soal yang jelas untuk dihitung, tidak perlu di tunjukan!
                        a = rand.nextInt(minNum, maxNum);
                        b = rand.nextInt(minNum, maxNum);
                        aFinal = a < 0 ? -1 * a : a;
                        bFinal = b < 0 ? -1 * b : b;

                    } else { // menunjukan soal yang penting!

                        // Generating value and Input
                        a = rand.nextInt(minNum, maxNum);
                        b = rand.nextInt(minNum, maxNum);
                        aFinal = a < 0 ? -1 * a : a;
                        bFinal = b < 0 ? -1 * b : b;
                        System.out
                                .println(aFinal < bFinal ? bFinal + " + " + aFinal + "" : aFinal + " + " + bFinal + "");
                        hasil = sc.nextInt();

                        // Calculating the Result
                        String cekHasilLoop = (hasil == (aFinal < bFinal ? bFinal + aFinal : aFinal + bFinal)
                                ? "correct"
                                : "try again: " + (aFinal < bFinal ? (bFinal + aFinal) : (aFinal + bFinal)));
                        System.out.println(cekHasilLoop + "\n");
                        pw.println(++counter + ". " + aFinal + " + " + bFinal + " = " + hasil + " | " + cekHasilLoop
                                + "\n");
                        System.out.print("continue? (y/n)");
                        optionGame = sc.next();
                    }

                }
                pw.println(currentTime());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (NullPointerException n) {
            System.err.println("not a valid input");
        }

    }

    public static String currentTime() {
        Date date = new Date();
        String currDate = new SimpleDateFormat("(dd|MM|yyyy HH:mm:ss)").format(date);
        return currDate;
    }

    private static void substractionTraining() {
        System.out.println("\033[H\033[2J"); // refresh the terminal automaticaly
        int maxNum = 20;
        try (Scanner sc = new Scanner(System.in)) {
            // System.out.print("name of the file: ");
            try (
                    // PrintWriter pw = extracted(sc)
                    PrintWriter pw = new PrintWriter("subs.Trng " + currentTime() + ".txt")) {
                pw.println(currentTime());
                System.out.print("normal? ");

                if (sc.nextLine().equals("normal")) {
                    System.out.println("calculate this problem on your abacus:");
                    for (int i = maxNum; i >= 0; i--) {
                        for (int j = maxNum; j >= 0; j--) {
                            if (i > j) {
                                System.out.println(i + " - " + j + "|");
                                int hasil = sc.nextInt();

                                String cekHasil = hasil == i - j ? "correct" : "try again: " + (i - j);
                                System.out.println(cekHasil + "\n");
                                pw.println(i + " - " + j + " = " + hasil + " | " + cekHasil + "\n");
                            }

                        }
                    }

                } else {
                    System.out.println("calculate this problem on your abacus:");
                    for (int i = maxNum; i > 0; i--) {
                        for (int j = maxNum; j > 0; j--) {
                            System.out.println(i + " - " + j + "|");
                            int hasil = sc.nextInt();

                            String cekHasil = hasil == i - j ? "correct" : "try again: " + (i - j);
                            System.out.println(cekHasil + "\n");
                            pw.println(i + " - " + j + " = " + hasil + " | " + cekHasil + "\n");

                        }
                    }
                }
                pw.println(currentTime());
                pw.close();

            } catch (Exception e) {
                System.err.println(e.getCause());
            }
        } catch (NullPointerException n) {
            System.err.println("not a valid input");
        }
    }

   
}
