package com.example.Backend.model;

import lombok.Data;

@Data
public class Staff {
    private Long userId;
    private Long shelterId;

    public Long getShelterId() {
        return shelterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
