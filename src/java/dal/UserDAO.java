package dal;

import context.DBContext;
import dto.UserDetailDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

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
