package com.example.qresent.service;

import com.example.qresent.model.Materie;
import com.example.qresent.repository.MaterieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterieService {

    private final MaterieRepository materieRepository;

    public MaterieService(MaterieRepository materieRepository) {
        this.materieRepository = materieRepository;
    }

    public List<Materie> listMaterii() {
       return materieRepository.findAll();
    }

    public Materie saveMaterie(final Materie materie) {
        return materieRepository.save(materie);
    }

    public void deleteMaterie(final Long id) {
        materieRepository.delete(materieRepository.findById(id).orElseGet(Materie::new));
    }

    public Optional<Materie> findMaterieById(Long id) {
        return materieRepository.findById(id);
    }


}
