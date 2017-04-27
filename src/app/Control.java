/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class Control {

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
    
    
}
