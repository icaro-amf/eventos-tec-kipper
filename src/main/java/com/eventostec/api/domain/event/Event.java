package com.eventostec.api.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 24)
    private String title;

    @Column(length = 200)
    private String description;

    private String imgUrl;
    private String eventUrl;
    private boolean remote;

    @Column(columnDefinition = "timestamp(8)")
    private LocalDateTime date;
}
