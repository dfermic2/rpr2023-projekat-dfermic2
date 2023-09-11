package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Korisnik;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        biranjeLoginRegistracija();
    }

    private static void biranjeLoginRegistracija() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite 1 za prijavu, 2 za registraciju: ");

        int opcija = scanner.nextInt();

        if(opcija == 1) {
            prijava();
        } else if (opcija == 2) {
            registracija();
        } else {
            System.out.println("Neispravan unos, pokušajte ponovo");
            biranjeLoginRegistracija();
        }
    }

    private static void prijava() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Unesite email: ");
        String email = scanner.nextLine();

        System.out.print("Unesite password: ");
        String password = scanner.nextLine();
    }

    private static void registracija() {
        System.out.println("Unijeli ste 2");
    }
}
