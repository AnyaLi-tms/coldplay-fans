package com.oocl.coldplayfans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oocl.coldplayfans.repository.MerchandiseDbRepository;
import com.oocl.coldplayfans.repository.OrderDbRepository;

import com.oocl.coldplayfans.model.Merchandise;
import com.oocl.coldplayfans.model.Order;
import com.oocl.coldplayfans.dto.UserMerchandiseResponse;
import com.oocl.coldplayfans.dto.MerchandiseOrderResponse;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;



@Service
public class MerchandiseService {
    @Autowired
    private MerchandiseDbRepository merchandiseDbRepository;
    @Autowired
    private OrderDbRepository orderDbRepository;

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

    public List<Merchandise> getInStockMerchandises(String type) {
        return merchandiseDbRepository.findInStockMerchandises(type);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Merchandise> buyMerchandises(Integer userId, Integer quantity, String type, String address) {
        List<Merchandise> merchandises = merchandiseDbRepository.findInStockMerchandises(type);
        if (merchandises.size() < quantity) {
            throw new RuntimeException("库存不足，请重试");
        }
        Order order = new Order("merchandise", address);
        Order newOrder = orderDbRepository.createOrder(order);
        for (int i = 0; i < quantity; i++) {
            Merchandise merchandise = merchandises.get(i);
            merchandise.setUserId(userId);
            merchandise.setStatus("purchased");
            merchandise.setPurchaseDate(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
            merchandise.setOrderId(newOrder.getId());
            merchandiseDbRepository.updateMerchandise(merchandise.getId(), merchandise);
        }
        return merchandises;
    }

    public List<UserMerchandiseResponse> loadMerchandiseOrders(int userId) {
        List<Merchandise> userMerchandises = merchandiseDbRepository.getMerchandiseByUserId(userId);
        Map<Integer, List<Merchandise>> merchandiseListByOrderId = userMerchandises.stream().collect(Collectors.groupingBy(Merchandise::getOrderId));
        List<UserMerchandiseResponse> userMerchandiseOrderList = new ArrayList<>();
        for(List<Merchandise> merchandises : merchandiseListByOrderId.values()){
            Integer orderId = merchandises.getFirst().getOrderId();
            Integer amount = merchandises.size();
            String imgUrl = merchandises.getFirst().getImgUrl();
            BigDecimal totalPrice = merchandises.stream()
                    .map(Merchandise::getPrice)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            LocalDateTime purchaseDate = merchandises.getFirst().getPurchaseDate();
            List<MerchandiseOrderResponse> merchandiseOrderResponses = new ArrayList<>();
            for(Merchandise merchandise : merchandises){
                MerchandiseOrderResponse merchandiseOrderResponse = new MerchandiseOrderResponse(merchandise.getId(), merchandise.getName(), merchandise.getPrice());
                merchandiseOrderResponses.add(merchandiseOrderResponse);
            }
            UserMerchandiseResponse userMerchandiseResponse = new UserMerchandiseResponse(orderId, amount, totalPrice, purchaseDate, "交易完成", merchandiseOrderResponses, imgUrl);
            userMerchandiseOrderList.add(userMerchandiseResponse);
        }
        userMerchandiseOrderList.sort((o1, o2) -> o2.getPurchaseDate().compareTo(o1.getPurchaseDate()));
        return userMerchandiseOrderList;
    }

    public List<Merchandise> getAllDistinctMerchandise(String query) {
        return merchandiseDbRepository.getAllDistinctMerchandise(query);
    }

    public Map<String, Integer> getMerchandiseCountMap() {
        List<Merchandise> merchandises = merchandiseDbRepository.getAllMerchandise();
        merchandises = merchandises.stream()
                .filter(m -> m.getStatus().equals("instock"))
                .collect(Collectors.toList());
        return merchandises.stream()
                .collect(Collectors.groupingBy(m -> m.getName(), Collectors.summingInt(m -> 1)));
    }
    
    public List<Merchandise> getMerchandiseByType(String type) {
        return merchandiseDbRepository.getMerchandiseByType(type);
    }

}
