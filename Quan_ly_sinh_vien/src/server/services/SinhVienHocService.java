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
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SinhVienHoc;
import server.interfaces.SinhVienHocInterface;

public class SinhVienHocService extends UnicastRemoteObject implements SinhVienHocInterface {

    private ConnectDb connectDb;

    public SinhVienHocService() throws RemoteException {
        super();
        connectDb = new ConnectDb();
    }

    @Override
    public SinhVienHoc addSinhVienHoc(SinhVienHoc sinhVienHoc) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "INSERT INTO SinhVienHoc (sinhVienId, monHocId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sinhVienHoc.getSinhVienId());
            statement.setInt(2, sinhVienHoc.getMonHocId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Thêm sinhVienHoc thất bại, không có bản ghi nào được thêm.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                sinhVienHoc.setId(id); // Gán id cho sinhVienHoc sau khi insert thành công.
            } else {
                throw new SQLException("Thêm sinhVienHoc thất bại, không lấy được id.");
            }
            generatedKeys.close();
            statement.close();
            return sinhVienHoc;
        } catch (SQLException e) {
            e.printStackTrace();
            return sinhVienHoc;
        }
    }

    @Override
    public List<SinhVienHoc> getAllSinhVienHoc() throws RemoteException {
        List<SinhVienHoc> sinhVienHocList = new ArrayList<>();
        Connection connection = connectDb.getConnection();
        try {
            String query = "SELECT id, sinhVienId, monHocId FROM SinhVienHoc";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int sinhVienId = resultSet.getInt("sinhVienId");
                int monHocId = resultSet.getInt("monHocId");

                SinhVienHoc sinhVienHoc = new SinhVienHoc(id, sinhVienId, monHocId);
                sinhVienHocList.add(sinhVienHoc);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVienHocList;
    }

    @Override
    public SinhVienHoc getById(int id) throws RemoteException {
        Connection connection = connectDb.getConnection();
        SinhVienHoc sinhVienHoc = null;
        try {
            String query = "SELECT sinhVienId, monHocId FROM SinhVienHoc WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int sinhVienId = resultSet.getInt("sinhVienId");

                int monHocId = resultSet.getInt("monHocId");

                sinhVienHoc = new SinhVienHoc(id, sinhVienId, monHocId);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhVienHoc;
    }


}
