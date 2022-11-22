package com.example.assignment;

import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import com.thoughtworks.xstream.XStream;

public class VaccinationApplication extends Application {
    public static VaccinationApplication.storage str;
    public static class storage {
        public VeryFunLinkedList<VaccinationCentre> vaxxCentre = new VeryFunLinkedList<>();
        public VeryFunLinkedList<Patient> patient = new VeryFunLinkedList<>();
    }


    @Override
    public void start(Stage stage) throws IOException {
        str = new VaccinationApplication.storage();
        FXMLLoader fxmlLoader = new FXMLLoader(VaccinationApplication.class.getResource("vaccination-centre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @SuppressWarnings("unchecked")
    public static void load() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xStream.createObjectInputStream(new FileReader("vaccinationFile.xml"));
        str = (VaccinationApplication.storage) is.readObject();
        is.close();
    }

    public static void save() throws Exception {
        XStream xStream = new XStream((new DomDriver()));
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("vaccinationFile.xml"));
        out.writeObject(str);
        out.close();
    }

    public static void main(String[] args) {
        launch();
    }
}