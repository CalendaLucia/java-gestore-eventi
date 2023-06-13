package org.learning.java.gestoreEventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        do {
            System.out.print("Inserisci un nuovo evento: ");
            String titolo = scanner.nextLine();

            if (titolo.isEmpty()) {
                System.out.println("Inserisci un titolo valido. Riprova.");
                continue; // Salta all'iterazione successiva del ciclo
            }

            System.out.print("Inserisci una data nel seguente formato dd/MM/yyyy : ");
            String input = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date;
            try {
                date = LocalDate.parse(input, formatter);
                System.out.println("Hai inserito: " + formatter.format(date));

                System.out.println("Quanti posti saranno disponibili per l'evento?");
                int totalePosti = Integer.parseInt(scanner.nextLine());
                Evento evento = new Evento(titolo, date, totalePosti); // Creazione dell'oggetto Evento
                System.out.println("Evento creato: " + evento.toString());
                //chiedere all’utente se e quante prenotazioni vuole fare
                System.out.println("Vuoi prenotare? si/no");
                String scelta = scanner.nextLine();
                switch (scelta) {
                    case "si":
                        System.out.println("Quante prenotazioni vuoi effettuare?");

                        break;
                    case "no":
                        System.out.println("Hai scelto di non prenotare");
                        break;
                    default:
                        System.out.println("Scelta non valida");
                        break;
                }

                stop = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " " + "Riprova"); // Stampa il messaggio dell'eccezione
            }
        } while (!stop);



        scanner.close();
    }
}
