package sample;

import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import org.w3c.dom.Text;

public class Controller {

    int len;
    Random r = new Random();
    String str = new String();
    File file = new File("/Users/gaborlant/Desktop/csv.txt");
    int cnt = 0;
    int correct = 0;
    double perc = 0.0;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label text;

    @FXML
    private Label score;

    @FXML
    private Button igaz, hamis, next;

    public Controller() throws FileNotFoundException {
    }

    @FXML
    private void igazAction(ActionEvent actionEvent) {
        cnt++;
        if( str.substring(str.length() - 1).equals("I")) {
            text.setStyle("-fx-background-color: green;");
            correct++;
        }
        if( str.substring(str.length() - 1).equals("H")) text.setStyle("-fx-background-color: red;");

        perc =(double) correct/ (double) cnt * 100;
        score.setText("" + cnt + "/" + correct + " " + perc + "%");

    }

    @FXML
    private void hamisAction(ActionEvent actionEvent) {
        cnt++;
        if( str.substring(str.length() - 1).equals("H")) {
            text.setStyle("-fx-background-color: green;");
            correct++;
        }
        if( str.substring(str.length() - 1).equals("I")) text.setStyle("-fx-background-color: red;");

        perc =(double) correct/ (double) cnt * 100;
        score.setText("" + cnt + "/" + correct + " " + String.format("%.2f", perc) + "%");

    }

    @FXML
    private void nextAction(ActionEvent actionEvent) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF16"));

        int pos = r.nextInt(len);

        for(int i = 0; i < pos; i++){
            str = in.readLine();
        }
        in.close();
        text.setStyle("-fx-background-color: white;");
        text.setText(str.substring(0, str.length()-1 ));


        perc =(double) correct/ (double) cnt * 100;

        score.setText("" + cnt + "/" + correct + " " + String.format("%.2f", perc) + "%");
    }


    @FXML
    void initialize() throws IOException {
        text.setWrapText(true);
        text.setMaxWidth(600);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF16"));

        len = 0;

        while ((in.readLine()) != null) {
            len++;
        }
        in.close();

        in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF16"));

        int pos = r.nextInt(len);

        for(int i = 0; i < pos; i++){
            str = in.readLine();
        }
        in.close();
        text.setText(str.substring(0, str.length()-1 ));
        perc =(double) correct/ (double) cnt * 100;
        score.setText("" + cnt + "/" + correct + " " + String.format("%.2f", perc) + "%");

    }

    @FXML
    private void reset(ActionEvent actionEvent) {
        cnt = 0;
        correct = 0;
        perc = 0;
        score.setText("" + cnt + "/" + correct + " " + String.format("%.2f", perc) + "%");
    }
}
