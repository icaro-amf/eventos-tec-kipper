package com.eventostec.api.controller;

import com.eventostec.api.domain.coupon.Coupon;
import com.eventostec.api.dto.coupon.CouponRequestDTO;
import com.eventostec.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping
    public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO response){
        Coupon coupon = couponService.addCouponToEvent(eventId, response);
        return ResponseEntity.ok(coupon);
    }
}
