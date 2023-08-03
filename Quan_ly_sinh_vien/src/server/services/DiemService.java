/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.services;

/**
 *
 * @author THAIHB.B19CN638
 */
import database.ConnectDb;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Diem;
import server.interfaces.DiemInterface;

public class DiemService extends UnicastRemoteObject implements DiemInterface {
    private ConnectDb connectDb;

    public DiemService() throws RemoteException {
        super();
        connectDb = new ConnectDb();
    }

    @Override
    public Diem addDiem(Diem diem) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "INSERT INTO Diem (sinhVienHocId, cc, btl, thi) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, diem.getSinhVienHocId());
            statement.setFloat(2, diem.getCc());
            statement.setFloat(3, diem.getBtl());
            statement.setFloat(4, diem.getThi());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diem;
    }

    @Override
    public Diem updateDiem(Diem diem) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "UPDATE Diem SET cc=?, btl=?, thi=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, diem.getCc());
            statement.setFloat(2, diem.getBtl());
            statement.setFloat(3, diem.getThi());
            statement.setInt(4, diem.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return diem;
    }

    @Override
    public void deleteDiem(int id) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "DELETE FROM Diem WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Diem> getAllDiem() throws RemoteException {
        List<Diem> diemList = new ArrayList<>();
        Connection connection = connectDb.getConnection();
        try {
            String query = "SELECT id, sinhVienHocId, cc, btl, thi FROM Diem";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int sinhVienHocId = resultSet.getInt("sinhVienHocId");
                float cc = resultSet.getFloat("cc");
                float btl = resultSet.getFloat("btl");
                float thi = resultSet.getFloat("thi");

                Diem diem = new Diem(id, sinhVienHocId, cc, btl, thi);
                diemList.add(diem);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diemList;
    }
}
