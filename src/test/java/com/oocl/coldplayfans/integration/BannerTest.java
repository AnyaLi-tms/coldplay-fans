package com.oocl.coldplayfans.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.coldplayfans.dao.Banner;
import com.oocl.coldplayfans.repository.BannerDbRepository;



@SpringBootTest
@AutoConfigureMockMvc
public class BannerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private BannerDbRepository bannerRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void should_return_banners_when_get_all_banners_exist() throws Exception {
        List<Banner> givenBanners = bannerRepository.getAllBanners();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/banners"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenBanners.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenBanners.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].imgUrl").value(givenBanners.get(0).getImgUrl()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].link").value(givenBanners.get(0).getLink()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(givenBanners.get(0).getStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].isDeleted").value(givenBanners.get(0).getIsDeleted()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenBanners.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenBanners.get(2).getId()));
    }
    



    @Test
    public void should_return_banner_when_get_id_banner_exist() throws Exception {
        List<Banner> givenBanners = bannerRepository.getAllBanners();

        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/banners/1"));

        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenBanners.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenBanners.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.imgUrl").value(givenBanners.get(0).getImgUrl()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.link").value(givenBanners.get(0).getLink()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenBanners.get(0).getStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(givenBanners.get(0).getIsDeleted()));
    }


    @Test
    public void post_banner_should_create_successfully() throws Exception {
        List<Banner> givenBanners = bannerRepository.getAllBanners();
        Banner newBanner = new Banner("Banner4", "https://www.coldplay.com/wp/wp-content/uploads/2025/08/new-dates.webp", "https://www.coldplay.com/7th-and-8th-september-wembley-shows-to-be-rescheduled/", true);
        givenBanners.add(newBanner);
        
        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions perform = client.perform(MockMvcRequestBuilders
                        .post("/banners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBanner)));
        perform.andExpect(MockMvcResultMatchers.status().isCreated());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/banners/4"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenBanners.get(3).getName()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.imgUrl").value(givenBanners.get(3).getImgUrl()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.link").value(givenBanners.get(3).getLink()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenBanners.get(3).getStatus()));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(givenBanners.get(3).getIsDeleted()));
    }
    

    @Test
    public void delete_banner_by_id_successful() throws Exception {
        ResultActions perform = client.perform(MockMvcRequestBuilders.delete("/banners/1"));

        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
        Banner banner = bannerRepository.getBannerById(1);
        assertEquals(banner.getIsDeleted(), Boolean.TRUE);
        assertEquals(banner.getStatus(), Boolean.FALSE);
    }
    

    @Test
    public void put_banner_by_id_successful() throws Exception {
        Banner newBanner = new Banner("Updated Banner", "https://www.coldplay.com/wp/wp-content/uploads/2025/08/updated-banner.webp", "https://www.coldplay.com/updated-banner", true);

        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions perform = client.perform(MockMvcRequestBuilders
                .put("/banners/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBanner)));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions perform_second = client.perform(MockMvcRequestBuilders.get("/banners/1"));
        perform_second.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Banner"));
    }


}