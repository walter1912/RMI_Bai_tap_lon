/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author THAIHB.B19CN638
 */
// RemoteService.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
    // Khai báo các phương thức mà client có thể gọi từ xa
    // Ví dụ:
    int add(int a, int b) throws RemoteException;
    String sayHello(String name) throws RemoteException;
}