package javafxdemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Stage stage;

    @FXML
    private TabPane tabPane;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadSymbols() throws IOException {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);

        List<String> lines = getFileLines(file);

        List<String> symbols = getIBSymbols(lines);

        List<Stock> stocks = new ArrayList<>();
        for (var symbol : symbols) {
            Stock stock = new Stock(symbol, BigDecimal.ONE);
            stocks.add(stock);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tab.fxml"));
        Tab tab = loader.load();
        var n = file.getName();
        tab.setText(n);
        VBox tabContent = (VBox) tab.getContent();

        StackPane info = (StackPane)tabContent.getChildren().get(0);
        Label ch1 = (Label) info.getChildren().get(0);
        Label ch2 = (Label) info.getChildren().get(1);

        Text time = (Text)ch1.getGraphic();
        Text count = (Text)ch2.getGraphic();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        time.setText(dtf.format(now));
        count.setText(String.valueOf(stocks.size()));

        TableView<Stock> table = (TableView<Stock>) tabContent.getChildren().get(1);
        TableColumn<Stock, String> symbolCol = (TableColumn<Stock, String>) table.getColumns().get(0);
        TableColumn<Stock, BigDecimal> closeCol = (TableColumn<Stock, BigDecimal>) table.getColumns().get(1);

        symbolCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("symbol"));
        closeCol.setCellValueFactory(new PropertyValueFactory<Stock, BigDecimal>("lastClose"));

        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.getItems().setAll(stocks);

        tabPane.getTabs().add(tab);

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
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }


}
