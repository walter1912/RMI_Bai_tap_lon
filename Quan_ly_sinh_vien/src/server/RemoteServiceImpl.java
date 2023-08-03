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
// RemoteServiceImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {
    public RemoteServiceImpl() throws RemoteException {
        // Constructor phải khai báo ngoại lệ RemoteException
        super();
    }
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "!";
    }
}
