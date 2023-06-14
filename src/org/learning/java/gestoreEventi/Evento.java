package org.learning.java.gestoreEventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Evento {
    private String titolo;
    private LocalDate date;
    private int totalePosti;
    private int postiPrenotati;

    public Evento (String titolo, LocalDate date, int totalePosti)  {

        validazioneTitolo(titolo);
        validazioneData(date);

        if (totalePosti <= 0  ) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo");
        }
        this.titolo = titolo;
        this.date = date;
        this.totalePosti = totalePosti;
        this.postiPrenotati = 0;
    }

    //METODI
public void prenota () {
    validazioneData(date);
    if (postiPrenotati >= totalePosti) {
        throw new IllegalStateException("Non ci sono più posti disponibili");
    }

     postiPrenotati++;
}

public void disdici () {
    validazioneData(date);

    if (postiPrenotati <= 0) {
        throw new IllegalStateException("Non ci sono prenotazioni");
    }

    postiPrenotati--;
}

//controlla se l evento è gia passato
public boolean isEventoPassato() {
    return date.isBefore(LocalDate.now());
}


//METODO PER OTTENERE NUMERO DI POSTI DISPONIBILI
public int getPostiDisponibili() {
    return totalePosti - postiPrenotati;
}

    @Override
public String toString() {
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormattata = date.format(formatter);
        return "\nData: " + dataFormattata + "\nNome evento: " + getTitolo() + "\nPosti disponibili: " + getPostiDisponibili();
}

//creare metodo per lancio eccezioni in comune
    private void validazioneTitolo(String titolo) throws IllegalArgumentException {
        if (titolo == null || titolo.isEmpty()) {
            throw new IllegalArgumentException("Titolo non valido");
        }
    }

    private void validazioneData(LocalDate date) {
        if ( date == null || date.isBefore(LocalDate.now()) ) {
            throw new IllegalArgumentException("La data non può essere precedente alla data corrente.");
        }
    }

//GETTER E SETTERS

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        validazioneTitolo(titolo);
        this.titolo = titolo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        validazioneData(date);
        this.date = date;
    }

    public int getTotalePosti() {
        return totalePosti;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
}
