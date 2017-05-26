package no.westerdals.odeand.hotellapplication;
// Created by Andreas Ødegaard on 22.05.2017.

import java.io.Serializable;

public class Guest implements Serializable {

    private String name, email;
    private long id, phoneNumber;
    private int roomNumber;


    public Guest(String name, String email, long id, long phoneNumber, int roomNumber) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
    }

    public Guest() {
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nPhone number: " + phoneNumber +
                "\nRoom number: " + roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (id != guest.id) return false;
        if (phoneNumber != guest.phoneNumber) return false;
        if (roomNumber != guest.roomNumber) return false;
        if (name != null ? !name.equals(guest.name) : guest.name != null) return false;
        return email != null ? email.equals(guest.email) : guest.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
        result = 31 * result + roomNumber;
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
