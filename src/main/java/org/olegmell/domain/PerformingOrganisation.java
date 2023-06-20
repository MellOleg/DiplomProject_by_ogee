package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="performing_organisation")
@Getter
@Setter
public class PerformingOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String organisationName;
    private String organisationEmail;
    private String organisationPhoneNumber;
    private String organisationAddress;

}