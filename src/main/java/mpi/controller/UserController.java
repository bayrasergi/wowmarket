package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Message;
import mpi.model.User;
import mpi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/{userId}/certificates/{professionName}")
    @ResponseBody
    public ResponseEntity<User> addCertificate(@PathVariable("userId") int userId, @PathVariable("professionName") String professionName) {
        return ResponseEntity.ok(userService.addCertificate(userId, professionName));
    }

    @GetMapping("{userId}/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.getMessages(userId));
    }

    @PostMapping("{userId}/messages")
    @ResponseBody
    public ResponseEntity<Message> createMessage(@PathVariable("userId") int userId, @RequestBody Message message) {
        return ResponseEntity.ok(userService.createMessage(userId, message));
    }
}
