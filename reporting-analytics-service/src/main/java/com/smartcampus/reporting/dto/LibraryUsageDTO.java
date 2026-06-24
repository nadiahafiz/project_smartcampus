package com.smartcampus.reporting.dto;

public class LibraryUsageDTO {
    private String resourceName;
    private String resourceType; // e.g., "Room" or "Book"
    private int usagePercentage;
    private String peakTime;

    public LibraryUsageDTO(String resourceName, String resourceType, int usagePercentage, String peakTime) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.usagePercentage = usagePercentage;
        this.peakTime = peakTime;
    }

    public String getResourceName() { return resourceName; }
    public String getResourceType() { return resourceType; }
    public int getUsagePercentage() { return usagePercentage; }
    public String getPeakTime() { return peakTime; }
}