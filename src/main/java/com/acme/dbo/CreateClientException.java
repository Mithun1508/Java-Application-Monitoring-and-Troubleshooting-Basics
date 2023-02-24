package com.acme.dbo;

import java.io.IOException;

public class CreateClientException extends Throwable {
    public CreateClientException(Client newClient, IOException e) {
        super(newClient.toString(), e);
    }
}
