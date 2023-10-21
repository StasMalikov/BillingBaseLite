package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private List<AccountDTO> accountDTOList;

    public CustomerDTO(Customer customer, List<AccountDTO> accountDTOList) {
        this.id = customer.getId().toString();
        this.firstName = customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.lastName = customer.getLastName();
        this.birthDate = customer.getBirthDate();
        this.accountDTOList = accountDTOList;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId().toString();
        this.firstName = customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.lastName = customer.getLastName();
        this.birthDate = customer.getBirthDate();
    }

}
