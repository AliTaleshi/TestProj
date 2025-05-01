package com.example.TestProj.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean completed;
}
