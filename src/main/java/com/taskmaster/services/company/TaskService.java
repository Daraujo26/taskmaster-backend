package com.taskmaster.services.company;

import com.taskmaster.models.company.data.Client;
import com.taskmaster.models.company.data.Task;
import com.taskmaster.models.company.data.TaskDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.TaskRepository;
import com.taskmaster.repositories.ClientRepository;
import com.taskmaster.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskDTO addTask(String email, TaskDTO taskDTO) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));

        Client client = clientRepository.findById(taskDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Task task = new Task();
        task.setUser(user);
        task.setClient(client);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setStartTime(taskDTO.getStartTime());
        task.setEndTime(taskDTO.getEndTime());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());

        Task savedTask = taskRepository.save(task);
        return new TaskDTO(savedTask);
    }

    public List<TaskDTO> getAllTasks(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    public Task updateTask(String email, Long taskId, Task taskUpdates) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Task existingTask = taskRepository.findByUserId(user.getId()).stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found for this user"));

        // Update only the fields that are provided
        if (taskUpdates.getTitle() != null) {
            existingTask.setTitle(taskUpdates.getTitle());
        }
        if (taskUpdates.getDescription() != null) {
            existingTask.setDescription(taskUpdates.getDescription());
        }
        if (taskUpdates.getDate() != null) {
            existingTask.setDate(taskUpdates.getDate());
        }
        if (taskUpdates.getStartTime() != null) {
            existingTask.setStartTime(taskUpdates.getStartTime());
        }
        if (taskUpdates.getEndTime() != null) {
            existingTask.setEndTime(taskUpdates.getEndTime());
        }
        if (taskUpdates.getStatus() != null) {
            existingTask.setStatus(taskUpdates.getStatus());
        }
        if (taskUpdates.getPriority() != null) {
            existingTask.setPriority(taskUpdates.getPriority());
        }
        if (taskUpdates.getClient() != null) {
            existingTask.setClient(clientRepository.findById(taskUpdates.getClient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found")));
        }

        return taskRepository.save(existingTask);
    }

    public void deleteTask(String email, Long taskId) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Task does not belong to the user");
        }

        taskRepository.delete(task);
    }
}
