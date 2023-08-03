/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author THAIHB.B19CN638
 */
import java.io.Serializable;

public class MonHoc implements Serializable {
    private int id;
    private String ma;
    private String ten;
    private int tinchi;

    public MonHoc() {
    }

    public MonHoc(String ma, String ten, int tinchi) {
        this.ma = ma;
        this.ten = ten;
        this.tinchi = tinchi;
    }

    
    public MonHoc(int id, String ma, String ten, int tinchi) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tinchi = tinchi;
    }

    // Getter và Setter cho các thuộc tính

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTinchi() {
        return tinchi;
    }

    public void setTinchi(int tinchi) {
        this.tinchi = tinchi;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", tinchi=" + tinchi + '}';
    }
    
}

