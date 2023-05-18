package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Services services;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Statuses statuses;

    private String filename;

    public Request() {
    }

    public Request(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public String getStatus(){
        if(status != null && !status.isEmpty()){
            return status ;
        }
        else {
            return status = "На рассмотрении";
        }
    }
}