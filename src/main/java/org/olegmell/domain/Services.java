package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String service_name;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<Request> requests;

    @OneToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private Set<PerformingOrganisation> performingOrganisation;
    //one to many


    public Services() {

    }

    public Services(String service_name) {
        this.service_name = service_name;
    }

}
