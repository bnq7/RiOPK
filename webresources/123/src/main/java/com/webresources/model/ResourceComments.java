package com.webresources.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ResourceComments {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 5000)
    private String commentText;  // Текст комментария
    private String commentDate;  // Дата комментария

    @ManyToOne(fetch = FetchType.LAZY)
    private User commentOwner;   // Пользователь, оставивший комментарий

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resource;   // Связь с интернет-ресурсом, о котором оставлен комментарий

    public ResourceComments(String commentText, String commentDate, User commentOwner, Resource resource) {
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.commentOwner = commentOwner;
        this.resource = resource;
    }
}