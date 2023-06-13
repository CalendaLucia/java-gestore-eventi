package org.learning.java.gestoreEventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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
            date = LocalDate.parse(input, formatter);
            System.out.println("Hai inserito: " + formatter.format(date));

            System.out.print("Inserisci l'ora di inizio dell'evento nel seguente formato: ora:minuti  ");
            LocalTime oraInizioConcerto = null;

            try {
                oraInizioConcerto = LocalTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Errore nell'inserimento dell'ora di inizio. Riprova! ");
                continue;
            }


            System.out.print("Inserisci il costo del biglietto nel seguente formato ##,##€ : ");
            BigDecimal prezzo = null;
            try {
                prezzo = scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Inserisci il formato del prezzo correttamente");
                continue;
            }

            System.out.print("Quanti posti saranno disponibili per l'evento?");
            int totalePosti = scanner.nextInt();
            scanner.nextLine();


            Concerto concerto = null; // Creazione dell'oggetto Evento
            try {
                concerto = new Concerto(titolo, date, totalePosti, oraInizioConcerto, prezzo);
            } catch (NumberFormatException e) {
                System.out.println("Il numero di posti totali deve essere positivo");
            }
            System.out.println("Evento creato: " + concerto.toString());

                // Ciclo per le prenotazioni/disdette
                while (true) {
                    System.out.println("1-Prenota  2-Annulla Prenotazione  3-Esci");
                    String scelta = scanner.nextLine();
                    switch (scelta) {
                        case "1":
                            System.out.print("Quante prenotazioni vuoi effettuare? ");
                            int numeroPrenotati = Integer.parseInt(scanner.nextLine());
                            concerto.prenota();
                            System.out.println("Hai prenotato per " + numeroPrenotati + " persone" + "\nPosti disponibili: " + concerto.getPostiDisponibili());
                            break;
                        case "2":
                            System.out.println("Hai scelto di disdire");
                            System.out.print("Quanti posti vuoi disdire? ");
                            int numeroDisdette = Integer.parseInt(scanner.nextLine());
                            try {
                                concerto.disdici();
                                System.out.print("Hai disdetto per " + numeroDisdette + " persone" + "\nPosti disponibili: " + concerto.getPostiDisponibili() + "\n");
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



            stop = true;
        } while (!stop);



        scanner.close();
    }
}
