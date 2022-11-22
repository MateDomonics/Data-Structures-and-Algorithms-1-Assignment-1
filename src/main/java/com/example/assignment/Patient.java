package com.example.assignment;

import java.time.LocalDate;

public class Patient {
    private String ppsn;
    private String name;
    private LocalDate dob;
    private String eircode;
    private String phoneNumber;
    private String email;
    private String accessibilityInfo;
    public VeryFunLinkedList<VaccinationRecord> records = new VeryFunLinkedList<>();

    public VeryFunLinkedList<VaccinationRecord> getRecords() {
        return records;
    }

    public void setRecords(VeryFunLinkedList<VaccinationRecord> records) {
        this.records = records;
    }

    public String getPpsn() {
        return ppsn;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEircode() {
        return eircode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccessibilityInfo() {
        return accessibilityInfo;
    }

    public void setPpsn(String ppsn) {
        if (Utilities.validPPS(ppsn)) {
            this.ppsn = ppsn;
        } else {
            this.ppsn = "Invalid PPS Number";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEircode(String eircode) {
        if (Utilities.validEircode(eircode)) {
            this.eircode = eircode;
        } else {
            this.eircode = "Invalid Eircode";
        }
    }

    public void setEmail(String email) {
        if (Utilities.validEmail(email)) {
            this.email = email;
        } else {
            this.email = "Invalid Email";
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccessibilityInfo(String accessibilityInfo) {
        this.accessibilityInfo = accessibilityInfo;
    }

    public Patient(String ppsn, String name, LocalDate dob, String eircode, String phoneNumber, String email, String accessibilityInfo) {
        setPpsn(ppsn);
        this.name = name;
        this.dob = dob;
        setEircode(eircode);
        this.phoneNumber = phoneNumber;
        setEmail(email);
        this.accessibilityInfo = accessibilityInfo;
    }

    @Override
    public String toString() {
        return getPpsn() + ": " + getName() + ": " + getDob() + ": " + getEircode() + ": " + getPhoneNumber() + ":" + getEmail() + ": " + getAccessibilityInfo();
    }
}
