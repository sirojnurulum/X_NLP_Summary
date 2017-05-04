/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABC
 */
public class Main {

    ArrayList<String> docs;
    ArrayList<String> docsStopWord;
    ArrayList<String> docsStem;
    HashMap<String, HashMap<String, Double>> tfDatas;
    HashMap<String, Double> idfData;
    HashMap<String, Double> tfIdfData;
    String text;
    Control control;

    public Main() {
        docs = new ArrayList<>();
        docsStopWord = new ArrayList<>();
        docsStem = new ArrayList<>();
        tfDatas = new HashMap<>();
        idfData = new HashMap<>();
        tfIdfData = new HashMap<>();
        control = new Control();
    }

    public void mulai() {
        readDataFromFile();
        removeStopWordAndStem();
        getTf();
        getIdf();
//        System.out.println(tfIdfData.size());
//        System.out.println("####################################################");
//        for (int i = 0; i < docs.size(); i++) {
//            System.out.println(docs.get(i));
//        }
//        System.out.println("####################################################");
//        System.out.println(control.createSummary(docs, tfIdfData));
    }

    public void getTf() {
        HashMap<String, Double> tfData;
        for (int i = 0; i < docsStopWord.size(); i++) {
            tfData = new HashMap<>();
            ArrayList<String> tmp = control.doTokenisasi(docsStopWord.get(i));
//            System.out.println("------------------------------------------");
//            System.out.println("i index = " + i);
//            System.out.println(docs.get(i));
            for (int j = 0; j < tmp.size(); j++) {
                if (tfData.get(tmp.get(j)) == null) {
                    tfData.put(tmp.get(j), control.calculateTf(tmp, tmp.get(j)));
//                    System.out.println(tmp.get(j) + " -> " + tfData.get(tmp.get(j)));
                }
            }
//            System.out.println("------------------------------------------");
            tfDatas.put(docs.get(i), tfData);
        }
    }

    public void getIdf() {
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            tmp.add(control.doTokenisasi(docs.get(i)));
        }
        for (Map.Entry<String, HashMap<String, Double>> entry : tfDatas.entrySet()) {
            String key = entry.getKey();
            HashMap<String, Double> tfData = entry.getValue();
            for (Map.Entry<String, Double> entry1 : tfData.entrySet()) {
                if (!idfData.containsKey(entry1.getKey())) {
                    idfData.put(entry1.getKey(), control.calculateIdf(tmp, entry1.getKey()));
                    System.out.println("" + entry1.getKey() + " : " + idfData.get(entry1.getKey()));
                    //calculate tfidf
                    tfIdfData.put(entry1.getKey(), (entry1.getValue() * idfData.get(entry1.getKey())));
                    System.out.println("tfidf : " + entry1.getKey() + " = " + tfIdfData.get(entry1.getKey()));
                }
            }
        }
    }

    public void removeStopWordAndStem() {
        for (int i = 0; i < docs.size(); i++) {
//            System.out.println("-->>docs");
//            System.out.println(docs.get(i));
            docsStopWord.add(control.doStopWord(docs.get(i)));
//            System.out.println("docsStopWord : ");
//            System.out.println(docsStopWord.get(i));
            docsStem.add(control.doStem(docsStopWord.get(i)));
//            System.out.println("docsStem : ");
//            System.out.println(docsStem.get(i));
//            System.out.println("\n");
        }
    }

    public void readDataFromFile() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/data/komputer"));
            Object[] o = bf.lines().toArray();
            for (Object object : o) {
                docs.add(String.valueOf(object));
                text = text + String.valueOf(object) + "\n";
//                System.out.println(String.valueOf(object));
//                System.out.println("\n\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Main().mulai();
    }
}
