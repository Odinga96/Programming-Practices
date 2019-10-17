package Main;

import java.io.Serializable;

public class Client implements Serializable {

    private String clientName;
    private String clientAddress;
    private String clientCity;
    private String clientEstate;
    private int    clientZipCode;
    private int    clientID;


    public Client() {
    }

    public Client(String clientName, String clientAddress, String clientCity, String clientEstate, int clientZipCode, int clientID) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientCity = clientCity;
        this.clientEstate = clientEstate;
        this.clientZipCode = clientZipCode;
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientEstate() {
        return clientEstate;
    }

    public void setClientEstate(String clientEstate) {
        this.clientEstate = clientEstate;
    }

    public int getClientZipCode() {
        return clientZipCode;
    }

    public void setClientZipCode(int clientZipCode) {
        this.clientZipCode = clientZipCode;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
