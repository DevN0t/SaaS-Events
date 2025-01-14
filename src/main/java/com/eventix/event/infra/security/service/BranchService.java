package com.eventix.event.infra.security.service;

import com.eventix.event.infra.security.dto.BranchCreateDTO;
import com.eventix.event.infra.security.dto.MessageReturnDTO;
import com.eventix.event.infra.security.model.Branch;
import com.eventix.event.infra.security.repository.BranchRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<Branch> getBranch(HttpServletRequest request, Integer id) {
        try {
            if (!jwtService.adminOnly(request.getHeader("Authorization"))) return ResponseEntity.status(403).build();

            var branch = branchRepository.findById(id).get();


            return ResponseEntity.ok().body(branch);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Branch>> getBranches(HttpServletRequest request) {
        try {
            if (!jwtService.adminOnly(request.getHeader("Authorization"))) return ResponseEntity.status(403).build();

            var branch = branchRepository.findAll();


            return ResponseEntity.ok().body(branch);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<MessageReturnDTO> createBranch(HttpServletRequest request, BranchCreateDTO data) {
        try {

            if (!jwtService.adminOnly(request.getHeader("Authorization"))) return ResponseEntity.status(403).body(
                    new MessageReturnDTO("Você não tem permissão para criar uma filial"));

            var branch = new Branch();
            branch.setName(data.name());
            branch.setStatus(true);
            branch.setLogo("defaultLogo.png");
            branch.setCreatedAt(LocalDateTime.now());
            branch.setName(data.name());
            branchRepository.save(branch);

            return ResponseEntity.ok().body(new MessageReturnDTO("Filial criada com sucesso"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageReturnDTO("Erro ao criar a filial"));
        }
    }

    public ResponseEntity<MessageReturnDTO> updateBranch(HttpServletRequest request, BranchCreateDTO data, Integer id) {
        try {
            if (!jwtService.adminOnly(request.getHeader("Authorization"))) return ResponseEntity.status(403).body(
                    new MessageReturnDTO("Você não tem permissão para atualizar uma filial"));

            var branch = branchRepository.findById(id).get();
            branch.setUpdatedAt(LocalDateTime.now());
            branch.setName(data.name());
            branchRepository.save(branch);

            return ResponseEntity.ok().body(new MessageReturnDTO("Filial atualizada com sucesso"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageReturnDTO("Erro ao atualizar a filial"));
        }
    }

    public ResponseEntity<MessageReturnDTO> deleteBranch(HttpServletRequest request, Integer id) {
        try {
            if (!jwtService.adminOnly(request.getHeader("Authorization"))) return ResponseEntity.status(403).body(
                    new MessageReturnDTO("Você não tem permissão para atualizar uma filial"));

            var branch = branchRepository.findById(id).get();
            branch.setStatus(false);
            branchRepository.save(branch);

            return ResponseEntity.ok().body(new MessageReturnDTO("Filial atualizada com sucesso"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageReturnDTO("Erro ao atualizar a filial"));
        }
    }
}
