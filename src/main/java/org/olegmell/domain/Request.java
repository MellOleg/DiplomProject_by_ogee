package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
public class Request implements Comparable<Request>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Пожалуйста, введите сообщение")
    private String text;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull(message = "Пожалуйста, выберите адрес из списка")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable=false)
    private Services service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    private String filename;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "last_modified")
    private Date lastModifiedTime;

    @Column(name = "completion_date")
    private Date completedTime;

    public Request() {
    }

    public Request(String text, Address address, User user, Status status) {
        this.author = user;
        this.text = text;
        this.address = address;
        this.status = status;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    @Override
    public int compareTo(Request requestToCompare) {
        return getCreatedTime().compareTo(requestToCompare.getCreatedTime());
    }
}