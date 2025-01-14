package com.eventix.event.modules.participants.infra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_participants", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate birth;

    private String phone;

    private Integer branchId;

    private String emergencyPhone;

    private String reference;

    private Boolean status;

    private Integer eventId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
