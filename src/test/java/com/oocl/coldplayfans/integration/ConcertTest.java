package com.oocl.coldplayfans.integration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.oocl.coldplayfans.repository.ConcertDbRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        List<Concert> givenConcerts = concertRepository.getConcertsByDate(null, null, null);

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/concerts"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenConcerts.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenConcerts.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].imgUrl").value(givenConcerts.get(0).getImgUrl()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].city").value(givenConcerts.get(0).getCity()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(givenConcerts.get(0).getStatus().toString()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].deleted").value(givenConcerts.get(0).isDeleted()));

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
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenConcerts.get(0).getStatus().toString()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(givenConcerts.get(0).isDeleted()));
    }


    @Test
    public void post_concert_should_create_successfully() throws Exception {
        Concert newConcert = new Concert("Concert4", Date.valueOf("2010-08-01"), Time.valueOf("20:00:00"), "Wembley Stadium", "London", Date.valueOf("2025-07-01"), Time.valueOf("10:00:00"), "Description for Concert4", "https://www.coldplay.com/concert4/seatmap", Concert.Status.available, "https://www.coldplay.com/concert4.jpg");
        
        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions perform = client.perform(MockMvcRequestBuilders
                        .post("/concerts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newConcert)));
        perform.andExpect(MockMvcResultMatchers.status().isCreated());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/concerts"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(newConcert.getName()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.[0].imgUrl").value(newConcert.getImgUrl()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(newConcert.getStatus().toString()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.[0].city").value(newConcert.getCity()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.[0].deleted").value(newConcert.isDeleted()));
        
    }

    @Test
    public void delete_concert_by_id_successful() throws Exception {
        ResultActions perform = client.perform(MockMvcRequestBuilders.delete("/concerts/1"));

        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
        Concert concert = concertRepository.getConcertById(1);
        assertEquals(concert.isDeleted(), Boolean.TRUE);
    }

    @Test
    public void put_concert_by_id_successful() throws Exception {
        Concert newConcert = new Concert("Updated Concert", Date.valueOf("2025-08-01"), Time.valueOf("20:00:00"), "Wembley Stadium", "London", Date.valueOf("2025-07-01"), Time.valueOf("10:00:00"), "Description for Updated Concert", "https://www.coldplay.com/concert4/seatmap", Concert.Status.ended, "https://www.coldplay.com/concert4.jpg");

        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions perform = client.perform(MockMvcRequestBuilders
                .put("/concerts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newConcert)));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/concerts/1"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Concert"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.imgUrl").value("https://www.coldplay.com/concert4.jpg"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ended"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("London"));
    }
    


}

