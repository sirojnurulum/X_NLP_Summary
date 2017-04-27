/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import IndonesianNLP.*;
import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class Control {

    IndonesianNETagger inet;
    IndonesianPOSTagger ipost;
    IndonesianPhraseChunker ipc;
    IndonesianReferenceResolution irr;
    IndonesianSentenceDetector isd;
    IndonesianSentenceFormalization isf;
    IndonesianSentenceTokenizer ist;
    IndonesianStemmer is;

    public Control() {
        this.inet = new IndonesianNETagger();
        this.ipc = new IndonesianPhraseChunker();
        this.irr = new IndonesianReferenceResolution();
        this.isd = new IndonesianSentenceDetector();
        this.isf = new IndonesianSentenceFormalization();
        this.ist = new IndonesianSentenceTokenizer();
        this.is = new IndonesianStemmer();
    }

    public double calculateTf(ArrayList<String> doc, String x) {
        int hasil = 0;
        hasil = doc.stream().filter((string) -> (string.equalsIgnoreCase(x))).map((_item) -> 1).reduce(hasil, Integer::sum);
        return hasil / doc.size();
    }

    public double calculateIdf(ArrayList<ArrayList<String>> docs, String x) {
        int hasil = 0;
        for (ArrayList<String> doc : docs) {
            hasil = doc.stream().filter((string) -> (string.equalsIgnoreCase(x))).map((_item) -> 1).reduce(hasil, Integer::sum);
        }
        return Math.log(docs.size() / hasil);
    }

    public double dalculateTfIdf(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String x) {
        return (calculateTf(doc, x) * calculateIdf(docs, x));
    }

    public String doStopWord(String kalimat) {
        System.out.println("++");
        System.out.println(kalimat);
        System.out.println("++ formalized sentence");
        kalimat = isf.formalizeSentence(kalimat);
        System.out.println(kalimat);
        System.out.println("++ formalized word");
        System.out.println(isf.formalizeWord(kalimat));
        System.out.println("++ stopword removed");
        isf.initStopword();
        kalimat = isf.deleteStopword(kalimat);
        System.out.println(kalimat);
        return kalimat;
    }
    
}
