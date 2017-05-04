/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import IndonesianNLP.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public String createSummary(ArrayList<String> docs, HashMap<String, Double> tfidf) {
        String hasil = "";
        ArrayList<String> tmp = new ArrayList<>();
        for (Map.Entry<String, Double> entry : tfidf.entrySet()) {
            tmp.add(entry.getKey());
        }
        System.out.println(Arrays.toString(tmp.toArray()));
        for (int i = 0; i < tmp.size() - 1; i++) {
            for (int j = i + 1; j < tmp.size(); j++) {
                if (tfidf.get(tmp.get(i)) < tfidf.get(tmp.get(j))) {
                    String x = tmp.get(i);
                    tmp.set(i, tmp.get(j));
                    tmp.set(j, x);
                }
            }
        }
        System.out.println(Arrays.toString(tmp.toArray()));
        ArrayList<String> newDocs = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            if (docs.get(i).contains(".")) {
                String[] q = docs.get(i).split(".");
                for (int j = 0; j < q.length; j++) {
                    tmp.add(q[j]);
                }
            } else {
                tmp.add(docs.get(i));
            }
        }
        for (int i = 0; i < newDocs.size(); i++) {
            if (newDocs.get(i).contains(tmp.get(0)) | newDocs.get(i).contains(tmp.get(1)) | newDocs.get(i).contains(tmp.get(2)) | newDocs.get(i).contains(tmp.get(3)) | newDocs.get(i).contains(tmp.get(4))) {
                hasil = hasil + newDocs.get(i);
                System.out.println(hasil);
            }
        }
        return hasil;
    }

    public double calculateTf(ArrayList<String> doc, String x) {
        float hasil = 0;
        for (int i = 0; i < doc.size(); i++) {
            if (doc.get(i).equalsIgnoreCase(x)) {
                hasil++;
            }
        }
//        System.out.println("+++++++++++++++++++++++");
//        System.out.println(x);
//        System.out.println("count = " + hasil);
//        System.out.println("div = " + hasil / doc.size());
        return hasil / (float) doc.size();
    }

    public double calculateIdf(ArrayList<ArrayList<String>> docs, String x) {
        float hasil = 0;
        for (int i = 0; i < docs.size(); i++) {
            for (int j = 0; j < docs.get(i).size(); j++) {
                if (x.equalsIgnoreCase(docs.get(i).get(j))) {
                    hasil++;
                    break;
                }

            }
        }
//        System.out.println("+++++++++++++++++++++++++++++++++");
//        System.out.println(x);
//        System.out.println("docs size = " + docs.size());
//        System.out.println("count = " + hasil);
//        System.out.println("IDF = " + Math.log(docs.size() / hasil));
        return Math.log(docs.size() / hasil);
    }

    public double dalculateTfIdf(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String x) {
        return (calculateTf(doc, x) * calculateIdf(docs, x));
    }

    public String doStopWord(String kalimat) {
//        System.out.println("++");
//        System.out.println(kalimat);
//        System.out.println("++ formalized sentence");
        kalimat = isf.formalizeSentence(kalimat);
//        System.out.println(kalimat);
//        System.out.println("++ formalized word");
//        System.out.println(isf.formalizeWord(kalimat));
//        System.out.println("++ stopword removed");
        isf.initStopword();
        kalimat = isf.deleteStopword(kalimat);
//        System.out.println(kalimat);
        return kalimat;
    }

    public String doStem(String kalimat) {
        ArrayList<String> tmp = ist.tokenizeSentence(kalimat);
        kalimat = "";
        for (int i = 0; i < tmp.size(); i++) {
            kalimat = kalimat + " " + is.stem(tmp.get(i));
        }
        return kalimat;
    }

    public ArrayList<String> doTokenisasi(String kalimat) {
        return ist.tokenizeSentence(kalimat);
    }

    public void postag(ArrayList<String> docs) {
        for (String[] string : IndonesianPOSTagger.doPOSTag(docs.get(0))) {
            System.out.println("====postag");
            for (String string1 : string) {
                System.out.println(string1);
            }
        }
    }

    public void sentenceDetector(ArrayList<String> docs) {
        IndonesianSentenceDetector isd = new IndonesianSentenceDetector();
        System.out.println(docs.get(0));
        System.out.println("---------------------------------------------");
        isd.splitSentence(docs.get(0)).stream().map((String string) -> {
            System.out.println(string);
            return string;
        }).forEachOrdered((_item) -> {
            System.out.println("++");
        });
    }

}
