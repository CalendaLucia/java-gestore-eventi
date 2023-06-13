package org.learning.java.gestoreEventi;

import java.time.LocalDate;

public class Evento {
    private String titolo;
    private LocalDate date;
    private int totalePosti;
    private int postiPrenotati = 0;

    public Evento (String titolo, LocalDate date, int totalePosti)  {

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data è già passata");
        }
        if (totalePosti <= 0  ) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo");
        }
        this.titolo = titolo;
        this.date = date;
        this.totalePosti = totalePosti;
    }

    //METODI
public void prenota () {
    if (date.isBefore(LocalDate.now())) {
        throw new IllegalStateException("L'evento è già passato");
    }

    if (postiPrenotati >= totalePosti) {
        throw new IllegalStateException("Non ci sono più posti disponibili");
    }

    postiPrenotati++;
}

public void disdici() {
    if (date.isBefore(LocalDate.now())) {
        throw new IllegalStateException("L'evento è già passato");
    }

    if (postiPrenotati <= 0) {
        throw new IllegalStateException("Non ci sono prenotazioni");
    }

    postiPrenotati--;
}

//controlla se l evento è gia passato
private boolean isEventoPassato() {
    return date.isBefore(LocalDate.now());
}

//controlla se ci sono posti disponibili
private boolean isPostiDisponibili() {
    return postiPrenotati < totalePosti;
}

//controlla se ci sono prenotazioni
private boolean hasPrenotazioni() {
    return postiPrenotati > 0;
}

//METODO PER OTTENERE NUMERO DI POSTI DISPONIBILI
public int getPostiDisponibili() {
    return totalePosti - postiPrenotati;
}

    @Override
public String toString() {
    return date.toString() + " - " + titolo;
}

//GETTER E SETTERS

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data è già passata");
        }
        this.date = date;
    }

    public int getTotalePosti() {
        return totalePosti;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
}
