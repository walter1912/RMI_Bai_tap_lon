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
import models.MonHoc;

public interface MonHocInterface extends Remote {
    MonHoc addMonHoc(MonHoc monHoc) throws RemoteException;
    MonHoc updateMonHoc(MonHoc monHoc) throws RemoteException;
    void deleteMonHoc(int id) throws RemoteException;
    List<MonHoc> getAllMonHoc() throws RemoteException;

    MonHoc getById(int id) throws RemoteException;
}

