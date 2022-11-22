package com.example.assignment;

import java.time.LocalDate;

public class VaccinationRecord {
    private LocalDate vaxxDate;
    private String vaxxTime;
    private String vaxxType;
    private String vaxxBatch;
    private String vaxxDetails;
    private String patientPPSN;

    public String getPatientPPSN() {
        return patientPPSN;
    }

    public void setPatientPPSN(String patientPPSN) {
        this.patientPPSN = patientPPSN;
    }

    public LocalDate getVaxxDate() {
        return vaxxDate;
    }

    public void setVaxxDate(LocalDate vaxxDate) {
        this.vaxxDate = vaxxDate;
    }

    public String getVaxxTime() {
        return vaxxTime;
    }

    public void setVaxxTime(String vaxxTime) {
        this.vaxxTime = vaxxTime;
    }

    public String getVaxxType() {
        return vaxxType;
    }

    public void setVaxxType(String vaxxType) {
        this.vaxxType = vaxxType;
    }

    public String getVaxxBatch() {
        return vaxxBatch;
    }

    public void setVaxxBatch(String vaxxBatch) {
        this.vaxxBatch = vaxxBatch;
    }

    public String getVaxxDetails() {
        return vaxxDetails;
    }

    public void setVaxxDetails(String vaxxDetails) {
        this.vaxxDetails = vaxxDetails;
    }

    public VaccinationRecord(LocalDate vaxxDate, String vaxxTime, String vaxxType, String vaxxBatch, String vaxxDetails, String patientPPSN) {
        this.vaxxDate = vaxxDate;
        this.vaxxTime = vaxxTime;
        this.vaxxType = vaxxType;
        this.vaxxBatch = vaxxBatch;
        this.vaxxDetails = vaxxDetails;
        this.patientPPSN = patientPPSN;
    }

    @Override
    public String toString() {
        return getPatientPPSN() + ": " + getVaxxDate() + ": " + getVaxxType() + ": " + getVaxxTime() + ": " + getVaxxBatch() + ": " + getVaxxDetails();
    }

}
