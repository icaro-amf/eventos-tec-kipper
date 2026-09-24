package com.eventostec.api.service;

import com.eventostec.api.domain.coupon.Coupon;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.dto.coupon.CouponRequestDTO;
import com.eventostec.api.repositories.CouponRepository;
import com.eventostec.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(request.code());
        coupon.setDiscount(request.discount());
        coupon.setEvent(event);
        coupon.setValid(new Date(request.valid()));

        return couponRepository.save(coupon);
    }

}
