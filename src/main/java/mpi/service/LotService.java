package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.Lot;
import mpi.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LotService {
    private LotRepository lotRepository;

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public List<Lot> createLots(List<Lot> lots) {
        return lotRepository.saveAll(lots);
    }

    public List<Lot> getLotsByItemId(int itemId) {
        return lotRepository.findAllByItem_Id(itemId);
    }
}
