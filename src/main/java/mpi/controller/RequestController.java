package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Item;
import mpi.model.Request;
import mpi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/request")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    private RequestService requestService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.createRequest(request));
    }

    @PutMapping("/{requestId}")
    @ResponseBody
    public ResponseEntity<Request> acceptRequest(@PathVariable("requestId") int requestId,
                                                 int userId,
                                                 @RequestBody List<Item> requestedItems) {
        return ResponseEntity.ok(requestService.acceptRequest(requestId, userId, requestedItems));
    }

    @DeleteMapping("/{requestId}")
    @ResponseBody
    public ResponseEntity<Request> closeRequest(@PathVariable("requestId") int requestId) {
        return ResponseEntity.ok(requestService.closeRequest(requestId));
    }

    @PatchMapping("/{requestId}")
    @ResponseBody
    public ResponseEntity<Request> completeRequest(@PathVariable("requestId") int requestId, int price) {
        return ResponseEntity.ok(requestService.completeRequest(requestId, price));
    }
}
