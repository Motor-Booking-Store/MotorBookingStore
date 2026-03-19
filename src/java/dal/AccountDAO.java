/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.UserDetailDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
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
                user.setRoleId(rs.getInt("roleId"));
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
                user.userName = rs.getString("userName");
                user.email = rs.getString("email");
                user.roleId = rs.getInt("roleId");
                user.avatar = rs.getString("avatar");

                return user;
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO Users "
                + "(userName, password, email, citizen_id, firstName, lastName, phoneNumber, licenseNumber, address, roleId, bankNumber) "
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

            return ps.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String sql = "SELECT userID, userName, email, citizen_id, firstName, lastName, phoneNumber, licenseNumber, address, bankNumber, roleId FROM Users";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();

                u.setUserID(rs.getInt("userID"));
                u.setUserName(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
                u.setCitizenId(rs.getString("citizen_id"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setLicenseNumber(rs.getString("licenseNumber"));
                u.setAddress(rs.getString("address"));
                u.setBankNumber(rs.getString("bankNumber"));
                u.setRoleId(rs.getInt("roleId"));

                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE userID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                User u = new User();

                u.setUserID(rs.getInt("userID"));
                u.setUserName(rs.getString("userName"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setCitizenId(rs.getString("citizen_id"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setLicenseNumber(rs.getString("licenseNumber"));
                u.setAddress(rs.getString("address"));
                u.setBankNumber(rs.getString("bankNumber"));
                u.setRoleId(rs.getInt("roleId"));

                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // nếu không tìm thấy
    }

    public boolean deleteUser(int userID) {
        String deleteReviews = "DELETE FROM Reviews WHERE userId = ?";
        String deleteRentalDetails = "DELETE FROM RentalDetails WHERE rentalId IN (SELECT rentalId FROM Rentals WHERE userID = ?)";
        String deleteRentals = "DELETE FROM Rentals WHERE userID = ?";
        String deleteUser = "DELETE FROM Users WHERE userID = ?";

        try {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = connection.prepareStatement(deleteReviews); PreparedStatement ps2 = connection.prepareStatement(deleteRentalDetails); PreparedStatement ps3 = connection.prepareStatement(deleteRentals); PreparedStatement ps4 = connection.prepareStatement(deleteUser)) {
                ps1.setInt(1, userID);
                ps1.executeUpdate();

                ps2.setInt(1, userID);
                ps2.executeUpdate();

                ps3.setInt(1, userID);
                ps3.executeUpdate();

                ps4.setInt(1, userID);
                int rows = ps4.executeUpdate();

                connection.commit();
                return rows > 0;
            }

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET "
                + "userName = ?, password = ?, email = ?, citizen_id = ?, "
                + "firstName = ?, lastName = ?, phoneNumber = ?, licenseNumber = ?, "
                + "address = ?, bankNumber = ?, roleId = ?, updatedAt = GETDATE() "
                + "WHERE userID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getCitizenId());
            ps.setString(5, user.getFirstName());
            ps.setString(6, user.getLastName());
            ps.setString(7, user.getPhoneNumber());
            ps.setString(8, user.getLicenseNumber());
            ps.setString(9, user.getAddress());
            ps.setString(10, user.getBankNumber());
            ps.setInt(11, user.getRoleId());
            ps.setInt(12, user.getUserID());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
