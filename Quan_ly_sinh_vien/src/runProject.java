
import client.ClientConsole;
import database.ConnectDb;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.RMIServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author THAIHB.B19CN638
 */
public class runProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AccessException, NotBoundException {
        // TODO code application logic here
//        chạy server 
        RMIServer server = new RMIServer();
        try {
             server.run();
            ClientConsole client = new ClientConsole();
            client.run();
        } catch (Exception ex) {
            System.out.println("Xuất hiện lỗi");
            ex.printStackTrace();
            server.close();
           
        }

    }
}
