package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PerformingOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String performingName;
    private String performingEmail;
    private int performingNumber;
    private String performingAddress;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Services services;

}