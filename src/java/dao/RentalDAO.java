package dao;

import context.DBContext;
import dto.PendingRentalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Rental;
import models.RentalStatus;

public class RentalDAO {

    public List<PendingRentalDTO> getPendingRentals() {

        List<PendingRentalDTO> list = new ArrayList<>();

        String sql
                = "SELECT \n"
                + "    r.rentalId,\n"
                + "    r.userID,\n"
                + "    rd.bikeId,\n"
                + "    u.firstName,\n"
                + "    u.lastName,\n"
                + "    m.bikeName,\n"
                + "    r.startDate,\n"
                + "    r.endDate,\n"
                + "    r.totalAmount,\n"
                + "    r.status\n"
                + "FROM Rentals r\n"
                + "JOIN Users u ON r.userID = u.userID\n"
                + "JOIN RentalDetails rd ON r.rentalId = rd.rentalId\n"
                + "JOIN Motorbikes m ON rd.bikeId = m.bikeId\n"
                + "WHERE r.status = ?";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, RentalStatus.Pending.name());

            ResultSet rs = ps.executeQuery();

            //ResultSet mapping
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

        String sql = "UPDATE Rentals SET status=? WHERE rentalId=?";

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
