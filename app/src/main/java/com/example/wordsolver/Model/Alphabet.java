package com.example.wordsolver.Model;

public class Alphabet {
    private int idChuCai;
    private String chuCai;

    public Alphabet() {
    }

    public Alphabet(int idChuCai, String chuCai) {
        this.idChuCai = idChuCai;
        this.chuCai = chuCai;
    }

    public int getIdChuCai() {
        return idChuCai;
    }

    public void setIdChuCai(int idChuCai) {
        this.idChuCai = idChuCai;
    }

    public String getChuCai() {
        return chuCai;
    }

    public void setChuCai(String chuCai) {
        this.chuCai = chuCai;
    }
}
