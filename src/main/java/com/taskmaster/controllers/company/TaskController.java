package com.taskmaster.controllers.company;

import com.taskmaster.models.company.data.Task;
import com.taskmaster.models.company.data.TaskDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;
import com.taskmaster.services.company.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(Principal principal, @RequestBody TaskDTO taskDTO) {
        String email = principal.getName();
        TaskDTO newTask = taskService.addTask(email, taskDTO);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<TaskDTO> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(Principal principal, @PathVariable Long taskId, @RequestBody Task task) {
        String email = principal.getName();
        Task updatedTask = taskService.updateTask(email, taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(Principal principal, @PathVariable Long taskId) {
        String email = principal.getName();
        taskService.deleteTask(email, taskId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String email = principal.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        return user.getId();
    }
}
