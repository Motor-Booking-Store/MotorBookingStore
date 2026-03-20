package dto;

public class LocationDTO {
    private int locationId;
    private String locationName;

    public LocationDTO() {}

    public LocationDTO(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }
}