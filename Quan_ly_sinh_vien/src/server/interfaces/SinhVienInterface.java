/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

/**
 *
 * @author THAIHB.B19CN638
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import models.SinhVien;

public interface SinhVienInterface extends Remote {
    void addSinhVien(SinhVien sinhVien) throws RemoteException;
    void updateSinhVien(SinhVien sinhVien) throws RemoteException;
    void deleteSinhVien(int id) throws RemoteException;
    SinhVien getById(int id) throws RemoteException;
    List<SinhVien> getAllSinhVien() throws RemoteException;
    List<SinhVien> searchSinhVien(String type, String value) throws RemoteException;
}

