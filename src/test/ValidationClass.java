import com.example.assignment.Booth;
import com.example.assignment.Patient;
import com.example.assignment.VaccinationCentre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidationClass {
    VaccinationCentre VC, VC2, VC3;
    Patient P, P2, P3;
    Booth B, B2, B3;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.now();
        VC = new VaccinationCentre("", "", "X91 K0EK", 20);
        VC2 = new VaccinationCentre("", "", "FF FFFF", 10);
        VC3 = new VaccinationCentre("", "", "X91K0EK",1);
        P = new Patient("1234567AA", "", date, "", "", "", "");
        P2 = new Patient("12345678AB", "", date, "", "", "", "");
        P3 = new Patient("1234567ABCD", "", date, "", "", "", "");
        B = new Booth("A2", 0, "");
        B2 = new Booth("AA33", 0, "");
        B3 = new Booth("ABC1", 0, "");
    }

    @AfterEach
    void tearDown() {
        VC = VC2 = VC3 = null;
        P = P2 = P3 = null;
        B = B2 = B3 = null;
    }

    @Test
    void EircodeTest() {
        //Correct
        assertEquals("X91 K0EK", VC.getEircode());
        //Out of bounds Eircode
        assertEquals("Invalid Eircode", VC2.getEircode());
        //No Space
        assertEquals("Invalid Eircode", VC3.getEircode());
    }

    @Test
    void PPSNTest() {
        //Correct
        assertEquals("1234567AA", P.getPpsn());
        //Too many numbers
        assertEquals("Invalid PPS Number", P2.getPpsn());
        //Too many letters
        assertEquals("Invalid PPS Number", P3.getPpsn());
    }

    @Test
    void BoothIDTest() {
        //Correct
        assertEquals("A2", B.getBoothID());
        //Too many numbers
        assertEquals("Invalid Booth ID", B2.getBoothID());
        //Too many letters
        assertEquals("Invalid Booth ID", B3.getBoothID());
    }

}