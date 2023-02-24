package com.acme.dbo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.Paths.get;

public class Presentation {
    private static final Path datafilePath = get("target/clients-qa.data");

    public static void main(String[] args) throws ReadClientsException, CreateClientException, IOException {
        if (args.length > 0) {
            System.out.println("Application called with program arguments:");
            for (String programArgument: args) {
                System.out.println(programArgument);
            }
        }

        System.out.println("Application called with custom application properties:");
        System.getProperties().keySet().stream()
                .map(Object::toString)
                .filter(key -> key.contains("app"))
                .forEach(System.out::println);


        final Controller applicationController = new Controller(new Service(new ClientRepository(datafilePath)));

        final Scanner console = new Scanner(System.in);
        while (true) {
            String readLine = console.nextLine();
            if (readLine.equals("quit")) System.exit(0);
            if (readLine.equals("read clients")) System.out.println(applicationController.getAllClients());
            if (readLine.contains("new client")) {
                final String clientData = readLine.replace("new client ", "");
                applicationController.createClient(clientData);
            }
        }
    }
}
