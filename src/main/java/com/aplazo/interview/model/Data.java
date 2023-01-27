package com.aplazo.interview.model;

import javax.persistence.*;

@lombok.Data
@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="data_id")
    private Long id;

    @Lob
    @Column(name="request")
    private String request;

    @Lob
    @Column(name="response")
    private String response;
}
