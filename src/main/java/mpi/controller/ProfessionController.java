package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Profession;
import mpi.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/professions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessionController {

    private ProfessionService professionService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Profession>> getProfessions(){
        return ResponseEntity.ok(professionService.getProfessions());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Profession> createProfession(@RequestBody Profession profession) {
        return ResponseEntity.ok(professionService.createProfession(profession));
    }
}
