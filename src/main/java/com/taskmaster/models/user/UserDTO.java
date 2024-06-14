package com.taskmaster.models.user;

import com.taskmaster.models.company.CompanyDetails;
import com.taskmaster.models.company.data.usage.UsageData;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private CompanyDetails companyDetails;
    private String token;
    private String password;
    private String role;
    private UsageData usageData;

    public UserDTO() {

    }

    public UserDTO(Long id, String firstName, String lastName, String email, String phoneNumber, String companyName,
            CompanyDetails companyDetails,
            String password, String role, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.companyDetails = companyDetails;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public UserDTO(AppUser user, String token) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
        this.companyName = user.getCompanyName();
        this.companyDetails = user.getCompanyDetails();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.token = token;
    }

    // Getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsageData getUsageData() {
        return usageData;
    }

    public void setUsageData(UsageData usageData) {
        this.usageData = usageData;
    }
}
