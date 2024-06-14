package com.taskmaster.controllers.company;

import com.taskmaster.models.company.data.usage.ContractItem;
import com.taskmaster.models.company.data.usage.ContractItemDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;
import com.taskmaster.services.company.ContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/contract-items")
public class ContractItemController {

    @Autowired
    private ContractItemService contractItemService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ContractItemDTO> addContractItem(Principal principal,
            @RequestBody ContractItem contractItem) {
        String email = principal.getName();
        ContractItemDTO newContractItem = contractItemService.addContractItem(email, contractItem);
        return ResponseEntity.ok(newContractItem);
    }

    @GetMapping
    public ResponseEntity<List<ContractItemDTO>> getAllContractItems(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<ContractItemDTO> contractItems = contractItemService.getAllContractItems(userId);
        return ResponseEntity.ok(contractItems);
    }

    @PutMapping("/{contractItemId}")
    public ResponseEntity<ContractItem> updateContractItem(Principal principal, @PathVariable Long contractItemId,
            @RequestBody ContractItem contractItem) {
        String email = principal.getName();
        ContractItem updatedContractItem = contractItemService.updateContractItem(email, contractItemId, contractItem);
        return ResponseEntity.ok(updatedContractItem);
    }

    @DeleteMapping("/{contractItemId}")
    public ResponseEntity<Void> deleteContractItem(Principal principal, @PathVariable Long contractItemId) {
        String email = principal.getName();
        contractItemService.deleteContractItem(email, contractItemId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String email = principal.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        return user.getId();
    }
}
