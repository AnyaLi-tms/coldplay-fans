package com.oocl.coldplayfans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oocl.coldplayfans.repository.MerchandiseDbRepository;
import com.oocl.coldplayfans.dao.Merchandise;
import java.util.List;


@Service
public class MerchandiseService {
    @Autowired
    private MerchandiseDbRepository merchandiseDbRepository;

    public MerchandiseService(MerchandiseDbRepository merchandiseDbRepository) {
        this.merchandiseDbRepository = merchandiseDbRepository;
    }

    public List<Merchandise> getAllMerchandise() {
        return merchandiseDbRepository.getAllMerchandise();
    }

    public Merchandise getMerchandiseById(Integer id) {
        return merchandiseDbRepository.getMerchandiseById(id);
    }
    public Merchandise createMerchandise(Merchandise merchandise) {
        return merchandiseDbRepository.saveMerchandise(merchandise);
    }
    public void deleteMerchandise(Integer id) {
        merchandiseDbRepository.deleteMerchandise(id);
    }
    public Merchandise updateMerchandise(Integer id, Merchandise updatedMerchandise) {
        return merchandiseDbRepository.updateMerchandise(id, updatedMerchandise);
    }
    public List<Merchandise> getMerchandiseByOrderId(Integer orderId) {
        return merchandiseDbRepository.getMerchandiseByOrderId(orderId);
    }
    

}
