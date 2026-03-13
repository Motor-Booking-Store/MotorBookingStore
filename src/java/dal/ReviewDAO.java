/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.ReviewDTO;
import dto.ReviewListDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<ReviewListDTO> GetAllReviewsBikeId(int bikeId) {
        List<ReviewListDTO> list = new ArrayList<>();
        String sql = "SELECT u.userName, r.comment, u.avatar, r.rating "
                + "FROM Reviews r "
                + "JOIN Users u ON r.userId = u.userID "
                + "WHERE r.bikeId = ? "
                + "ORDER BY r.createdAt DESC";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bikeId);
            rs = stm.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("userName");
                String comment = rs.getString("comment");
                String avatar = rs.getString("avatar");
                int rating = rs.getInt("rating");
                list.add(new ReviewListDTO(userName, comment, avatar, rating));
            }
            rs.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addReview(ReviewDTO review) {
        String sql = "INSERT INTO Reviews(userId, bikeId, rating, comment) VALUES (?, ?, ?, ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, review.getUserId());
            stm.setInt(2, review.getBikeId());
            stm.setInt(3, review.getRating());
            stm.setString(4, review.getComment());
            stm.executeUpdate();

            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
