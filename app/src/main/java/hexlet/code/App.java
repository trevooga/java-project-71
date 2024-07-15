package hexlet.code;
import hexlet.code.Differ;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import  picocli.CommandLine.Parameters;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    @Override
    public void run() {
        System.out.println("Hello world!");
    }
}
