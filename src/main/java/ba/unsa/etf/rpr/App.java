package ba.unsa.etf.rpr;

import org.apache.commons.cli.*;

public class App {
    private static final Option LOGIN = new Option("l", "login", false, "Logujte se u aplikaciju");
    private static final Option REGISTER = new Option("r", "register", false, "Registrujte se u aplikaciju");


    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();

        Options options = new Options();
        options.addOption(LOGIN);
        options.addOption(REGISTER);

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption(LOGIN.getLongOpt())) {

            } else if (commandLine.hasOption(REGISTER.getLongOpt())) {

            } else {

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
