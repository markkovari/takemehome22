package io.spring.service.impl;

import io.spring.model.Adoptee;
import io.spring.repository.AdopteeRepository;
import io.spring.service.AdopteeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopteeServiceImpl implements AdopteeService {

    private final AdopteeRepository adopteeRepository;


    public AdopteeServiceImpl(AdopteeRepository adopteeRepository) {
        this.adopteeRepository = adopteeRepository;
    }

    @Override
    public List<Adoptee> getAll() {
        return (List<Adoptee>) adopteeRepository.findAll();
    }
}
