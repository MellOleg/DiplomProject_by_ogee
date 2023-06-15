package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Request> requestSet;
}
