package com.taskmaster.models.company.data;

import java.util.Date;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String status;
    private String priority;
    private Long clientId;
    private Long userId;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.date = task.getDate();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.clientId = task.getClient() != null ? task.getClient().getId() : null;
        this.userId = task.getUser().getId();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
}
