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
import models.Diem;

public interface DiemInterface extends Remote {
    void addDiem(Diem diem) throws RemoteException;
    void updateDiem(Diem diem) throws RemoteException;
    void deleteDiem(int id) throws RemoteException;
    List<Diem> getAllDiem() throws RemoteException;
}

