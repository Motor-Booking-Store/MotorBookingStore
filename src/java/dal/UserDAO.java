package dal;

import context.DBContext;
import dto.UserDetailDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public void EditUserDetail(int userId, UserDetailDTO dto) {
        String sql = "UPDATE Users SET userName=?, firstName=?, lastName=?, phoneNumber=?, licenseNumber=?, address=?, avatar=?, updatedAt=GETDATE() WHERE userID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, dto.getUserName());
            stm.setString(2, dto.getFirstName());
            stm.setString(3, dto.getLastName());
            stm.setString(4, dto.getPhoneNumber());
            stm.setString(5, dto.getLicenseNumber());
            stm.setString(6, dto.getAddress());
            stm.setString(7, dto.getAvatar());
            stm.setInt(8, userId);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDetailDTO GetUserDetailById(int userId) {
        String sql = "SELECT u.userName, u.email, u.firstName, u.lastName, "
                + "u.phoneNumber, u.licenseNumber, u.address, u.bankNumber, u.avatar, r.roleName "
                + "FROM Users u "
                + "JOIN Roles r ON u.roleId = r.roleId "
                + "WHERE u.userID = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Dùng constructor đầy đủ để tạo DTO luôn
                return new UserDetailDTO(
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("licenseNumber"),
                        rs.getString("address"),
                        rs.getString("bankNumber"),
                        rs.getString("avatar"),
                        rs.getString("roleName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
