package com.example.Backend.model.application;

import lombok.*;

import java.sql.*;

@Data
@Builder
@AllArgsConstructor
public class Application {
    int id;
    int userId;
    int petId;
    int shelterId;
    String message;
    Timestamp timeStamp;
    String status;
}
