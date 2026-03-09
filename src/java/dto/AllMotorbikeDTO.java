/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author nguye
 */
public class AllMotorbikeDTO {

    private int bikeId;
    private String bikeName;
    private String brand;
    private String model;
    private String licensePlate;
    private double pricePerDay;
    private String image;
    private String status;

    public AllMotorbikeDTO(int bikeId, String bikeName, String brand, String model, String licensePlate, double pricePerDay, String image, String status) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
        this.image = image;
        this.status = status;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
