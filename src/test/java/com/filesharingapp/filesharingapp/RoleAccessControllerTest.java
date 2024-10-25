package com.filesharingapp.filesharingapp;

import com.filesharingapp.filesharingapp.controllers.RoleAccessController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleAccessControllerTest {

    @InjectMocks
    RoleAccessController roleAccessController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roleAccessController).build();
    }

    @Test
    @DisplayName("Should allow access to all")
    public void shouldAllowAccessToAll() throws Exception {
        mockMvc.perform(get("/api/test/all"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should allow access to user")
    @WithMockUser(roles = "USER")
    public void shouldAllowAccessToUser() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("Should allow access to mod")
    @WithMockUser(roles = "MOD")
    public void shouldAllowAccessToMod() throws Exception {
        mockMvc.perform(get("/api/test/mod"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("Should allow access to admin")
    @WithMockUser(roles = "ADMIN")
    public void shouldAllowAccessToAdmin() throws Exception {
        mockMvc.perform(get("/api/test/admin"))
                .andExpect(status().isOk());
    }


}