package fr.maetic.service.praticien;

import fr.maetic.dao.praticien.PraticienRepository;
import fr.maetic.model.praticien.Praticien;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PraticienServiceImpl implements PraticienService {

    private final PraticienRepository praticienRepository;

    @Override
    public Praticien save(Praticien praticien) {
        return praticienRepository.save(praticien);
    }

    @Override
    public Optional<Praticien> find(Long id) {
        return praticienRepository.findById(id);
    }

    @Override
    public List<Praticien> findAll() {
        return praticienRepository.findAll();
    }

    @Override
    public List<Praticien> findAll(Sort sort){
        return praticienRepository.findAll(sort);
    }

    @Override
    public Page<Praticien> findAll(Pageable pageable){
        return praticienRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    praticienRepository.deleteById(id);
    }

    @Override
    public void delete(Praticien praticien) {
        praticienRepository.delete(praticien);
    }

    @Override
    public void deleteAll() {
        praticienRepository.deleteAll();
    }

    @Override
    public long count() {
        return praticienRepository.count();
    }

}