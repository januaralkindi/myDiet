package com.example.mydiet.Model;

public class UserModel {
    private Integer id;
    private String nama;
    private Integer tinggi;
    private Integer usia;
    private Integer berat;
    private Integer jenis_kelamin;
    private Integer jenis_aktivitas;

    public UserModel(Integer id, String nama, Integer tinggi, Integer usia, Integer berat, Integer jenis_kelamin, Integer jenis_aktivitas, Double kalori) {
        this.id = id;
        this.nama = nama;
        this.tinggi = tinggi;
        this.usia = usia;
        this.berat = berat;
        this.jenis_kelamin = jenis_kelamin;
        this.jenis_aktivitas = jenis_aktivitas;
        this.kalori = kalori;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getTinggi() {
        return tinggi;
    }

    public void setTinggi(Integer tinggi) {
        this.tinggi = tinggi;
    }

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }

    public Integer getBerat() {
        return berat;
    }

    public void setBerat(Integer berat) {
        this.berat = berat;
    }

    public Integer getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(Integer jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public Integer getJenis_aktivitas() {
        return jenis_aktivitas;
    }

    public void setJenis_aktivitas(Integer jenis_aktivitas) {
        this.jenis_aktivitas = jenis_aktivitas;
    }

    public Double getKalori() {
        return kalori;
    }

    public void setKalori(Double kalori) {
        this.kalori = kalori;
    }

    private Double kalori;


}
