package com.example.assignment;

public class VaccinationCentre {
    private String name;
    private String address;
    private String eircode;
    private int parkingSpace;
    public VeryFunLinkedList<Booth> booths = new VeryFunLinkedList<>();

    public VeryFunLinkedList<Booth> getBooths() {
        return booths;
    }

    public void setBooths(VeryFunLinkedList<Booth> booths) {
        this.booths = booths;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEircode() {
        return eircode;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEircode(String eircode) {
        if (Utilities.validEircode(eircode)) {
            this.eircode = eircode;
        } else {
            this.eircode = "Invalid Eircode";
        }
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    @Override
    public String toString() {
        return getName() + ": " + getAddress() + ": " + getEircode() + ": " + getParkingSpace();
    }

    public VaccinationCentre(String name, String address, String eircode, int parkingSpace) {
        this.name = name;
        this.address = address;
        setEircode(eircode);
        this.parkingSpace = parkingSpace;
    }


}
