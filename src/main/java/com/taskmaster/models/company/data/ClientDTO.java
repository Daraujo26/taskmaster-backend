package com.taskmaster.models.company.data;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String clientNotes;
    private String propertyAddress;
    private Long userId;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.companyName = client.getCompanyName();
        this.phoneNumber = client.getPhoneNumber();
        this.email = client.getEmail();
        this.clientNotes = client.getClientNotes();
        this.propertyAddress = client.getPropertyAddress();
        this.userId = client.getUser().getId();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientNotes() {
        return clientNotes;
    }

    public void setClientNotes(String clientNotes) {
        this.clientNotes = clientNotes;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
