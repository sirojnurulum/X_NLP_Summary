/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import IndonesianNLP.IndonesianPOSTagger;
import IndonesianNLP.IndonesianSentenceDetector;
import IndonesianNLP.IndonesianSentenceTokenizer;
import IndonesianNLP.IndonesianStemmer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABC
 */
public class Main {

    ArrayList<String> teks;

    public Main() {
        teks = new ArrayList<>();
    }

    public void mulai() {
        readDataFromFile();
//        sentenceDetector();
//        tokenisasi();
//        postag();
        stem();
    }

    public void stem() {
        IndonesianStemmer is = new IndonesianStemmer();
        ArrayList<String> tmp = new IndonesianSentenceTokenizer().tokenizeSentence(teks.get(0));
        for (String string : tmp) {
            System.out.println("---------");
            System.out.println("kata : " + string);
            System.out.println("Basic word : " + is.stem(string));
            for (int i = 0; i < is.derivationalprefix.size(); i++) {
                System.out.println("Derivational Prefix : " + is.derivationalprefix.get(i));
            }
            System.out.println("Particle Suffix: " + is.particlesuffix);
            System.out.println("Possessive Pronoun Suffix: " + is.possessivepronounsuffix);
            System.out.println("Derivational Suffix: " + is.derivationalsuffix);

        }
    }

    public void postag() {
        for (String[] string : IndonesianPOSTagger.doPOSTag(teks.get(0))) {
            System.out.println("====postag");
            for (String string1 : string) {
                System.out.println(string1);
            }
        }
    }

    public void tokenisasi() {
        IndonesianSentenceTokenizer ist = new IndonesianSentenceTokenizer();
        System.out.println(teks.get(0));
        System.out.println("--------------------------------------------");
        ist.tokenizeSentence(teks.get(0)).stream().map((tek) -> {
            System.out.println(tek);
            return tek;
        }).forEachOrdered((_item) -> {
            System.out.println("++");
        });
        System.out.println("=============================================");
        ist.tokenizeSentenceWithCompositeWords(teks.get(0)).stream().map((object) -> {
            System.out.println(object);
            return object;
        }).forEachOrdered((_item) -> {
            System.out.println("++");
        });
    }

    public void sentenceDetector() {
        IndonesianSentenceDetector isd = new IndonesianSentenceDetector();
        System.out.println(teks.get(0));
        System.out.println("---------------------------------------------");
        isd.splitSentence(teks.get(0)).stream().map((String string) -> {
            System.out.println(string);
            return string;
        }).forEachOrdered((_item) -> {
            System.out.println("++");
        });
    }

    public void readDataFromFile() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/data/komputer"));
            Object[] o = bf.lines().toArray();
            for (Object object : o) {
                teks.add(String.valueOf(object));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Main().mulai();
    }
}
