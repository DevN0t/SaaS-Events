package com.eventix.event.modules.events.infra.repository;

import com.eventix.event.modules.events.dto.EventDTO;
import com.eventix.event.modules.events.dto.EventShortDTO;
import com.eventix.event.modules.events.infra.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByIdAndBranchId(Integer id, Integer branchId);

    List<EventShortDTO> findAllByBranchId(Integer branchId);

    EventDTO findByBranchIdAndId(Integer branchId, Integer id);

}
