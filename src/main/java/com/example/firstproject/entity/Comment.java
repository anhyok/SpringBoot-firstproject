package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //primary key

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;    //parents article

    @Column
    private String nickname;    //writer

    @Column
    private String body;        //content
}