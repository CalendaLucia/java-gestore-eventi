package org.learning.java.gestoreEventi;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Concerto extends Evento{
    private LocalTime oraInizioConcerto;
    private BigDecimal prezzo;
    public Concerto(String titolo, LocalDate date, int totalePosti, LocalTime oraInizioConcerto, BigDecimal prezzo) {
        super(titolo, date, totalePosti);

        // Verifica se l'ora di inizio del concerto è valida
        if (oraInizioConcerto == null) {
            throw new DateTimeParseException("L'ora di inizio del concerto non può essere nulla.", null, -1);
        }
        // Verifica se il prezzo è valido
        if (prezzo == null || prezzo.compareTo(BigDecimal.ZERO) < 0) {
            throw new InputMismatchException("Il prezzo del concerto deve essere un valore positivo.");
        }
        this.oraInizioConcerto = oraInizioConcerto;
        this.prezzo = prezzo;
    }

    //metodi
    public String formattaOra(LocalTime oraInizioConcerto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return oraInizioConcerto.format(formatter);
    }

    public String formattaPrezzo(BigDecimal prezzo) {
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
                "\nL'evento avrà inizio alle ore " + formattaOra(oraInizioConcerto) + "\n" +
                "Prezzo del biglietto: " + formattaPrezzo(prezzo);
    }
}
