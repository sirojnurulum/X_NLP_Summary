/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author ABC
 */
public class Paper {

    public Paper() {
    }

    public Paper(String judul, String abstrak, String[] kataKunci, String pendahuluan, String landasanTeori, String metode, String hasil, String kesimpulan) {
        this.judul = judul;
        this.abstrak = abstrak;
        this.kataKunci = kataKunci;
        this.pendahuluan = pendahuluan;
        this.landasanTeori = landasanTeori;
        this.metode = metode;
        this.hasil = hasil;
        this.kesimpulan = kesimpulan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAbstrak() {
        return abstrak;
    }

    public void setAbstrak(String abstrak) {
        this.abstrak = abstrak;
    }

    public String[] getKataKunci() {
        return kataKunci;
    }

    public void setKataKunci(String[] kataKunci) {
        this.kataKunci = kataKunci;
    }

    public String getPendahuluan() {
        return pendahuluan;
    }

    public void setPendahuluan(String pendahuluan) {
        this.pendahuluan = pendahuluan;
    }

    public String getLandasanTeori() {
        return landasanTeori;
    }

    public void setLandasanTeori(String landasanTeori) {
        this.landasanTeori = landasanTeori;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    private String judul;
    private String abstrak;
    private String[] kataKunci;
    private String pendahuluan;
    private String landasanTeori;
    private String metode;
    private String hasil;
    private String kesimpulan;
}
