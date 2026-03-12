package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Rental;
import model.RentalStatus;

public class RentalDAO {

    public List<Rental> getPendingRentals() {

        List<Rental> list = new ArrayList<>();

        String sql = "SELECT * FROM Rentals WHERE status = ?";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, RentalStatus.Pending.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Rental r = new Rental(
                        rs.getInt("rentalId"),
                        rs.getDate("rentalDate"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("status"),
                        rs.getDouble("totalAmount"),
                        rs.getInt("userID"),
                        rs.getDate("createdAt"),
                        rs.getBoolean("isPaid")
                );

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void approveRental(int rentalId) {

        String sql = "UPDATE Rentals SET status=? WHERE rentalId=?";

        try {

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, RentalStatus.Approved.name());
            ps.setInt(2, rentalId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
