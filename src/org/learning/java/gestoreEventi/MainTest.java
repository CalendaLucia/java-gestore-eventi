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

                // Ciclo per le prenotazioni/disdette
                while (true) {
                    System.out.println("1-Prenota  2-Annulla Prenotazione  3-Esci");
                    String scelta = scanner.nextLine();
                    switch (scelta) {
                        case "1":
                            System.out.print("Quante prenotazioni vuoi effettuare? ");
                            int numeroPrenotati = Integer.parseInt(scanner.nextLine());
                            evento.prenota(numeroPrenotati);
                            System.out.println("Hai prenotato per " + numeroPrenotati + " persone" + "\nPosti disponibili: " + evento.getPostiDisponibili());
                            break;
                        case "2":
                            System.out.println("Hai scelto di disdire");
                            System.out.print("Quanti posti vuoi disdire? ");
                            int numeroDisdette = Integer.parseInt(scanner.nextLine());
                            try {
                                evento.disdici(numeroDisdette);
                                System.out.print("Hai disdetto per " + numeroDisdette + " persone" + "\nPosti disponibili: " + evento.getPostiDisponibili() + "\n");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            System.out.println("Grazie e alla prossima");
                            stop = true; // Imposta la condizione di uscita dal ciclo principale
                            break;
                        default:
                            System.out.println("Scelta non valida");
                            break;
                    }

                    if (stop) {
                        break; // Esce dal ciclo delle prenotazioni/disdette se stop è true
                    }
                }


            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " " + "Riprova"); // Stampa il messaggio dell'eccezione
            }
            stop = true;
        } while (!stop);



        scanner.close();
    }
}
