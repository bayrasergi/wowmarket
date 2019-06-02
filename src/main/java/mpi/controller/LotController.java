package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Lot;
import mpi.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/lots")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LotController {

    private LotService lotService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Lot>> getAllLots() {
        return ResponseEntity.ok(lotService.getAllLots());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<Lot>> createLots(@RequestBody List<Lot> lots) {
        return ResponseEntity.ok(lotService.createLots(lots));
    }

    @GetMapping("/items/{itemId}")
    @ResponseBody
    public ResponseEntity<List<Lot>> getLotsByItemId(@PathParam("itemId") int itemId) {
        return ResponseEntity.ok(lotService.getLotsByItemId(itemId));
    }
}
