package com.acme.dbo;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.System.lineSeparator;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;

public class Controller {
    private static final Pattern clientDataPattern = compile("^(\\d+),(\\w+),(\\w+)$");
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void createClient(String clientData) throws CreateClientException {
        Matcher clientDataParsed = clientDataPattern.matcher(clientData);
        if (clientDataParsed.find()) {
            final Client newClient = new Client(
                    parseInt(clientDataParsed.group(1)),
                    clientDataParsed.group(2),
                    clientDataParsed.group(3)
            );

            service.createClient(newClient);

        } else {
            throw new IllegalArgumentException("Illegal client data format for '" + clientData + "'");
        }
    }

    public String getAllClients() throws ReadClientsException {
        return "Clients" + lineSeparator() + "=======" + lineSeparator()
                + service.getAllClients().stream()
                    .map(client ->
                        client.getId() + lineSeparator() + "--" + lineSeparator()
                        + client.getLastName() + " " + client.getFirstName())
                    .collect(joining(lineSeparator()));
    }
}
