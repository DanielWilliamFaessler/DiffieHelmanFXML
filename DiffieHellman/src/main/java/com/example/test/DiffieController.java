package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    private ArrayList<Integer> publicKeys = new ArrayList<>();



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

    //generates first primitive root of p (called modder here)
    private int genG(int modder){
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
            for (int j = 1; j<modder; j++){
                BigInteger powered = base.pow(j);
                BigInteger moduloOfBase = powered.mod(BigInteger.valueOf(modder));
                val = Integer.parseInt(moduloOfBase+"");
                if (n.contains(val)){
                    comparator.add(val);
                }
            }
            Collections.sort(comparator);
            if (n.equals(comparator)){
                g=i;
            }

        }
        return g;
    }

    static int prime(Random rand, int min, int max) {
        int prime = rand.nextInt(max - min) + min;
        while (!isPrime(prime)) {
            prime = rand.nextInt(max - min) + min;
        }
        return prime;
    } //erstellt Primzahl

    //setzt werte in variablen
    private void genPrime(){
        p=prime(rand,min,max);
    }

    private void genKeys(){
        int a = 5;
        int b = 7;
        BigInteger big = BigInteger.valueOf(g);
        BigInteger powereda = big.pow(a);
        BigInteger moddeda = powereda.mod(BigInteger.valueOf(p));
        BigInteger poweredb = big.pow(b);
        BigInteger moddedb = poweredb.mod(BigInteger.valueOf(p));
        ka = Integer.parseInt(moddeda+"");
        kb = Integer.parseInt(moddedb+"");
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

    private void generateKeys() {
        genPrime();
        g=genG(p);
        genKeys();
        publicKeys.add(ka);
        publicKeys.add(kb);
    }

    @FXML
    protected void onGenAButtonClick() {
        generateKeys();
        PrimeAText.setText("Here comes KeyA: " + publicKeys.get(0));
        PrimeBText.setText("Here comes KeyB: " + publicKeys.get(1));
    }

}