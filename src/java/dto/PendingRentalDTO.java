package dto;

import java.util.Date;

public class PendingRentalDTO {

    private int rentalId;
    private int userId;
    private int bikeId;

    private String firstName;
    private String lastName;
    private String bikeName;

    private Date startDate;
    private Date endDate;

    private double totalAmount;
    private String status;

    public PendingRentalDTO(int rentalId, int userId, int bikeId,
            String firstName, String lastName,
            String bikeName, Date startDate,
            Date endDate, double totalAmount, String status) {

        this.rentalId = rentalId;
        this.userId = userId;
        this.bikeId = bikeId;

        this.firstName = firstName;
        this.lastName = lastName;
        this.bikeName = bikeName;

        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getRentalId() { return rentalId; }
    public int getUserId() { return userId; }
    public int getBikeId() { return bikeId; }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBikeName() { return bikeName; }

    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
}