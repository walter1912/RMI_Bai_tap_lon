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
import models.MonHoc;
import server.interfaces.MonHocInterface;

public class MonHocService extends UnicastRemoteObject implements MonHocInterface {

    private ConnectDb connectDb;

    public MonHocService() throws RemoteException {
        super();
        connectDb = new ConnectDb();
    }

    @Override
    public void addMonHoc(MonHoc monHoc) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "INSERT INTO MonHoc (ma, ten, tinchi) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, monHoc.getMa());
            statement.setString(2, monHoc.getTen());
            statement.setInt(3, monHoc.getTinchi());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMonHoc(MonHoc monHoc) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "UPDATE MonHoc SET ma=?, ten=?, tinchi=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, monHoc.getMa());
            statement.setString(2, monHoc.getTen());
            statement.setInt(3, monHoc.getTinchi());
            statement.setInt(4, monHoc.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMonHoc(int id) throws RemoteException {
        Connection connection = connectDb.getConnection();
        try {
            String query = "DELETE FROM MonHoc WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MonHoc> getAllMonHoc() throws RemoteException {
        List<MonHoc> monHocList = new ArrayList<>();
        Connection connection = connectDb.getConnection();
        try {
            String query = "SELECT id, ma, ten, tinchi FROM MonHoc";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ma = resultSet.getString("ma");
                String ten = resultSet.getString("ten");
                int tinchi = resultSet.getInt("tinchi");

                MonHoc monHoc = new MonHoc(id, ma, ten, tinchi);
                monHocList.add(monHoc);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monHocList;
    }

    @Override
    public MonHoc getById(int id) {
        Connection connection = connectDb.getConnection();
        MonHoc monHoc = null;
        try {
            String query = "SELECT ma, ten, tinchi FROM MonHoc WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String ma = resultSet.getString("ma");
                String ten = resultSet.getString("ten");
                int tinchi = resultSet.getInt("tinchi");

                monHoc = new MonHoc(id, ma, ten, tinchi);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monHoc;
    }
}
