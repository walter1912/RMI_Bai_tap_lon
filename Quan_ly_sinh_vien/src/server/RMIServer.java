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
// RMIServer.java
import database.ConnectDb;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import server.interfaces.DiemInterface;
import server.interfaces.MonHocInterface;
import server.interfaces.SinhVienHocInterface;
import server.interfaces.SinhVienInterface;
import server.services.DiemService;
import server.services.MonHocService;
import server.services.SinhVienHocService;
import server.services.SinhVienService;

public class RMIServer {

    private static Registry registry;
    private static int port = 1912;

    public RMIServer() {
    }

    public static void run() {
        try {
            // Tạo một đối tượng của SinhVienService
            SinhVienInterface sinhVienService = new SinhVienService();

            // Tạo một đối tượng của MonHocService
            MonHocInterface monHocService = new MonHocService();

            // Tạo một đối tượng của DiemService
            DiemInterface diemService = new DiemService();

            // Tạo một đối tượng của SInhVienHocService
            SinhVienHocInterface sinhVienHocService = new SinhVienHocService();

            // Đăng ký các đối tượng service vào registry với các tên tương ứng
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("SinhVienRemote", sinhVienService);
            registry.rebind("MonHocRemote", monHocService);
            registry.rebind("DiemRemote", diemService);
            registry.rebind("SinhVienHocRemote", sinhVienHocService);

            System.out.println("RMIServer đã được khởi chạy...");
            System.out.println("Server is ready in port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        if (registry != null) {
            try {
                // Hủy đăng ký đối tượng RemoteService
                UnicastRemoteObject.unexportObject(registry, true);
                System.out.println("RMI Registry closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có server nào chạy ở port này");
        }
    }
}
