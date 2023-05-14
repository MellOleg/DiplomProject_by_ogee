package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String service_name;

    @OneToOne(mappedBy = "services", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Request request;

    @OneToOne(mappedBy = "services", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private PerformingOrganisation performingOrganisation;
    //one to many


    public Services() {

    }

    public Services(String service_name) {
        this.service_name = service_name;
    }

}
