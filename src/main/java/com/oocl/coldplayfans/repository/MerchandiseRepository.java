package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Merchandise;
import java.util.List;

public interface MerchandiseRepository {
    List<Merchandise> getAllMerchandise();
    Merchandise getMerchandiseById(Integer id);
    Merchandise saveMerchandise(Merchandise merchandise);
    void deleteMerchandise(Integer id);
    Merchandise updateMerchandise(Integer id, Merchandise updatedMerchandise);
}
