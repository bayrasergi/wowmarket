package mpi.service;

import lombok.AllArgsConstructor;
import mpi.exception.EntityException;
import mpi.model.Profession;
import mpi.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessionService {

    private ProfessionRepository professionRepository;

    public List<Profession> getProfessions() {
        return professionRepository.findAll();
    }

    public Profession createProfession(Profession profession) {
        validateProfessionName(profession);
        String name = String.format("%s%s",
                profession.getName().substring(0, 0).toUpperCase(),
                profession.getName().substring(1, profession.getName().length() - 1).toLowerCase());
        profession.setName(name);
        return professionRepository.save(profession);
    }

    private void validateProfessionName(Profession profession) {
        if (profession.getName() == null || profession.getName().isEmpty()) {
            throw new EntityException("Profession name must not be null", HttpStatus.BAD_REQUEST, profession);
        }
        List<Profession> professions = professionRepository.findAllByName(profession.getName());
        if (!professions.isEmpty()) {
            throw new EntityException(String.format("Profession with name '%s' already exist!", profession.getName()),
                    HttpStatus.BAD_REQUEST, profession);
        }
    }
}
