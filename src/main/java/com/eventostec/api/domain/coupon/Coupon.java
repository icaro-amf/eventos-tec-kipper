package com.eventostec.api.domain.coupon;

import com.eventostec.api.domain.eventos.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private Date valid;
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
