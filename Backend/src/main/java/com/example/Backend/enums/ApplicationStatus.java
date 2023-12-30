package com.example.Backend.enums;

public enum ApplicationStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DENIED("DENIED");

    private final String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
