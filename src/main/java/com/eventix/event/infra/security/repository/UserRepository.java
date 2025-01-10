package com.eventix.event.infra.security.repository;

import com.eventix.event.infra.security.dto.UserDTO;
import com.eventix.event.infra.security.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("""
             SELECT new UserDTO(user.id, user.username) from UserModel user
            """)
    List<UserDTO> findUsers();

    Boolean existsByEmail(String email);

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    UserModel findUserModelByUsername(String subjectFromJwt);
}
