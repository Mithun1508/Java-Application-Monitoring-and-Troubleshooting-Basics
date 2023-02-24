package com.acme.dbo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationSystemTest {
    private final Path datafilePath = get("target/clients.data");
    private Controller applicationController;

    @Before
    public void setupApplication() throws IOException {
        applicationController = new Controller(new Service(new ClientRepository(datafilePath)));
    }

    @Before
    public void setupDatastore() throws IOException {
        createFile(datafilePath);
    }

    @After
    public void cleanDatastore() throws IOException {
        delete(datafilePath);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPersistClientWhenSavingIncorrectClientData() throws CreateClientException {
        applicationController.createClient("abc");
    }

    @Test
    public void shouldPersistClientWhenSavingCorrectClientData() throws CreateClientException, IOException {
        applicationController.createClient("1,FirstName,LastName");
        assertThat(readAllLines(datafilePath))
                .containsExactly("1,FirstName,LastName");
    }


    @Test
    public void shouldPersistClientWhenSavingCorrectClientDataAndDataStoreNotEmpty() throws IOException, CreateClientException {
        write(datafilePath, asList("1,FirstName,LastName"));

        applicationController.createClient("2,FirstName2,LastName2");

        assertThat(readAllLines(datafilePath))
            .containsExactly(
                    "1,FirstName,LastName",
                    "2,FirstName2,LastName2"
            );
    }

    @Test
    public void shouldGetClientsWhenDataStoreNotEmpty() throws IOException, ReadClientsException {
        write(datafilePath, asList("1,FirstName,LastName", "2,FirstName2,LastName2"));

        assertThat(applicationController.getAllClients())
                .contains("Clients")
                .contains("=======")
                    .contains("1")
                    .contains("--")
                    .contains("LastName FirstName")

                    .contains("2")
                    .contains("--")
                    .contains("LastName2 FirstName2");
    }
}
