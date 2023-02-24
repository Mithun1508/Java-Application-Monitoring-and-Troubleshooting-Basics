package com.acme.dbo;

import java.io.IOException;
import java.util.Collection;

public class Service {
    private ClientRepository clients;

    public Service(ClientRepository clients) {
        this.clients = clients;
    }

    public void createClient(Client newClient) throws CreateClientException {
        try {
            clients.save(newClient);
        } catch (IOException e) {
            throw new CreateClientException(newClient, e);
        }
    }

    public Collection<Client> getAllClients() throws ReadClientsException {
        try {
            return clients.getAllClients();
        } catch (IOException e) {
            throw new ReadClientsException(e);
        }
    }
}
