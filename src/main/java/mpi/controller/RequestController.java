package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Request;
import mpi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/requests")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    private RequestService requestService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Request>> getRequestsByUser() {
        return ResponseEntity.ok(requestService.getRequestsByUser());
    }

    @GetMapping("/profession")
    @ResponseBody
    public ResponseEntity<List<Request>> getRequestsByProfession() {
        return ResponseEntity.ok(requestService.getRequestsByProfession());
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<List<Request>> editRequests(@RequestBody List<Request> edits) {
        return ResponseEntity.ok(requestService.editRequests(edits));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.createRequest(request));
    }

    @PutMapping("/{requestId}/user/{userId}")
    @ResponseBody
    public ResponseEntity<Request> acceptRequest(@PathVariable("requestId") int requestId,
                                                 @PathVariable("userId") int userId,
                                                 @RequestBody List<Integer> requestedItemsIds) {
        return ResponseEntity.ok(requestService.acceptRequest(requestId, userId, requestedItemsIds));
    }

    @DeleteMapping("/{requestId}")
    @ResponseBody
    public ResponseEntity<Request> closeRequest(@PathVariable("requestId") int requestId) {
        return ResponseEntity.ok(requestService.closeRequest(requestId));
    }

    @PutMapping("/{requestId}/price/{price}")
    @ResponseBody
    public ResponseEntity<Request> completeRequest(@PathVariable("requestId") int requestId, @PathVariable("price") int price) {
        return ResponseEntity.ok(requestService.completeRequest(requestId, price));
    }
}
