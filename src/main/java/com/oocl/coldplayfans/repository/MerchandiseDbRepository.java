package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Merchandise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class MerchandiseDbRepository implements MerchandiseRepository {

    @Autowired
    private JpaMerchandiseRepository MerchandiseRepository;

    public MerchandiseDbRepository(JpaMerchandiseRepository jpaMerchandiseRepository) {
        this.MerchandiseRepository = jpaMerchandiseRepository;
    }


    @Override
    public List<Merchandise> getAllMerchandise() {
        return MerchandiseRepository.findAll();
    }

    @Override
    public Merchandise getMerchandiseById(Integer id) {
        return MerchandiseRepository.findById(id).orElse(null);
    }

    @Override
    public Merchandise saveMerchandise(Merchandise merchandise) {
        return MerchandiseRepository.save(merchandise);
    }

    @Override
    public void deleteMerchandise(Integer id) {
        MerchandiseRepository.deleteById(id);
    }

    @Override
    public Merchandise updateMerchandise(Integer id, Merchandise updatedMerchandise) {
        return MerchandiseRepository.findById(id).map(existingMerchandise -> {
            existingMerchandise.setName(updatedMerchandise.getName());
            existingMerchandise.setDescription(updatedMerchandise.getDescription());
            existingMerchandise.setPrice(updatedMerchandise.getPrice());
            existingMerchandise.setImgUrl(updatedMerchandise.getImgUrl());
            existingMerchandise.setStatus(updatedMerchandise.getStatus());
            existingMerchandise.setIsDeleted(updatedMerchandise.getIsDeleted());
            return MerchandiseRepository.save(existingMerchandise);
        }).orElse(null);
    }

    @Override
    public List<Merchandise> getMerchandiseByOrderId(Integer orderId) {
        return MerchandiseRepository.findByOrderId(orderId);
    }
}
