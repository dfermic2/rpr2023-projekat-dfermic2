package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        biranjeLoginRegistracija();
    }

    private static void biranjeLoginRegistracija() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite 1 za prijavu, 2 za registraciju: ");

        int opcija = scanner.nextInt();

        if (opcija == 1) {
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
        System.out.println("Prijava");

        System.out.print("Unesite email: ");
        String email = scanner.nextLine();

        System.out.print("Unesite password: ");
        String password = scanner.nextLine();

        try {
            Korisnik korisnik = KorisnikManager.findByEmail(email);
            if (korisnik == null || !korisnik.getPassword().equals(password)) {
                System.out.println("Unijeli ste neispravne podatke!");
                biranjeLoginRegistracija();
            } else {
                System.out.println("Uspješno ste prijavljeni!");
            }
        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registracija() {
        System.out.println("Unijeli ste 2");
    }
}
