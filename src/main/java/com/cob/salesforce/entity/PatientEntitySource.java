package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "patient_entity_source")
@Getter
@Setter
public class PatientEntitySource extends PatientDependencyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "entity_name")
    private String name;
    @Column
    private Long created;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @PrePersist
    private void beforeSaving() {
        created = new Date().getTime();
    }
}
