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

    //one to many

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "service_organisation",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    )
    private Set<PerformingOrganisation> organisationsServices;

    public Services() {    }

    public Services(String service_name) {
        this.service_name = service_name;
    }

}
