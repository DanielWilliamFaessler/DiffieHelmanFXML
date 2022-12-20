package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class DiffieController {
    private int min = 100;
    private int max = 999;
    Random rand = new Random();
    private int p; //modulo variable
    private int g; //basis für potenzrechnung
    private int ka; //public key Alice
    private int kb; //public key Bob



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

    static int prime(Random rand, int min, int max) {
        int prime = rand.nextInt(max - min) + min;
        while (!isPrime(prime)) {
            prime = rand.nextInt(max - min) + min;
        }
        return prime;
    } //erstellt Primzahl

    //setzt werte in variablen
    private void genPrimes(){
        p=prime(rand,min,max);
        g=prime(rand,min,p);
    }
    //to Generate public Key Value
    private static Integer calculatePower(int g,int p, int x){
        String result;
        BigInteger big = BigInteger.valueOf(g);
        BigInteger powered = big.pow(x);
        result= "" + powered.mod(BigInteger.valueOf(p));
        return Integer.parseInt(result);
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
        System.out.println(p);
        System.out.println(g);
        System.out.println(ka);
        System.out.println(kb);
    }

    @FXML
    private Label PrimeAText;
    @FXML
    private Label PrimeBText;

    ArrayList<Integer> generateKeys() {
        ArrayList<Integer> listkeys = new ArrayList<Integer>();
        genPrimes();
        genKeys();
        listkeys.add(ka);
        listkeys.add(kb);
        return listkeys;
    }

    @FXML
    protected void onGenAButtonClick() {
        PrimeAText.setText("Here comes KeyA: " + generateKeys().get(0));
    }

    @FXML
    protected void onGenBButtonClick() {
        PrimeBText.setText("Here comes KeyB: " + generateKeys().get(1));
    }
}