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

public class SinhVien implements Serializable {

    private int id;
    private String ma;
    private String ten;
    private String address;
    private String birthday;

    public SinhVien() {
    }

    public SinhVien(String ma, String ten, String address, String birthday) {
        this.ma = ma;
        this.ten = ten;
        this.address = address;
        this.birthday = birthday;
    }

    public SinhVien(int id, String ma, String ten, String address, String birthday) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.address = address;
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", address=" + address + ", birthday=" + birthday + '}';
    }
}
