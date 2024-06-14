package com.taskmaster.controllers.company;

import com.taskmaster.models.company.data.Client;
import com.taskmaster.models.company.data.ClientDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;
import com.taskmaster.services.company.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(Principal principal, @RequestBody Client client) {
        String email = principal.getName();
        ClientDTO newClient = clientService.addClient(email, client);
        return ResponseEntity.ok(newClient);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<ClientDTO> clients = clientService.getAllClients(userId);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(Principal principal, @PathVariable Long clientId,
            @RequestBody Client client) {
        String email = principal.getName();
        Client updatedClient = clientService.updateClient(email, clientId, client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(Principal principal, @PathVariable Long clientId) {
        String email = principal.getName();
        clientService.deleteClient(email, clientId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String email = principal.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        return user.getId();
    }
}
