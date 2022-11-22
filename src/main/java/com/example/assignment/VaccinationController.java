package com.example.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.assignment.VaccinationApplication.str;

public class VaccinationController implements Initializable {
    //Create the JOptionPane Frame for the methods.
    public JFrame frame;

    private int vaxxIndex;

    @FXML
    private DatePicker dobPatient, appointDate;
    @FXML
    private TextField pps, namePatient, eircodeP, phoneNum, emailP, accessP;
    @FXML
    private TextField nameVC, addressVC, eircodeVC, parkingVC;
    @FXML
    private TextField boothID, floorID, accessID;
    @FXML
    private TextField vaxxBatch, vaxxinator;
    @FXML
    private TextField typeSearch, batchSearch;
    @FXML
    private ComboBox<String> boothBox;
    @FXML
    private ComboBox<String> appointVC;
    @FXML
    private ComboBox<String> appointBooth;
    @FXML
    private ComboBox<String> appointPatient;
    @FXML
    private ChoiceBox<String> recordPatient;
    @FXML
    private ChoiceBox<String> vaxxType;
    @FXML
    private ChoiceBox<String> vaxxTime;
    @FXML
    private ListView<Booth> listBooth;
    @FXML
    private ListView<Object> listVC;
    @FXML
    private ListView<Patient> listPatient;
    @FXML
    private ListView<VaccinationAppointment> listAppointment;
    @FXML
    private ListView<Object> listComplete, listPending;
    @FXML
    private ListView<Object> listSearch;

