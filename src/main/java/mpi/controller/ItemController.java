package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Item;
import mpi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {
    private ItemService itemService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItems(items));
    }
}
