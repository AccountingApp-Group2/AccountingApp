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
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    public Long createdBy;

    @Column(nullable = false, updatable = false)
    public LocalDateTime createdTime;

    @Column(nullable = false)
    public Long updatedBy;

    @Column(nullable = false)
    public LocalDateTime updatedTime;

    private Boolean isDeleted=false;

}
