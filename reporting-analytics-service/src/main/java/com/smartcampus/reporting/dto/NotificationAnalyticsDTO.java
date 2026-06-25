package com.smartcampus.reporting.dto;

public class NotificationAnalyticsDTO {
    private String alertType;
    private int sentCount;
    private int actionedCount;

    public NotificationAnalyticsDTO(String alertType, int sentCount, int actionedCount) {
        this.alertType = alertType;
        this.sentCount = sentCount;
        this.actionedCount = actionedCount;
    }

    public String getAlertType() { return alertType; }
    public int getSentCount() { return sentCount; }
    public int getActionedCount() { return actionedCount; }
    
    public String getResponseRate() {
        if (sentCount == 0) return "0%";
        return String.format("%.1f%%", ((double) actionedCount / sentCount) * 100);
    }
}