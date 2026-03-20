package dal;

import context.DBContext;
import dto.LocationDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LocationDAO extends DBContext {

    public ArrayList<LocationDTO> getAllLocations() {
        ArrayList<LocationDTO> list = new ArrayList<>();
        String sql = "SELECT locationId, locationName FROM Locations";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new LocationDTO(
                        rs.getInt("locationId"),
                        rs.getString("locationName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}