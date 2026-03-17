package dal;

import context.DBContext;
import dto.AllMotorbikeDTO;
import dto.MotorbikeDetailDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Motorbike;

public class MotorbikeDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public ArrayList<AllMotorbikeDTO> getAllMotorbikes() {

        ArrayList<AllMotorbikeDTO> list = new ArrayList<>();

        String sql = "SELECT bikeId, bikeName, brand, model, licensePlate, pricePerDay, image, status FROM Motorbikes";

        try {

            stm = connection.prepareCall(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                AllMotorbikeDTO bike = new AllMotorbikeDTO(
                        rs.getInt("bikeId"),
                        rs.getString("bikeName"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("licensePlate"),
                        rs.getDouble("pricePerDay"),
                        rs.getString("image"),
                        rs.getString("status")
                );

                list.add(bike);
            }
            rs.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public MotorbikeDetailDTO getMotorbikeDetail(int id) {
        String sql = """
                 SELECT m.*, l.locationName, l.address
                 FROM Motorbikes m
                 JOIN Locations l ON m.locationId = l.locationId
                 WHERE m.bikeId = ?
                 """;

        try {
            stm = connection.prepareCall(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();
            if (rs.next()) {

                MotorbikeDetailDTO bike = new MotorbikeDetailDTO();

                bike.setBikeId(rs.getInt("bikeId"));
                bike.setBikeName(rs.getString("bikeName"));
                bike.setBrand(rs.getString("brand"));
                bike.setModel(rs.getString("model"));
                bike.setLicensePlate(rs.getString("licensePlate"));
                bike.setPricePerDay(rs.getDouble("pricePerDay"));
                bike.setDescription(rs.getString("description"));
                bike.setImage(rs.getString("image"));
                bike.setStatus(rs.getString("status"));

                bike.setLocationId(rs.getInt("locationId"));
                bike.setLocationName(rs.getString("locationName"));
                bike.setAddress(rs.getString("address"));

                bike.setCreatedAt(rs.getDate("createdAt"));
                bike.setUpdatedAt(rs.getDate("updatedAt"));

                return bike;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public boolean isBikeAvailable(int bikeId) {
        String sql = "SELECT 1 FROM Motorbikes WHERE bikeId = ? AND status = 'Available'";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bikeId);
            rs = stm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int countPendingRentalsByUser(int userId) {
        String sql = """
        SELECT COUNT(*) AS total
        FROM Rentals
        WHERE userID = ?
          AND status = 'Pending'
        """;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean hasConflictingRental(int bikeId, java.sql.Date startDate, java.sql.Date endDate) {
        String sql = """
        SELECT 1
        FROM Rentals r
        JOIN RentalDetails rd ON r.rentalId = rd.rentalId
        WHERE rd.bikeId = ?
          AND r.status IN ('Pending', 'Approved')
          AND r.startDate <= ?
          AND r.endDate >= ?
        """;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bikeId);
            stm.setDate(2, endDate);
            stm.setDate(3, startDate);
            rs = stm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isBikeRentedToday(int bikeId) {
        String sql = """
        SELECT 1
        FROM Rentals r
        JOIN RentalDetails rd ON r.rentalId = rd.rentalId
        JOIN Motorbikes m ON m.bikeId = rd.bikeId
        WHERE rd.bikeId = ?
          AND m.status != 'Maintenance'
          AND r.status = 'Approved'
          AND CAST(GETDATE() AS DATE) BETWEEN r.startDate AND r.endDate
        """;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bikeId);
            rs = stm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<AllMotorbikeDTO> searchMotorbikesByName(String keyword) {
        ArrayList<AllMotorbikeDTO> list = new ArrayList<>();
        String sql = "SELECT bikeId, bikeName, brand, model, licensePlate, pricePerDay, image, status "
                + "FROM Motorbikes WHERE LOWER(bikeName) LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                AllMotorbikeDTO bike = new AllMotorbikeDTO(
                        rs.getInt("bikeId"),
                        rs.getString("bikeName"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("licensePlate"),
                        rs.getDouble("pricePerDay"),
                        rs.getString("image"),
                        rs.getString("status")
                );
                list.add(bike);
            }
            rs.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<AllMotorbikeDTO> filterMotorbikes(String bikeName, String brand, Double minPrice, Double maxPrice, String status) {
        ArrayList<AllMotorbikeDTO> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT bikeId, bikeName, brand, model, licensePlate, pricePerDay, image, status
        FROM Motorbikes
        WHERE 1=1
    """);

        ArrayList<Object> params = new ArrayList<>();

        if (bikeName != null && !bikeName.trim().isEmpty()) {
            sql.append(" AND LOWER(bikeName) LIKE ?");
            params.add("%" + bikeName.trim().toLowerCase() + "%");
        }

        if (brand != null && !brand.trim().isEmpty()) {
            sql.append(" AND brand = ?");
            params.add(brand.trim());
        }

        if (minPrice != null) {
            sql.append(" AND pricePerDay >= ?");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            sql.append(" AND pricePerDay <= ?");
            params.add(maxPrice);
        }

        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND status = ?");
            params.add(status.trim());
        }

        try {
            stm = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                stm.setObject(i + 1, params.get(i));
            }

            rs = stm.executeQuery();

            while (rs.next()) {
                AllMotorbikeDTO bike = new AllMotorbikeDTO(
                        rs.getInt("bikeId"),
                        rs.getString("bikeName"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("licensePlate"),
                        rs.getDouble("pricePerDay"),
                        rs.getString("image"),
                        rs.getString("status")
                );
                list.add(bike);
            }

            rs.close();
            stm.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<String> getAllBrands() {
        ArrayList<String> brands = new ArrayList<>();
        String sql = "SELECT DISTINCT brand FROM Motorbikes WHERE brand IS NOT NULL ORDER BY brand";

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                brands.add(rs.getString("brand"));
            }

            rs.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return brands;
    }

    public List<Motorbike> getAllNewMotorbike() {
        List<Motorbike> list = new ArrayList<>();

        String sql = "SELECT TOP 3 * FROM Motorbikes ORDER BY createdAt DESC";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Motorbike bike = new Motorbike(
                        rs.getInt("bikeId"),
                        rs.getString("bikeName"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("licensePlate"),
                        rs.getDouble("pricePerDay"),
                        rs.getInt("locationId"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt")
                );

                list.add(bike);
            }
            
            rs.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
