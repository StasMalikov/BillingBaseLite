package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Customer(CustomerDTO customerDTO) {
        update(customerDTO);
    }

    public Customer update(CustomerDTO customerDTO) {
        this.firstName = customerDTO.getFirstName();
        this.middleName = customerDTO.getMiddleName();
        this.lastName = customerDTO.getLastName();
        this.birthDate = customerDTO.getBirthDate();
        return this;
    }

}
