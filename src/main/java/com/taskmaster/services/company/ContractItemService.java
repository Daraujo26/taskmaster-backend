package com.taskmaster.services.company;

import com.taskmaster.models.company.data.usage.ContractItem;
import com.taskmaster.models.company.data.usage.ContractItemDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.ContractItemRepository;
import com.taskmaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractItemService {

    @Autowired
    private ContractItemRepository contractItemRepository;

    @Autowired
    private UserRepository userRepository;

    public ContractItemDTO addContractItem(String email, ContractItem contractItem) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        contractItem.setUser(user);
        ContractItem savedContractItem = contractItemRepository.save(contractItem);
        return new ContractItemDTO(savedContractItem);
    }

    public List<ContractItemDTO> getAllContractItems(Long userId) {
        List<ContractItem> contractItems = contractItemRepository.findByUserId(userId);
        return contractItems.stream().map(ContractItemDTO::new).collect(Collectors.toList());
    }

    public ContractItem updateContractItem(String email, Long contractItemId, ContractItem contractItemUpdates) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        ContractItem existingContractItem = contractItemRepository.findByUserId(user.getId()).stream()
                .filter(contractItem -> contractItem.getId().equals(contractItemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Contract item not found for this user"));

        // Update only the fields that are provided
        if (contractItemUpdates.getItemClass() != null) {
            existingContractItem.setItemClass(contractItemUpdates.getItemClass());
        }
        if (contractItemUpdates.getItemName() != null) {
            existingContractItem.setItemName(contractItemUpdates.getItemName());
        }
        if (contractItemUpdates.getItemMessage() != null) {
            existingContractItem.setItemMessage(contractItemUpdates.getItemMessage());
        }
        if (contractItemUpdates.getItemPrice() != null) {
            existingContractItem.setItemPrice(contractItemUpdates.getItemPrice());
        }
        if (contractItemUpdates.getImages() != null) {
            existingContractItem.setImages(contractItemUpdates.getImages());
        }
        if (contractItemUpdates.getChargeTax() != null) {
            existingContractItem.setChargeTax(contractItemUpdates.getChargeTax());
        }
        if (contractItemUpdates.getOptional() != null) {
            existingContractItem.setOptional(contractItemUpdates.getOptional());
        }

        return contractItemRepository.save(existingContractItem);
    }

    public void deleteContractItem(String email, Long contractItemId) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        ContractItem contractItem = contractItemRepository.findByIdAndUserId(contractItemId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Contract item not found for this user"));
        contractItemRepository.delete(contractItem);
    }
}
