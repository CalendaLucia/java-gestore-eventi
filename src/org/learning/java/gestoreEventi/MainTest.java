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
        boolean stop = false; // Variabile per controllare la condizione di uscita dal ciclo principale


        // Inizio ciclo principale
        do {
            System.out.print("Inserisci un nuovo evento: ");
            String titolo = scanner.nextLine();

            if (titolo.isEmpty()) {
                System.out.println("Inserisci un titolo valido. Riprova.");
                continue; //Ritorna indietro alla prima richiesta
            }
            // Richiesta e parsing della data dell'evento
            System.out.print("Inserisci una data nel seguente formato dd/MM/yyyy : ");
            String input = scanner.nextLine();
          // Creazione di un formatter per il parsing della data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date;
            try {
                date = LocalDate.parse(input, formatter); // Parsing della data nel formato specificato
                System.out.println("Hai inserito: " + formatter.format(date)); // Stampa della data formattata correttamente
            } catch (IllegalArgumentException e) {
                System.out.println("Errore nell'inserimento della data. Riprova!");
                continue; // Torna indietro  in caso di errore di parsing
            }catch (DateTimeParseException e) {
                System.out.println("Errore nell'inserimento della data. Riprova!");
                continue;
            }

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
                System.out.println("Evento creato: " + concerto.toString());
            } catch (NumberFormatException e) {
                System.out.println("Il numero di posti totali deve essere positivo");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("La data non può essere precedente alla data corrente.");
                continue;
            }


                //Inizio Secondo Ciclo per le prenotazioni/disdette
                while (true) {
                    System.out.println("1-Prenota  2-Annulla Prenotazione  3-Esci");
                    String scelta = scanner.nextLine();
                    switch (scelta) {
                        case "1":
                            System.out.print("Quante prenotazioni vuoi effettuare? ");
                            int numeroPrenotati = Integer.parseInt(scanner.nextLine());
                            try {
                                concerto.prenota();
                            } catch (IllegalStateException e) {
                                System.out.println("Mi dispiace, non ci sono più posti disponibili");
                                continue;
                            }
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
                            stop = true;   // Imposto la variabile 'stop' su 'true' per indicare la condizione di uscita dal ciclo principale
                            break;
                        default:
                            System.out.println("Scelta non valida");
                            break;
                    }
                    // Se 'stop' è true, esce dal secondo ciclo
                    if (stop) {
                        break;
                    }
                }  // Fine del secondo ciclo

            stop = true;  // Imposto la variabile 'stop' su 'true' per indicare la condizione di uscita dal primo ciclo
        } while (!stop); // Continua a eseguire il ciclo principale fintanto che 'stop' è false





        scanner.close();
    }
}
