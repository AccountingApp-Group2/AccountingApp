package com.example.accountingapp.entity;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Where(clause = "is_deleted=false")
public class Company extends BaseEntity {
    private String title;
    private String address1;
    private String address2;
    private String zip;
    private String representative;
    private String email;
    @Column(columnDefinition = "DATE")
    private LocalDateTime establishment_date;
    private boolean enabled;
    private String phone;
    // TODO: 5/24/22 Rumia
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "state_id")
//    private State state;


}
