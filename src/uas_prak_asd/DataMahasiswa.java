package uas_prak_asd;

public class DataMahasiswa {

    private String nim;
    private String nama;
    private String alamat;
    private String rt;
    private String rw;
    private String kota;
    private String agama;
    private String gender;

    public DataMahasiswa(String nim, String nama, String alamat, String rt, String rw, String kota, String agama, String gender) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.rt = rt;
        this.rw = rw;
        this.kota = kota;
        this.agama = agama;
        this.gender = gender;
    }
//

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
    

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
