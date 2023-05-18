package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Statuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String statusName;

    @OneToOne(mappedBy = "statuses", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Request request;

    public Statuses() {

    }

    public Statuses(String statusName) {
        this.statusName = statusName;
    }
}
