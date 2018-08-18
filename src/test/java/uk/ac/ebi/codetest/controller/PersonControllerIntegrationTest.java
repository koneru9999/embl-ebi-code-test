package uk.ac.ebi.codetest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.ebi.codetest.CodeTestApplication;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CodeTestApplication.class)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenNoAuthz_whenGetPersons_thenStatus401() throws Exception {
        mvc.perform(get("/person"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenUserNoPrivileges_whenGetPersons_thenStatus403() throws Exception {
        mvc.perform(get("/person")
                .with(httpBasic("user", "password"))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void givenReadUser_whenGetPersons_thenStatus200() throws Exception {
        mvc.perform(get("/person")
                .with(httpBasic("user2", "password"))
        ).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void givenReadWriteUser_whenGetPersons_thenStatus200() throws Exception {
        mvc.perform(get("/person")
                .with(httpBasic("manager", "password"))
        ).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void givenNoAuthz_whenPutPerson_thenStatus401() throws Exception {
        mvc.perform(put("/person"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenReadUser_whenPutPerson_thenStatus403() throws Exception {
        mvc.perform(put("/person")
                .content(getPersonInJson())
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("user2", "password"))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void givenWriteUser_whenPutPerson_thenStatus201() throws Exception {
        mvc.perform(put("/person")
                .content(getPersonInJson())
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("manager", "password"))
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.first_name", is("Venkaiah")))
                .andExpect(jsonPath("$.last_name", is("Koneru")))
                .andExpect(jsonPath("$.favourite_colour", is("blue")))
                .andExpect(jsonPath("$.age", is("35")));

        mvc.perform(get("/person")
                .with(httpBasic("manager", "password"))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.person", hasSize(1)))
                .andExpect(jsonPath("$.person[0].first_name", is("Venkaiah")))
                .andExpect(jsonPath("$.person[0].last_name", is("Koneru")))
                .andExpect(jsonPath("$.person[0].favourite_colour", is("blue")))
                .andExpect(jsonPath("$.person[0].age", is("35")));
    }

    private String getPersonInJson() {
        return new StringBuilder("{")
                .append("\"first_name\": \"Venkaiah\", ")
                .append("\"last_name\": \"Koneru\", ")
                .append("\"age\": \"35\", ")
                .append("\"favourite_colour\": \"blue\"")
                .append("}")
                .toString();
    }
}
