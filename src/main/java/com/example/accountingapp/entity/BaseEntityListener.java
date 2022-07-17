package com.example.accountingapp.entity;

import com.example.accountingapp.entity.common.UserPrincipal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.createdTime = LocalDateTime.now();
        baseEntity.updatedTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
            baseEntity.createdBy = ((UserPrincipal) principal).getId();
            baseEntity.updatedBy = ((UserPrincipal) principal).getId();
        }
    }

    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.updatedTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
            baseEntity.updatedBy = ((UserPrincipal) principal).getId();
        }
    }
}
