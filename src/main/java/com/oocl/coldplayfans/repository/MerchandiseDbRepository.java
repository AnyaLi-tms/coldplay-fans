package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Merchandise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.ArrayList;

@Repository
public class MerchandiseDbRepository implements MerchandiseRepository {

    @Autowired
    private JpaMerchandiseRepository jpaMerchandiseRepository;

    public MerchandiseDbRepository(JpaMerchandiseRepository jpaMerchandiseRepository) {
        this.jpaMerchandiseRepository = jpaMerchandiseRepository;
    }

    @Override
    public List<Merchandise> getAllMerchandise() {
        return jpaMerchandiseRepository.findAll().stream().filter(m -> !m.getIsDeleted()).toList();
    }

    @Override
    public List<Merchandise> getAllDistinctMerchandise(String query) {
        List<Merchandise> merchandises = jpaMerchandiseRepository.findAll();
        List<String> distinctName = jpaMerchandiseRepository.findAllInStock(query);
        List<Merchandise> result = new ArrayList<>();
        for (String name : distinctName) {
            for (Merchandise merchandise : merchandises) {
                if (merchandise.getName().equals(name) && merchandise.getStatus().equals("instock") && !merchandise.getIsDeleted()) {
                    result.add(merchandise);
                    break;
                }
            }
        }
        return result;
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
    public List<Merchandise> findInStockMerchandises(String type) {
        return jpaMerchandiseRepository.findInStockMerchandises(type);
    }

    @Override
    public List<Merchandise> getMerchandiseByUserId(Integer userId) {
        return jpaMerchandiseRepository.findByUserId(userId);
    }

    @Override
    public List<Merchandise> getMerchandiseByType(String type) {
        return jpaMerchandiseRepository.findByType(type);
    }   
}
