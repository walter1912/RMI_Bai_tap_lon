package server.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author THAIHB.B19CN638
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import models.SinhVienHoc;

public interface SinhVienHocInterface extends Remote {

    SinhVienHoc addSinhVienHoc(SinhVienHoc sinhVienHoc) throws RemoteException;

    List<SinhVienHoc> getAllSinhVienHoc() throws RemoteException;

    SinhVienHoc getById(int id) throws RemoteException;
}
