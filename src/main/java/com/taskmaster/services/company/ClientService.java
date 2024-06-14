package com.taskmaster.services.company;

import com.taskmaster.models.company.data.Client;
import com.taskmaster.models.company.data.ClientDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.ClientRepository;
import com.taskmaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public ClientDTO addClient(String email, Client client) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        client.setUser(user);
        Client savedClient = clientRepository.save(client);
        return new ClientDTO(savedClient);
    }

    public List<ClientDTO> getAllClients(Long userId) {
        List<Client> clients = clientRepository.findByUserId(userId);
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public Client updateClient(String email, Long clientId, Client clientUpdates) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Client existingClient = clientRepository.findByUserId(user.getId()).stream()
                .filter(client -> client.getId().equals(clientId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Client not found for this user"));

        // Update only the fields that are provided
        if (clientUpdates.getFirstName() != null) {
            existingClient.setFirstName(clientUpdates.getFirstName());
        }
        if (clientUpdates.getLastName() != null) {
            existingClient.setLastName(clientUpdates.getLastName());
        }
        if (clientUpdates.getCompanyName() != null) {
            existingClient.setCompanyName(clientUpdates.getCompanyName());
        }
        if (clientUpdates.getPhoneNumber() != null) {
            existingClient.setPhoneNumber(clientUpdates.getPhoneNumber());
        }
        if (clientUpdates.getEmail() != null) {
            existingClient.setEmail(clientUpdates.getEmail());
        }
        if (clientUpdates.getClientNotes() != null) {
            existingClient.setClientNotes(clientUpdates.getClientNotes());
        }
        if (clientUpdates.getPropertyAddress() != null) {
            existingClient.setPropertyAddress(clientUpdates.getPropertyAddress());
        }

        return clientRepository.save(existingClient);
    }

    public void deleteClient(String email, Long clientId) {
        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            Optional<Client> clientOptional = clientRepository.findById(clientId);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                if (!client.getUser().getId().equals(user.getId())) {
                    throw new IllegalArgumentException("Client does not belong to the user");
                }
                clientRepository.delete(client);
            } else {
                throw new IllegalArgumentException("Client not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
