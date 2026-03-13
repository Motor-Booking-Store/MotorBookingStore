/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.User;

/**
 *
 * @author pc
 */
public class AccountDAO extends DBContext {

    public User checkUnique(String fieldName, String value) {
        User user = null;

        String sql = "SELECT * FROM Users WHERE " + fieldName + " = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean createAccount(User user) {

        String sql = "INSERT INTO Users "
                + "(userName, password, email, citizen_id, firstName, lastName, phoneNumber, licenseNumber, address, roleId, bankNumber, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.userName);
            ps.setString(2, user.password);
            ps.setString(3, user.email);
            ps.setString(4, user.citizen_id);
            ps.setString(5, user.firstName);
            ps.setString(6, user.lastName);
            ps.setString(7, user.phoneNumber);
            ps.setString(8, user.licenseNumber);
            ps.setString(9, user.address);
            ps.setInt(10, user.roleId);
            ps.setString(11, user.getBankNumber());
            ps.setTimestamp(12, new java.sql.Timestamp(user.createdAt.getTime()));
            ps.setTimestamp(13, new java.sql.Timestamp(user.updatedAt.getTime()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public User login(String email, String password) {

        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.userID = rs.getInt("userID");
                user.userName = rs.getString("username");
                user.email = rs.getString("email");
                user.roleId = rs.getInt("roleId");

                return user;
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
