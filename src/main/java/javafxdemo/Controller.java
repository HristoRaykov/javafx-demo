package javafxdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadSymbols() throws IOException {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);

        List<String> lines = getFileLines(file);

        List<String> symbols = getIBSymbols(lines);



        System.out.println();
    }

    private List<String> getIBSymbols(List<String> lines) {
        List<String> symbols = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String symbol = line.split(",")[1];
            symbols.add(symbol);
        }

        return symbols;
    }

    private List<String> getFileLines(File file) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null){
            lines.add(line);
        }

        return lines;
    }


}
