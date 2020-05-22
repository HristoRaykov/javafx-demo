package javafxdemo;

import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class Stock {

    private String symbol;
    private BigDecimal lastClose;

    public Stock(String symbol, BigDecimal lastClose) {
        this.symbol = symbol;
        this.lastClose = lastClose;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getLastClose() {
        return lastClose;
    }

    public void setLastClose(BigDecimal lastClose) {
        this.lastClose = lastClose;
    }
}
