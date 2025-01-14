package com.eventix.event.modules.participants.infra.repository;

import com.eventix.event.modules.participants.dto.ParticipantsDTO;
import com.eventix.event.modules.participants.dto.ParticipantsShortDTO;
import com.eventix.event.modules.participants.infra.model.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participants, Integer> {

    List<ParticipantsShortDTO> findAllByEventId(Integer branchId);

    Participants findByIdAndBranchId(Integer id, Integer branchId);

    ParticipantsDTO findByBranchIdAndId(Integer branchId, Integer id);
}
