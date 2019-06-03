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
@RequestMapping("/requests")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    private RequestService requestService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
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
