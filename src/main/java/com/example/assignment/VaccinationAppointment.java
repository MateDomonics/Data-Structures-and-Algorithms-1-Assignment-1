package com.example.assignment;

import java.time.LocalDate;

public class VaccinationAppointment {
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

    public String getVaxxTime() {
        return vaxxTime;
    }

    public String getVaxxType() {
        return vaxxType;
    }

    public String getVaxxBatch() {
        return vaxxBatch;
    }

    public String getVaxxDetails() {
        return vaxxDetails;
    }

    public void setVaxxDate(LocalDate vaxxDate) {
        this.vaxxDate = vaxxDate;
    }

    public void setVaxxTime(String vaxxTime) {
        this.vaxxTime = vaxxTime;
    }

    public void setVaxxType(String vaxxType) {
        this.vaxxType = vaxxType;
    }

    public void setVaxxBatch(String vaxxBatch) {
        this.vaxxBatch = vaxxBatch;
    }

    public void setVaxxDetails(String vaxxDetails) {
        this.vaxxDetails = vaxxDetails;
    }

    public VaccinationAppointment(LocalDate vaxxDate, String vaxxTime, String vaxxType, String vaxxBatch, String vaxxDetails, String patientPPSN) {
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
