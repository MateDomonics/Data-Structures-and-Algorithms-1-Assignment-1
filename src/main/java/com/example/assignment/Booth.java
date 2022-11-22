package com.example.assignment;

public class Booth {
    private String boothID;
    private int floorLevel;
    private String easilyAccessible;
    public VeryFunLinkedList<VaccinationAppointment> appointment = new VeryFunLinkedList<>();

    public VeryFunLinkedList<VaccinationAppointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(VeryFunLinkedList<VaccinationAppointment> appointment) {
        this.appointment = appointment;
    }

    public String getBoothID() {
        return boothID;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public String isEasilyAccessible() {
        return easilyAccessible;
    }

    public void setBoothID(String boothID) {
        if (Utilities.validBoothID(boothID)) {
            this.boothID = boothID;
        } else {
            this.boothID = "Invalid Booth ID";
        }
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public void setEasilyAccessible(String easilyAccessible) {
        this.easilyAccessible = easilyAccessible;
    }

    public Booth(String boothID, int floorLevel, String easilyAccessible) {
        setBoothID(boothID);
        this.floorLevel = floorLevel;
        this.easilyAccessible = easilyAccessible;
    }

    @Override
    public String toString() {
        return getBoothID() + ": " + getFloorLevel() + ": " + isEasilyAccessible();
    }
}
