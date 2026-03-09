package dao;

import context.DBContext;
import dto.AllMotorbikeDTO;
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
}