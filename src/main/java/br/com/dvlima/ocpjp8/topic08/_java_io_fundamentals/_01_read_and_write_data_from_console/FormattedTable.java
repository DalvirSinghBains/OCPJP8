package br.com.dvlima.ocpjp8.topic08._java_io_fundamentals._01_read_and_write_data_from_console;

import java.io.Console;

public class FormattedTable {

    void line(Console console) {
        console.printf("------------------------------------------------------------\n");
    }

    void printHeader(Console console) {
        console.printf("%-15s \t %s \t %s \t %s \n", "Player", "Matches", "Goals",
                "Goals per match");
    }

    void printRow(Console console, String player, int matches, int goals) {
        console.printf("%-15s \t %5d \t\t %d \t\t %.1f \n", player, matches, goals,
                ((float) goals / (float) matches));
    }
}
