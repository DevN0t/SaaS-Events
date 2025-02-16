package com.eventix.event.modules.events.infra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_events", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer branchId;

    private String name;

    private String description;

    private String code;

    private String value;

    private String location;

    private String contact;

    private Boolean online;

    private Boolean status;

    private LocalDateTime date;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
