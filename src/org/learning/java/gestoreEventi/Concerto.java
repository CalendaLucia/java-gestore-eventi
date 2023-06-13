package org.learning.java.gestoreEventi;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    private LocalTime oraInizioConcerto;
    private BigDecimal prezzo;
    public Concerto(String titolo, LocalDate date, int totalePosti, LocalTime oraInizioConcerto, BigDecimal prezzo) {
        super(titolo, date, totalePosti);
        this.oraInizioConcerto = oraInizioConcerto;
        this.prezzo = prezzo;
    }

    //metodi
    public String formattaOra() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return oraInizioConcerto.format(formatter);
    }

    public String formattaPrezzo() {
        DecimalFormat decimalFormat = new DecimalFormat("##,##€");
        return decimalFormat.format(prezzo);
    }

    //getter e setter
    public LocalTime getOraInizioConcerto() {
        return oraInizioConcerto;
    }

    public void setOraInizioConcerto(LocalTime oraInizioConcerto) {
        this.oraInizioConcerto = oraInizioConcerto;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "L'evento avrà inizio alle ore " + getOraInizioConcerto() +"\n" + "Prezzo biglietto: "+ getPrezzo();
    }
}