    //When the program is initialised, populate the Vaccination Type and Batch Number combo boxes with information.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaxxType.getItems().addAll("Pfizer", "AstraZeneca", "Moderna");
        vaxxTime.getItems().addAll("9.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00");
    }

    @FXML
    public void addVC(ActionEvent event) {
        String inputParking = parkingVC.getText();
        if (!inputParking.matches("[0-9]*")) { //Looks for any amount of numbers from 0 to 9
            inputParking = "1";
        }
        int parkingNumber = Integer.parseInt(inputParking);
        //Create the Vaccination Centre Object from the TextFields.
        VaccinationCentre v = new VaccinationCentre(nameVC.getText(), addressVC.getText(), eircodeVC.getText(), parkingNumber);
        //Add the Vaccination Centre Object to the Linked List.
        str.vaxxCentre.addElement(v);
        //Call the method below.
        updateListViewVC();
        System.out.println(v);
        for (int i = 0; i < str.vaxxCentre.listLength(); i++) {
            System.out.println(str.vaxxCentre.getObject(i));
        }
        //Clear the combo boxes before populating them with the list of VCs.
        boothBox.getItems().clear();
        appointVC.getItems().clear();
        for (int i = str.vaxxCentre.listLength() - 1; i >= 0; i--) {
            boothBox.getItems().add(str.vaxxCentre.getObject(i).getName());
            appointVC.getItems().add(str.vaxxCentre.getObject(i).getName());
            System.out.println(boothBox.getItems());
        }

    }

    //I only cried a little bit when trying to get the existence of a booth validated.
    @FXML
    public void addBooth(ActionEvent event) {
        //Save the selected vaccination centre from the combo box into a variable
        int indexVC = 0;
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(boothBox.getSelectionModel().getSelectedItem())) {
                //If it is, make that VC equal to a variable.
                VaccinationCentre selectedVc = str.vaxxCentre.getObject(indexVC);
                //An int was needed here, because the list would keep getting longer after adding, and thus the method would be run too many times.
                int boothLength = selectedVc.getBooths().listLength();
                //If the VC's booths list is empty, add the new booth.
                if (selectedVc.getBooths().listLength() == 0) {
                    Booth newBooth = new Booth(boothID.getText(), Integer.parseInt(floorID.getText()), accessID.getText());
                    selectedVc.booths.addElement(newBooth);
                    listBooth.getItems().add(newBooth);
                } else {
                    //If it isn't, run a for loop to check if the booth already exists. If it does, set the boolean variable to true.
                    boolean boothExist = false;
                    for (int x = 0; x < boothLength; x++) {
                        if (boothID.getText().equals(selectedVc.getBooths().getObject(x).getBoothID())) {
                            boothExist = true;
                            JOptionPane.showMessageDialog(frame, "You cannot add an already existing Booth! Please change the details!", "Booth already exists", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    //If it doesn't exist, add the booth to the linked list and the list view.
                    if (!boothExist) {
                        Booth newBooth = new Booth(boothID.getText(), Integer.parseInt(floorID.getText()), accessID.getText());
                        selectedVc.booths.addElement(newBooth);
                        listBooth.getItems().add(newBooth);
                    }
                }
            }
            indexVC++;
        }
    }

    //Create a new Patient object and add it to the Linked List.
    @FXML
    public void addPatient(ActionEvent event) {
        Patient p = new Patient(pps.getText(), namePatient.getText(), dobPatient.getValue(), eircodeP.getText(), phoneNum.getText(), emailP.getText(), accessP.getText());
        str.patient.addElement(p);
        //Clear the combo box in the Appointments menu and populate it again.
        appointPatient.getItems().clear();
        for (int i = str.patient.listLength() - 1; i >= 0; i--) {
            appointPatient.getItems().add(str.patient.getObject(i).getPpsn());
            System.out.println(appointPatient.getItems());

        }
        //Console validation to keep track of what's added.
        for (int i = 0; i < str.patient.listLength(); i++) {
            System.out.println(str.patient.getObject(i));
        }
        updateListViewPatient();
    }


    //Looking at this hurts my head, but it works.
    //Loop through the VCs, the selected VCs booths, the selected booth's patients,
    //Create the new appointment and add the appointment to the booth's Appointment list, and the appointment list view.
    @FXML
    public void addAppointment(ActionEvent event) {
        for (int i = 0; i < str.vaxxCentre.listLength(); i++) {
            if (str.vaxxCentre.getObject(i).getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                for (int x = 0; x < str.vaxxCentre.getObject(i).getBooths().listLength(); x++) {
                    if (str.vaxxCentre.getObject(i).getBooths().getObject(x).getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                        for (Patient tempPatient : str.patient) {
                            if (tempPatient.getPpsn().equals(appointPatient.getSelectionModel().getSelectedItem())) {
                                VaccinationAppointment appointment = new VaccinationAppointment(appointDate.getValue(), vaxxTime.getValue(), vaxxType.getValue(), vaxxBatch.getText(), vaxxinator.getText(), appointPatient.getValue());
                                str.vaxxCentre.getObject(i).getBooths().getObject(x).getAppointment().addElement(appointment);
                                listAppointment.getItems().clear();
                                for (int y = 0; y < str.vaxxCentre.getObject(i).getBooths().getObject(x).getAppointment().listLength(); y++) {
                                    listAppointment.getItems().add(str.vaxxCentre.getObject(i).getBooths().getObject(x).getAppointment().getObject(y));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void completeAppointment(ActionEvent event) {
        int index = listAppointment.getSelectionModel().getSelectedIndex();
        int indexVC = 0;
        int indexB = 0;
        int indexP = 0;
        //Go through each VC and compare them to the selected VC in the combo box.
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        //Go through each Booth inside the VC and compare them to the selected Booth in the combo box.
        for (Booth temp : str.vaxxCentre.getObject(indexVC).getBooths()) {
            if (temp.getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexB++;
        }
        //Go through each Patient and compare them to the selected Patient in the combo box.
        for (Patient temp : str.patient) {
            if (temp.getPpsn().equals(appointPatient.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexP++;
        }
        //Save a temporary Appointment of what the user selected in the list view.
        VaccinationAppointment temp = listAppointment.getSelectionModel().getSelectedItem();
        //Create a Record based on the information of the temporary Appointment.
        VaccinationRecord tempR = new VaccinationRecord(temp.getVaxxDate(), temp.getVaxxTime(), temp.getVaxxType(), temp.getVaxxBatch(), temp.getVaxxDetails(), str.patient.getObject(indexP).getPpsn());
        //Remove the selected Appointment from the Linked List and the list view.
        str.vaxxCentre.getObject(indexVC).getBooths().getObject(indexB).getAppointment().removeElement(index);
        listAppointment.getItems().remove(index);
        //Add the Record to the Records linked list of the selected Patient.
        str.patient.getObject(indexP).getRecords().addElement(tempR);
    }

    //Clear the list view of the complete appointments and populate it after getting the correct Record.
    @FXML
    public void showCompleteAppointment(ActionEvent event) {
        listComplete.getItems().clear();
        for (int i = 0; i < str.patient.listLength(); i++) {
            for (VaccinationRecord tempRec : str.patient.getObject(i).getRecords()) {
                if (tempRec.getPatientPPSN().equals(recordPatient.getSelectionModel().getSelectedItem())) {
                    listComplete.getItems().add(tempRec);
                }
            }
        }
    }

    //Clear the list view of the pending appointments and populate it after getting the correct VC, VC's booth and booth's appointment list.
    @FXML
    public void showPendingAppointments(ActionEvent event) {
        listPending.getItems().clear();
        for (VaccinationCentre tempVC : str.vaxxCentre) {
            for (Booth tempBooth : tempVC.getBooths()) {
                for (VaccinationAppointment tempApp : tempBooth.getAppointment()) {
                    if (tempApp.getPatientPPSN().equals(recordPatient.getSelectionModel().getSelectedItem())) {
                        listPending.getItems().add(tempApp);
                    }
                }
            }
        }
    }

    //Search the system by Vaccination Type.
    @FXML
    public void searchType(ActionEvent event) {
        int indexVC = 0;
        int indexB = 0;
        int indexP = 0;
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        for (Booth temp : str.vaxxCentre.getObject(indexVC).getBooths()) {
            if (temp.getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexB++;
        }
        for (Patient temp : str.patient) {
            if (temp.getPpsn().equals(appointPatient.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexP++;
        }
        //After getting the VC, VC's Booth and Patient, go through the appointments of the select Booth, compare the vaccination type to what the user inputted to the temp,
        //and if it's the same (ignoring Upper and Lower Case), add it.
        listSearch.getItems().clear();
        for (VaccinationAppointment temp : str.vaxxCentre.getObject(indexVC).getBooths().getObject(indexB).getAppointment()) {
            if (temp.getVaxxType().equalsIgnoreCase(typeSearch.getText())) {
                listSearch.getItems().add("Pending Appointment: " + temp);
            }
        }
        //Same as above, but compare what the user inputted to the Records list.
        for (VaccinationRecord tempR : str.patient.getObject(indexP).getRecords()) {
            if (tempR.getVaxxType().equalsIgnoreCase(typeSearch.getText())) {
                listSearch.getItems().add("Completed Vaccination: " + tempR);
            }
        }
    }

    @FXML
    public void searchBatch(ActionEvent event){
        int indexVC = 0;
        int indexB = 0;
        int indexP = 0;
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        for (Booth temp : str.vaxxCentre.getObject(indexVC).getBooths()) {
            if (temp.getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexB++;
        }
        for (Patient temp : str.patient) {
            if (temp.getPpsn().equals(appointPatient.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexP++;
        }
        //Same as above, but compare the batch number.
        listSearch.getItems().clear();
        for (VaccinationAppointment temp : str.vaxxCentre.getObject(indexVC).getBooths().getObject(indexB).getAppointment()) {
            if (temp.getVaxxBatch().equalsIgnoreCase(batchSearch.getText())) {
                listSearch.getItems().add("Pending Appointment: " + temp);
            }
        }
        for (VaccinationRecord tempR : str.patient.getObject(indexP).getRecords()) {
            if (tempR.getVaxxBatch().equalsIgnoreCase(batchSearch.getText())) {
                listSearch.getItems().add("Completed Vaccination: " + tempR);
            }
        }
    }

    @FXML
    public void searchBoth(ActionEvent event){
        int indexVC = 0;
        int indexB = 0;
        int indexP = 0;
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        for (Booth temp : str.vaxxCentre.getObject(indexVC).getBooths()) {
            if (temp.getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexB++;
        }
        for (Patient temp : str.patient) {
            if (temp.getPpsn().equals(appointPatient.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexP++;
        }
        //Same as above, but compare both Batch Number and Type at the same time.
        listSearch.getItems().clear();
        for (VaccinationAppointment temp : str.vaxxCentre.getObject(indexVC).getBooths().getObject(indexB).getAppointment()) {
            if (temp.getVaxxType().equalsIgnoreCase(typeSearch.getText()) && temp.getVaxxBatch().equalsIgnoreCase(batchSearch.getText())) {
                listSearch.getItems().add("Pending Appointment: " + temp);
            }
        }
        for (VaccinationRecord tempR : str.patient.getObject(indexP).getRecords()) {
            if (tempR.getVaxxType().equalsIgnoreCase(typeSearch.getText()) && tempR.getVaxxBatch().equalsIgnoreCase(batchSearch.getText())) {
                listSearch.getItems().add("Completed Vaccination: " + tempR);
            }
        }
    }

    //When you click the Combo Box, the list of vaccination centres need to be reprinted, so that a VC isn't added twice.
    @FXML
    public void comboBoxVCBooth(ActionEvent event) {
        String vc = boothBox.getSelectionModel().getSelectedItem();
        for (int i = 0; i < str.vaxxCentre.listLength(); i++) {
            if (str.vaxxCentre.getObject(i).getName().matches(vc)) {
                updateListViewBooth(vc);
            }
        }
    }

    //Populates the Appointment's Booth combo box.
    @FXML
    public void comboBoxAppointVC(ActionEvent event) {
        appointBooth.getItems().clear();
        String vc = appointVC.getSelectionModel().getSelectedItem();
        for (int i = 0; i < str.vaxxCentre.listLength(); i++) {
            if (str.vaxxCentre.getObject(i).getName().matches(vc)) {
                VaccinationCentre selected = str.vaxxCentre.getObject(i);
                vaxxIndex = i;
                for (int x = 0; x < selected.getBooths().listLength(); x++) {
                    appointBooth.getItems().addAll(selected.booths.getObject(x).getBoothID());
                    updateListViewAppointment(vc);
                }
            }
        }
    }

    //Populates the List view of the appointment
    @FXML
    public void comboBoxAppointBooth(ActionEvent event) {
        String b = appointBooth.getSelectionModel().getSelectedItem();
        for (int x = 0; x < str.vaxxCentre.getObject(vaxxIndex).getBooths().listLength(); x++) {
            if (str.vaxxCentre.getObject(vaxxIndex).getBooths().getObject(x).getBoothID().matches(b)) {
                listAppointment.getItems().clear();
                if (str.vaxxCentre.getObject(vaxxIndex).getBooths().getObject(x).getAppointment().listLength() != 0) {
                    for (int i = 0; i < str.vaxxCentre.getObject(vaxxIndex).getBooths().getObject(x).getAppointment().listLength(); i++) {
                        listAppointment.getItems().add(str.vaxxCentre.getObject(vaxxIndex).getBooths().getObject(x).getAppointment().getObject(i));
                    }
                }
            }
        }
    }

    //Populates the choice box of Patient Records.
    @FXML
    public void choiceBoxRecordPatient(MouseEvent event) {
        recordPatient.getItems().clear();
        for (Patient temp : str.patient) {
            recordPatient.getItems().add(temp.getPpsn());
        }
    }

    @FXML
    public void deleteVC(ActionEvent event) {
        int index = listVC.getSelectionModel().getSelectedIndex();
        str.vaxxCentre.removeElement(index);
        //Remove the selected item from the list view.
        listVC.getItems().remove(index);
        //Remove the selected item from the choice box in the "booth" tab.
        boothBox.getItems().remove(index);
    }

    @FXML
    public void deleteBooth(ActionEvent event) {
        int index = listBooth.getSelectionModel().getSelectedIndex();
        int indexVC = 0;
        //Run a for-each loop looking through the VC linked list, and if we find the VC that was selected in the
        //combo box, break the loop, otherwise increment indexVC.
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(boothBox.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        //Using indexVC, get the specified VC, get its list of booths and remove the selected element.
        str.vaxxCentre.getObject(indexVC).getBooths().removeElement(index);
        //Remove the selected element from list view.
        listBooth.getItems().remove(index);
    }

    @FXML
    public void deletePatient(ActionEvent event) {
        int index = listPatient.getSelectionModel().getSelectedIndex();
        str.patient.removeElement(index);
        listPatient.getItems().remove(index);
    }

    @FXML
    public void deleteAppointment(ActionEvent event) {
        int index = listAppointment.getSelectionModel().getSelectedIndex();
        int indexVC = 0;
        int indexB = 0;
        for (VaccinationCentre temp : str.vaxxCentre) {
            if (temp.getName().equals(appointVC.getSelectionModel().getSelectedItem())) {
                break;
            }
            indexVC++;
        }
        for (Booth temp : str.vaxxCentre.getObject(indexVC).getBooths()) {
            if (temp.getBoothID().equals((appointBooth.getSelectionModel().getSelectedItem()))) {
                break;
            }
            indexB++;
        }
        str.vaxxCentre.getObject(indexVC).getBooths().getObject(indexB).getAppointment().removeElement(index);
        listAppointment.getItems().remove(index);
    }

    //Clear the VC list view every time this is called.
    //Make a reverse for loop to align the indexes of the list view and the linked list.
    public void updateListViewVC() {
        listVC.getItems().clear();
        for (int i = str.vaxxCentre.listLength() - 1; i >= 0; i--) {
            listVC.getItems().addAll(str.vaxxCentre.getObject(i));
        }
    }

    //Clear the booth list view every time.
    //Make sure a VC exists. If it does, go through the VC linked list, and check if the
    //selected VC in the combo box exists in the linked list.
    public void updateListViewBooth(String vc) {
        listBooth.getItems().clear();
        if (vc != null) {
            int index = 0;
            for (VaccinationCentre temp : str.vaxxCentre) {
                if (temp.getName().equals(vc)) {
                    break;
                }
                index++;
            }
            //Go through the booths of the specified VC from above, then add them all back into the list view.
            for (Booth temp : str.vaxxCentre.getObject(index).getBooths()) {
                listBooth.getItems().add(temp);
            }
        }
    }

    //I only spent an hour on this method not working, only to realise I was calling the VC linked list.
    public void updateListViewPatient() {
        listPatient.getItems().clear();
        for (int i = str.patient.listLength() - 1; i >= 0; i--) {
            listPatient.getItems().addAll(str.patient.getObject(i));
        }
    }

    //Clear the Appointment list view.
    //Go through VCs, Booths and Appointments, and add all the appointments back in.
    public void updateListViewAppointment(String ap) {
        listAppointment.getItems().clear();
        if (ap != null) {
            for (VaccinationCentre temp : str.vaxxCentre) {
                if (temp.getName().equals(ap)) {
                    for (Booth tempBooth : temp.getBooths()) {
                        if (tempBooth.getBoothID().equals(appointBooth.getSelectionModel().getSelectedItem())) {
                            for (VaccinationAppointment tempAppointment : tempBooth.getAppointment()) {
                                listAppointment.getItems().add(tempAppointment);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    //Clear all the linked lists and list views. Also give the user anxiety as a bonus feature.
    @FXML
    public void nukeAll() {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to nuke the program? (THIS WILL DELETE EVERYTHING!)", "Nuke Confirmation", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            str.vaxxCentre.clearList();
            str.patient.clearList();
            listVC.getItems().clear();
            listBooth.getItems().clear();
            listPatient.getItems().clear();
            listAppointment.getItems().clear();
            listComplete.getItems().clear();
            listPending.getItems().clear();
            listSearch.getItems().clear();
            vaxxType.getSelectionModel().clearSelection();
            vaxxTime.getSelectionModel().clearSelection();
            JOptionPane.showMessageDialog(null, "Everything is now destroyed.");
        } else {
            JOptionPane.showMessageDialog(null, "Nothing was nuked, you are spared for now.");
        }
    }

    //The save and load methods work, but the load method doesn't show the newly added information on the GUI.
    //The information is added from the load method, and when you add new information, the list views get updated and the loaded information is present.
    //The fix would be to call each List View updating method in the load method, but because they rely on information inputted beforehand, I can't call them down here.
    //This would require a lot of refactoring which I didn't want to do.
    public void save() throws Exception {
        VaccinationApplication.save();
    }

    public void load() throws Exception {
        VaccinationApplication.load();
    }
}