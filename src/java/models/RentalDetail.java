package models;

public class RentalDetail {
    public int rentalId;
    public int bikeId;

    public double pricePerDay;

    public RentalDetail(int rentalId, int bikeId, double pricePerDay) {
        this.rentalId = rentalId;
        this.bikeId = bikeId;
        this.pricePerDay = pricePerDay;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }    
}
