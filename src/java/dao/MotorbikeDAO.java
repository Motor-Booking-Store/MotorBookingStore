package dao;

import context.DBContext;
import dto.AllMotorbikeDTO;
import dto.MotorbikeDetailDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
