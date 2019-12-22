package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Lot;
import mpi.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/lots")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LotController {

    private LotService lotService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<Lot>> createLots(@RequestBody List<Lot> lots) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lotService.createLots(lots));
    }

    @DeleteMapping("/{lotId}")
    @ResponseBody
    public ResponseEntity<Lot> deleteLot(@PathParam("lotId") int lotId) {
        return ResponseEntity.ok(lotService.deleteLotById(lotId));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<List<Lot>> editLots(@RequestBody List<Lot> edits) {
        return ResponseEntity.ok(lotService.editLots(edits));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Lot>> getLotsByUser() {
        return ResponseEntity.ok(lotService.getLotsByUser());
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Lot>> getAllLots() {
        return ResponseEntity.ok(lotService.getAllLots());
    }

    @GetMapping("/item/name/{itemName}")
    @ResponseBody
    public ResponseEntity<List<Lot>> getLotsByItemName(@PathParam("itemName") String itemName) {
        return ResponseEntity.ok(lotService.getLotsByItemName(itemName));
    }

    @GetMapping("/item/type/{itemType}")
    @ResponseBody
    public ResponseEntity<List<Lot>> getLotsByItemType(@PathParam("itemType") String itemType) {
        return ResponseEntity.ok(lotService.getLotsByItemType(itemType));
    }
}
