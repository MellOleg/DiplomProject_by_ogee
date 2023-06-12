package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Getter
@Setter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Пожалуйста, введите сообщение")
    private String text;
    @NotBlank(message = "Пожалуйста, введите тэг")
    private String tag;

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

    public Request(String text, String tag, User user, Status status) {
        this.author = user;
        this.text = text;
        this.tag = tag;
        this.status = status;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

}