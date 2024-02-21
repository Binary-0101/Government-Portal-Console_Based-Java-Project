package main;

import main.model.DBConnection;
import main.views.Main;

public class App {
    public static void main(String[] args) throws Exception {
        DBConnection.connect();
        Main.landingPage();
    }
}

