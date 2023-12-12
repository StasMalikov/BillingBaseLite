package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POLICY")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VALUES")
    private String values;

    @Column(name = "DESCRIPTION")
    private String description;

//    @ManyToMany(mappedBy = "policies", fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "policies_job_template",
//            joinColumns = @JoinColumn(name = "policy_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_Template_id"))
//    private Set<JobTemplate> jobTemplates;

}
