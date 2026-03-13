package dal;

import context.DBContext;
import dto.PendingRentalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.RentalStatus;

public class RentalDAO {

    public List<PendingRentalDTO> getRentalsByStatus(String status) {

        List<PendingRentalDTO> list = new ArrayList<>();

        String sql
                = "SELECT "
                + "r.rentalId, "
                + "r.userID, "
                + "rd.bikeId, "
                + "u.firstName, "
                + "u.lastName, "
                + "m.bikeName, "
                + "r.startDate, "
                + "r.endDate, "
                + "r.totalAmount, "
                + "r.status "
                + "FROM Rentals r "
                + "JOIN Users u ON r.userID = u.userID "
                + "JOIN RentalDetails rd ON r.rentalId = rd.rentalId "
                + "JOIN Motorbikes m ON rd.bikeId = m.bikeId ";

        if (status != null && !status.equalsIgnoreCase("all")) {
            sql += " WHERE r.status = ?";
        }

        sql += " ORDER BY r.createdAt DESC";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            if (status != null && !status.equalsIgnoreCase("all")) {
                ps.setString(1, status);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PendingRentalDTO dto = new PendingRentalDTO(
                        rs.getInt("rentalId"),
                        rs.getInt("userID"),
                        rs.getInt("bikeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("bikeName"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status")
                );

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<PendingRentalDTO> getRentalsByUser(int userId, String status) {

        List<PendingRentalDTO> list = new ArrayList<>();

        String sql
                = "SELECT "
                + "r.rentalId, "
                + "r.userID, "
                + "rd.bikeId, "
                + "u.firstName, "
                + "u.lastName, "
                + "m.bikeName, "
                + "r.startDate, "
                + "r.endDate, "
                + "r.totalAmount, "
                + "r.status "
                + "FROM Rentals r "
                + "JOIN Users u ON r.userID = u.userID "
                + "JOIN RentalDetails rd ON r.rentalId = rd.rentalId "
                + "JOIN Motorbikes m ON rd.bikeId = m.bikeId "
                + "WHERE r.userID = ? ";

        if (status != null && !status.equalsIgnoreCase("all")) {
            sql += " AND r.status = ?";
        }

        sql += " ORDER BY r.createdAt DESC";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            if (status != null && !status.equalsIgnoreCase("all")) {
                ps.setString(2, status);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PendingRentalDTO dto = new PendingRentalDTO(
                        rs.getInt("rentalId"),
                        rs.getInt("userID"),
                        rs.getInt("bikeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("bikeName"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status")
                );

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateRentalStatus(int rentalId, String status) {

        String sql = "UPDATE Rentals SET status=? WHERE rentalId=? AND status='" + RentalStatus.Pending.name() + "'";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, rentalId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
