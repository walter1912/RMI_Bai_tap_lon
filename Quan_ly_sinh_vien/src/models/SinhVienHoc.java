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

public class SinhVienHoc implements Serializable {
    private int id = 0;
    private int sinhVienId = 0;
    private int monHocId = 0;

    public SinhVienHoc() {
    }

    public SinhVienHoc(int sinhVienId, int monHocId) {
        this.sinhVienId = sinhVienId;
        this.monHocId = monHocId;
    }
    
    public SinhVienHoc(int id, int sinhVienId, int monHocId) {
        this.id = id;
        this.sinhVienId = sinhVienId;
        this.monHocId = monHocId;
    }

    // Getter và Setter cho các thuộc tính

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSinhVienId() {
        return sinhVienId;
    }

    public void setSinhVienId(int sinhVienId) {
        this.sinhVienId = sinhVienId;
    }

    public int getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(int monHocId) {
        this.monHocId = monHocId;
    }

    @Override
    public String toString() {
        return "SinhVienHoc{" + "id=" + id + ", sinhVienId=" + sinhVienId + ", monHocId=" + monHocId + '}';
    }
    
}
