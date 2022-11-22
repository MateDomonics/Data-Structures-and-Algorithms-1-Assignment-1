package com.example.assignment;

public class Utilities {
    public static boolean validPPS(String ppsNumber) {
        if ((ppsNumber != null) && (ppsNumber.length() == 9)) {
            return (ppsNumber.matches("[0-9]{7}[A-Za-z]{2}"));
        } else {
            return false;
        }
    }

    public static boolean validEmail(String email) {
        return (email.contains("@") && email.contains("."));
    }

    //https://stackoverflow.com/questions/33391412/validation-for-irish-eircode
    public static boolean validEircode(String eircode) {
        if ((eircode != null) && (eircode.length() == 8)) {
            return (eircode.matches("(?:^[AC-FHKNPRTV-Y][0-9]{2}|D6W)[ -]?[0-9AC-FHKNPRTV-Y]{4}$"));
        } else {
            return false;
        }
    }

    public static boolean validBoothID(String boothID) {
        if ((boothID != null) && (boothID.length() == 2)) {
            return (boothID.matches("[A-Z][0-9]"));
        } else {
            return false;
        }
    }
}
