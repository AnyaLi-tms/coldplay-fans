package com.oocl.coldplayfans.integration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.oocl.coldplayfans.repository.ConcertDbRepository;
import com.oocl.coldplayfans.dao.Banner;
import com.oocl.coldplayfans.dao.Concert;



@SpringBootTest
@AutoConfigureMockMvc
public class ConcertTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private ConcertDbRepository concertRepository;

    @BeforeEach
    public void setup() {
    }


    @Test
    public void should_return_concerts_when_get_all_concerts_exist() throws Exception {
        List<Concert> givenConcerts = concertRepository.getAllConcerts();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/concerts"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenConcerts.get(3).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenConcerts.get(3).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].imgUrl").value(givenConcerts.get(3).getImgUrl()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].city").value(givenConcerts.get(3).getCity()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(givenConcerts.get(3).getStatus().toString()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].isDeleted").value(givenConcerts.get(3).isDeleted()));

        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenConcerts.get(2).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenConcerts.get(1).getId()));
    }

    @Test
    public void should_return_concert_when_get_id_concert_exist() throws Exception {
        List<Concert> givenConcerts = concertRepository.getAllConcerts();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/concerts/1"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenConcerts.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenConcerts.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.imgUrl").value(givenConcerts.get(0).getImgUrl()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.city").value(givenConcerts.get(0).getCity()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenConcerts.get(0).getStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(givenConcerts.get(0).isDeleted()));
    }

}

