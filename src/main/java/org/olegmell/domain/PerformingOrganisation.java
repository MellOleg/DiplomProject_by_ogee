package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PerformingOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String organisationName;
    private String organisationEmail;
    private int organisationPhoneNumber;
    private String organisationAddress;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Services services;

}