package com.eventostec.api.domain.address;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 85)
    private String city;

    @Column(length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}