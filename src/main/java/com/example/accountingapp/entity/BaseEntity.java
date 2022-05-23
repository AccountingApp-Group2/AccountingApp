package com.example.accountingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long createdBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private Long updatedBy;

    @Column(nullable = false)
    private LocalDateTime updatedTime;


    private Boolean isDeleted=false;

    @PrePersist
    public void onPrePersist() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime=LocalDateTime.now();
        this.createdBy = 1L;
        this.updatedBy=1L;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedTime=LocalDateTime.now();
        this.updatedBy=1L;
    }

}
