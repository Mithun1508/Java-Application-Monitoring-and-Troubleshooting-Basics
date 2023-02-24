package com.acme.dbo;

public class Client {
    private int id;
    private String firstName;
    private String lastName;

    public Client(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName;
    }
}
