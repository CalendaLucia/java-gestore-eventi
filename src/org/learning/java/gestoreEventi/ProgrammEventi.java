package org.learning.java.gestoreEventi;

import java.util.ArrayList;
import java.util.List;

public class ProgrammEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    //metodi

    public void aggiungiEvento(Evento evento) {
       eventi.add(evento);
    }

   
}
