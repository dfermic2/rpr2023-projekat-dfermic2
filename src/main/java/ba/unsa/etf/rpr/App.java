package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.business.RezervacijaManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
                rezervacija(korisnik.getId());
            }
        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registracija() {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Registracija");

        System.out.print("Unesite ime: ");
        String ime = scanner.nextLine();

        System.out.print("Unesite prezime: ");
        String prezime = scanner.nextLine();

        System.out.print("Unesite email: ");
        String email = scanner.nextLine();

        System.out.print("Unesite adresu: ");
        String adresa = scanner.nextLine();

        System.out.print("Unesite password: ");
        String password = scanner.nextLine();

        try {
            Korisnik korisnik = KorisnikManager.findByEmail(email);

            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || adresa.isEmpty() || password.isEmpty()) {
                System.out.println("Nepotpuni podatci!");
                biranjeLoginRegistracija();
            }
            if (korisnik != null) {
                System.out.println("Email adresa vec postoji!");
                biranjeLoginRegistracija();
            }
            if (!Pattern.compile(emailRegex).matcher(email).matches()) {
                System.out.println("Neispravan format email adrese");
                biranjeLoginRegistracija();
            }

            System.out.println("Uspješno ste registrovani!");

        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }

    //Validacija da je pocetak prije kraja i sl.
    private static void rezervacija(int korisnikId) {
        System.out.println("Početak rezervacije");
        LocalDate pocetak = unosDatuma();

        System.out.println("Kraj rezervacije");
        LocalDate kraj = unosDatuma();

        List<Kucica> kucicaList;

        try {
            kucicaList = KucicaManager.getAll();
        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }

        Set<Integer> kuciceId = RezervacijaManager.findBetweenDates(pocetak);
        kuciceId.addAll(RezervacijaManager.findBetweenDates(kraj));
        List<Kucica> kuciceFiltered = kucicaList.stream().filter(kucica -> !kuciceId.contains(kucica.getId())).collect(Collectors.toList());

        System.out.println("Slobodne kućice:");
        for(Kucica kucica : kuciceFiltered) {
            System.out.println(kucica.toString());
        }
        Kucica kucica = unosKucice(kuciceFiltered);
        BigDecimal cijena = kucica.getCijena().multiply(BigDecimal.valueOf(DAYS.between(pocetak, kraj)));

        Rezervacija rezervacija = new Rezervacija(korisnikId, kucica.getId(), pocetak, kraj, cijena);
        try {
            RezervacijaManager.add(rezervacija);
            System.out.println("Uspješno ste rezervisali kućicu!");
        } catch (DolinaSreceException e) {
            System.out.println("Greška pri kreiranju rezervacije");
            throw new RuntimeException(e);
        }
    }

    private static Kucica unosKucice(List<Kucica> kuciceFiltered) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite id kućice koju želite rezervisati: ");
        int kucicaId = scanner.nextInt();
        for (Kucica kucica : kuciceFiltered) {
            if(kucica.getId() == kucicaId)
                return kucica;
        }
        System.out.println("Pogrešan id!");
        unosKucice(kuciceFiltered);
        return null;
    }

    private static LocalDate unosDatuma() {
        System.out.println("Unesite datum u formatu yyyy-MM-dd: ");
        Scanner scanner = new Scanner(System.in);
        String datum = scanner.nextLine();
        LocalDate localDate = returnValidDate(datum);

        if(localDate == null) {
            System.out.println("Pogršan unos!");
            unosDatuma();
        };
        return localDate;
    }

    private static LocalDate returnValidDate(String datum) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;

        try {
            localDate = LocalDate.from(dateTimeFormatter.parse(datum));
            return localDate;
        } catch (Exception e) {
            return null;
        }
    }
}
