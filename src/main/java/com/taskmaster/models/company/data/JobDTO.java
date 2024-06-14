package com.taskmaster.models.company.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class JobDTO {

    private Long id;
    private String jobTitle;
    private String message;
    private List<Date> jobDates;
    private Date startTime;
    private Date endTime;
    private String status;
    private Long clientId;
    private Long userId;
    private Map<Long, Integer> contractItems;

    public JobDTO() {
    }

    public JobDTO(Job job) {
        this.id = job.getId();
        this.jobTitle = job.getJobTitle();
        this.message = job.getMessage();
        this.jobDates = job.getJobDates();
        this.startTime = job.getStartTime();
        this.endTime = job.getEndTime();
        this.status = job.getStatus();
        this.clientId = job.getClient() != null ? job.getClient().getId() : null;
        this.userId = job.getUser().getId();
        this.contractItems = job.getContractItems();
    }

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Integer> getContractItems() {
        return contractItems;
    }

    public void setContractItems(Map<Long, Integer> contractItems) {
        this.contractItems = contractItems;
    }
}
