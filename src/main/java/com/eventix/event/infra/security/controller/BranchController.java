package com.eventix.event.infra.security.controller;

import com.eventix.event.infra.security.dto.BranchCreateDTO;
import com.eventix.event.infra.security.dto.MessageReturnDTO;
import com.eventix.event.infra.security.model.Branch;
import com.eventix.event.infra.security.service.BranchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/branch/")
    public ResponseEntity<Branch> getBranch(HttpServletRequest request, @RequestParam Integer id) {
        return branchService.getBranch(request, id);
    }

    @GetMapping("/branches/")
    public ResponseEntity<List<Branch>> getBranches(HttpServletRequest request) {
        return branchService.getBranches(request);
    }

    @PostMapping("/branch/")
    public ResponseEntity<MessageReturnDTO> createBranch(HttpServletRequest request, @RequestBody BranchCreateDTO data) {
        return branchService.createBranch(request, data);
    }

    @PutMapping("/branch/")
    public ResponseEntity<MessageReturnDTO> updateBranch(HttpServletRequest request, @RequestBody BranchCreateDTO data, @RequestParam Integer id) {
        return branchService.updateBranch(request, data, id);
    }

    @DeleteMapping("/branch/")
    public ResponseEntity<MessageReturnDTO> deleteBranch(HttpServletRequest request, @RequestParam Integer id) {
        return branchService.deleteBranch(request, id);
    }
}
