package com.acme.dbo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.*;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;

public class ClientRepository {
    private static final Pattern clientStorePattern = compile("^(\\d+),(\\w+),(\\w+)$");
    private Path datafilePath;

    public ClientRepository(Path datafilePath) throws IOException {
        if (!exists(datafilePath)) createFile(datafilePath);
        this.datafilePath = datafilePath;
    }

    public void save(Client newClient) throws IOException {
        write(datafilePath, asList(newClient.toString()), StandardOpenOption.APPEND);
    }

    public Collection<Client> getAllClients() throws IOException {
        return lines(datafilePath)
                .map(clientStorePattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> new Client(parseInt(matcher.group(1)), matcher.group(2), matcher.group(3)))
                .collect(Collectors.toList());
    }
}
