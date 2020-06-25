package io.github.w7mike.controller;

import io.github.w7mike.model.Job;
import io.github.w7mike.model.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobRepository.class)
public class JobControllerLightIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JobRepository jobRepository;

    @Test
    @DisplayName("should return given Job")
    void httpGet_returnsGivenJob() throws Exception {
        // given
        var id = jobRepository.save(new Job("foo", LocalDateTime.now())).getId();

        // when + then
        mockMvc.perform(get("/jobs/" + id))
                .andExpect(status().is2xxSuccessful());
    }
}