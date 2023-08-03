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

public class Diem implements Serializable {
    private int id = 0;
    private int sinhVienHocId;
    private float cc;
    private float btl;
    private  float thi;



    public Diem() {
    }

    public Diem(int sinhVienHocId, float cc, float btl,  float thi) {
        this.sinhVienHocId = sinhVienHocId;
        this.cc = cc;
        this.btl = btl;
        this.thi = thi;
    }
    
    
    public Diem(int id, int sinhVienHocId, float cc, float btl,  float thi) {
        this.id = id;
        this.sinhVienHocId = sinhVienHocId;
        this.cc = cc;
        this.btl = btl;
        this.thi = thi;
    }

    // Getter và Setter cho các thuộc tính

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSinhVienHocId() {
        return sinhVienHocId;
    }

    public void setSinhVienHocId(int sinhVienHocId) {
        this.sinhVienHocId = sinhVienHocId;
    }

    public float getCc() {
        return cc;
    }

    public void setCc(float cc) {
        this.cc = cc;
    }

    public float getBtl() {
        return btl;
    }

    public void setBtl(float btl) {
        this.btl = btl;
    }

    public float getThi() {
        return thi;
    }

    public void setThi( float thi) {
        this.thi = thi;
    }

    @Override
    public String toString() {
        return "Diem{" + "id=" + id + ", sinhVienHocId=" + sinhVienHocId + ", cc=" + cc + ", btl=" + btl + ", thi=" + thi + '}';
    }

    
}

