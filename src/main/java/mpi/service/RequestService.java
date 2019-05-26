package mpi.service;

import mpi.model.Request;
import mpi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    public Request getRequestById(int id) {
        return requestRepository.getOne(id);
    }
}
