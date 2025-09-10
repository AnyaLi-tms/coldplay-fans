package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Merchandise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class MerchandiseDbRepository implements MerchandiseRepository {

    @Autowired
    private JpaMerchandiseRepository jpaMerchandiseRepository;

    public MerchandiseDbRepository(JpaMerchandiseRepository jpaMerchandiseRepository) {
        this.jpaMerchandiseRepository = jpaMerchandiseRepository;
    }


    @Override
    public List<Merchandise> getAllMerchandise() {
        return jpaMerchandiseRepository.findAll();
    }

    @Override
    public Merchandise getMerchandiseById(Integer id) {
        return jpaMerchandiseRepository.findById(id).orElse(null);
    }

    @Override
    public Merchandise saveMerchandise(Merchandise merchandise) {
        return jpaMerchandiseRepository.save(merchandise);
    }

    @Override
    public void deleteMerchandise(Integer id) {
        jpaMerchandiseRepository.deleteById(id);
    }

    @Override
    public Merchandise updateMerchandise(Integer id, Merchandise updatedMerchandise) {
        return jpaMerchandiseRepository.findById(id).map(existingMerchandise -> {
            existingMerchandise.setName(updatedMerchandise.getName());
            existingMerchandise.setDescription(updatedMerchandise.getDescription());
            existingMerchandise.setPrice(updatedMerchandise.getPrice());
            existingMerchandise.setImgUrl(updatedMerchandise.getImgUrl());
            existingMerchandise.setStatus(updatedMerchandise.getStatus());
            existingMerchandise.setIsDeleted(updatedMerchandise.getIsDeleted());
            return jpaMerchandiseRepository.save(existingMerchandise);
        }).orElse(null);
    }

    @Override
    public List<Merchandise> getMerchandiseByOrderId(Integer orderId) {
        return jpaMerchandiseRepository.findByOrderId(orderId);
    }

    @Override
    public List<Merchandise> findInStockMerchandises(String name) {
        return jpaMerchandiseRepository.findInStockMerchandises(name);
    }

    @Override
    public List<Merchandise> getMerchandiseByUserId(Integer userId) {
        return jpaMerchandiseRepository.findByUserId(userId);
    }
}
