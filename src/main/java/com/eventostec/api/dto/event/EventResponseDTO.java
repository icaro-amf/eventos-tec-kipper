package com.eventostec.api.dto.event;

import java.util.Date;
import java.util.UUID;

public record EventResponseDTO(UUID id, String title, String description, Date date, String uf, String city, Boolean remote, String eventUrl, String imgUrl) {
}
