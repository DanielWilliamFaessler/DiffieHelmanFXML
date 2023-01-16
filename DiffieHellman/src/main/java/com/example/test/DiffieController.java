package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class DiffieController {
    private int min = 100;
    private int max = 999;
    Random rand = new Random();
    private int p; //modulo variable
    private int g; //basis für potenzrechnung
    private int ka; //public key Alice
    private int kb; //public key Bob
    private int a;
    private int b;
    private ArrayList<Integer> publicKeys = new ArrayList<>();


    //checks if Primenumber is under max value and if it even is a Primenumber
    static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        //tested ob die übergebene Zahl Primzahl ist
        for (int i = 2; i <= num / 2; i++) {
            if (Math.floorMod(num, i) == 0)
                return false;
        }
        return true;
    }

    //generates Primenumber
    static int prime(Random rand, int min, int max) {
        int prime = rand.nextInt(max - min) + min;
        while (!isPrime(prime)) {
            prime = rand.nextInt(max - min) + min;
        }
        return prime;
    }

    //generates Value for P
    private void genPrime() {
        p = prime(rand, min, max);
    }

    /*generates first primitive root of p (called modder here)
     */
    private int genG(int modder) {
        int g = 0;
        int val;
        ArrayList<Integer> n = new ArrayList<>();
        for (int k = 1; k < modder; k++) {
            n.add(k);
        }
        ArrayList<Integer> comparator = new ArrayList<>();
        //iterates
        for (int i = 1; i < modder; i++) {
            BigInteger base = BigInteger.valueOf(i);
            comparator.clear();
            for (int j = 1; j < modder; j++) {
                BigInteger powered = base.pow(j);
                BigInteger moduloOfBase = powered.mod(BigInteger.valueOf(modder));
                val = Integer.parseInt(moduloOfBase + "");
                if (n.contains(val)) {
                    comparator.add(val);
                }
            }
            Collections.sort(comparator);
            if (n.equals(comparator)) {
                g = i;
                break;
            }

        }
        return g;
    }

    //generates public Keys (placeholder)
    private void genKeys() {
        BigInteger big = BigInteger.valueOf(g);
        BigInteger powereda = big.pow(a);
        BigInteger moddeda = powereda.mod(BigInteger.valueOf(p));
        BigInteger poweredb = big.pow(b);
        BigInteger moddedb = poweredb.mod(BigInteger.valueOf(p));
        ka = Integer.parseInt(moddeda + "");
        kb = Integer.parseInt(moddedb + "");
        System.out.println(" ");
        System.out.println(p);
        System.out.println(g);
        System.out.println(ka);
        System.out.println(kb);
    }

    @FXML
    private Label PrimeAText;
    @FXML
    private Label PrimeBText;
    @FXML
    private TextArea Log;

    //generates all needed values
    public void generateKeys() {
        publicKeys.clear();
        genPrime();//generates p
        g = genG(p); // generates g -> Base
        genKeys(); // generate Keys
        publicKeys.add(ka);
        publicKeys.add(kb);
    }

    @FXML
    private TextField privateA;

    @FXML
    private TextField privateB;

    @FXML
    protected void sendPA() {
        Log.setText("");
        a = Integer.parseInt(privateA.getText());
        Log.setText(Log.getText() + "Alice chooses " + a + " as her private Key" + "\n (This is not being transmitted, it is calculated locally)" + "\n");
    }

    @FXML
    protected void sendPB() {
        b = Integer.parseInt(privateB.getText());
        Log.setText(Log.getText() + "Bob chooses " + b + " as his private Key" + "\n (This is not being transmitted, it is calculated locally)" + "\n \n");
    }

    @FXML
    protected void onGenAButtonClick() throws InterruptedException {
        generateKeys();
        PrimeAText.setText("Here comes Public KeyA: " + publicKeys.get(0));
        PrimeBText.setText("Here comes Public KeyB: " + publicKeys.get(1));
        Log.setText(
                Log.getText()
                        + "System generates Primenumber for modulo calculation -> 'p' : "+p+"\n"
                        + "System generates Primitive Root from p for modulo calculation -> 'g' : "+g+"\n \n"
                        + "Calculating Alice's public Key... \n"
                        + "Alice's public Key is : " + g + "^" + a + " modulo " + p + " \n"
                        + "= " + publicKeys.get(0) + "\n (This is not being transmitted, it is calculated locally)" + "\n \n"
                        + "Calculating Bob's public Key... \n"
                        + "Bob's public Key is : " + g + "^" + b + " modulo " + p + " \n"
                        + "= " + publicKeys.get(1) + "\n (This is not being transmitted, it is calculated locally) \n \n"
                        +"Public Communication Log starts here: "
        );
        Log.setText(
                Log.getText() + "\n \n"
                        + "Alice sends her public Key A: " + publicKeys.get(0)+"\n"
                        + "Bob sends his public Key B: " + publicKeys.get(1)
        );
    }

}