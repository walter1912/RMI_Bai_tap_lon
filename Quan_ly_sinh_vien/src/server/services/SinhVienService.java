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
import java.sql.ResultSet;
import database.ConnectDb;
import models.SinhVien;
import server.interfaces.SinhVienInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienService extends UnicastRemoteObject implements SinhVienInterface {

    private ConnectDb connectDb;

    public SinhVienService() throws RemoteException {
        super();
        connectDb = new ConnectDb();
    }

    @Override
    public SinhVien addSinhVien(SinhVien sinhVien) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "INSERT INTO SinhVien (ma, ten, address, birthday) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sinhVien.getMa());
            statement.setString(2, sinhVien.getTen());
            statement.setString(3, sinhVien.getAddress());
            statement.setString(4, sinhVien.getBirthday());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhVien;
    }

    @Override
    public SinhVien updateSinhVien(SinhVien sinhVien) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "UPDATE SinhVien SET ma=?, ten=?, address=?, birthday=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sinhVien.getMa());
            statement.setString(2, sinhVien.getTen());
            statement.setString(3, sinhVien.getAddress());
            statement.setString(4, sinhVien.getBirthday());
            statement.setInt(5, sinhVien.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return sinhVien;
    }

    @Override
    public void deleteSinhVien(int id) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "DELETE FROM SinhVien WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SinhVien> getAllSinhVien() throws RemoteException {
        List<SinhVien> sinhVienList = new ArrayList<>();
        Connection connection = connectDb.getConnection();
        try {
            String query = "SELECT id, ma, ten, address, birthday FROM SinhVien";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ma = resultSet.getString("ma");
                String ten = resultSet.getString("ten");
                String address = resultSet.getString("address");
                String birthday = resultSet.getString("birthday");

                SinhVien sinhVien = new SinhVien(id, ma, ten, address, birthday);
                sinhVienList.add(sinhVien);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVienList;
    }

    @Override
    public List<SinhVien> searchSinhVien(String type, String value) {
        List<SinhVien> sinhVienList = new ArrayList<>();
        Connection connection = connectDb.getConnection();
        try {
            String query;
            switch (type) {
                case "ten":
                    query = "SELECT id, ma, ten, address, birthday FROM SinhVien WHERE ten LIKE ?";
                    break;
                case "address":
                    query = "SELECT id, ma, ten, address, birthday FROM SinhVien WHERE address LIKE ?";
                    break;
                case "birthday":
                    query = "SELECT id, ma, ten, address, birthday FROM SinhVien WHERE birthday LIKE ?";
                    break;
                default:
                    // Loại tìm kiếm không hợp lệ, trả về danh sách rỗng.
                    return sinhVienList;
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + value + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ma = resultSet.getString("ma");
                String ten = resultSet.getString("ten");
                String address = resultSet.getString("address");
                String birthday = resultSet.getString("birthday");

                SinhVien sinhVien = new SinhVien(id, ma, ten, address, birthday);
                sinhVienList.add(sinhVien);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVienList;
    }

    @Override
    public SinhVien getById(int id) {
        Connection connection = connectDb.getConnection();
        SinhVien sinhVien = null;
        try {
            String query = "SELECT ma, ten, address, birthday FROM SinhVien WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String ma = resultSet.getString("ma");
                String ten = resultSet.getString("ten");
                String address = resultSet.getString("address");
                String birthday = resultSet.getString("birthday");

                sinhVien = new SinhVien(id, ma, ten, address, birthday);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhVien;
    }
}
