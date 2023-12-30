package com.example.Backend.model.application;

import lombok.*;

import java.sql.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    int id;
    int userId;
    int petId;
    int shelterId;
    int staffId;
    String message;
    Timestamp timeStamp;
    String status;
}
