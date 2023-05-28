package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private ContactStatus status;

    @Column(name = "VALUE")
    private String contactValue;

    @Column(name = "CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private ContactCategory category;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LAST_CNTCT_DATE")
    private Date lastContactDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acct_id", nullable = false)
    private Account account;

    private enum ContactCategory {
        EMAIL,
        MOBILE,
        LANDLINE,
        OFFICE,
        HOME
    }
    public enum ContactStatus {
        ACTIVE,
        UNKNOWN,
        ARCHIVED
    }

}
