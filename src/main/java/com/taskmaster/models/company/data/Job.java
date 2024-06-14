package com.taskmaster.models.company.data;

import com.taskmaster.models.user.AppUser;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String message;

    @ElementCollection
    @CollectionTable(name = "job_dates", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "date")
    private List<Date> jobDates;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ElementCollection
    @CollectionTable(name = "job_contract_items", joinColumns = @JoinColumn(name = "job_id"))
    @MapKeyColumn(name = "contract_item_id")
    @Column(name = "quantity")
    private Map<Long, Integer> contractItems; // Map to hold contract item IDs and their quantities

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Date> getJobDates() {
        return jobDates;
    }

    public void setJobDates(List<Date> jobDates) {
        this.jobDates = jobDates;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Map<Long, Integer> getContractItems() {
        return contractItems;
    }

    public void setContractItems(Map<Long, Integer> contractItems) {
        this.contractItems = contractItems;
    }
}
