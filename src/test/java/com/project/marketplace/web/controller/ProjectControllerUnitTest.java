package com.project.marketplace.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.marketplace.message.Messages;
import com.project.marketplace.model.Project;
import com.project.marketplace.service.MarketplaceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vikas on 4/8/2018.
 */
public class ProjectControllerUnitTest {

    private MockMvc mockMvc;
    @Mock
    private MarketplaceService marketplaceService;
    @Mock
    private Messages messages;
    @InjectMocks
    ProjectController projectController ;

    @Before
    public void setUp() throws Exception{
        initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(projectController)
                .build();
    }

    @Test
    public void shouldSubmitNewProject() throws Exception {
        Project project = buildProject();
        when(marketplaceService.addProject(any(Project.class))).thenReturn(project);
        mockMvc.perform(
                post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project))
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldFetchProjectDetails() throws Exception {
        Project project = buildProject();
        when(marketplaceService.getProject(1l)).thenReturn(project);
        mockMvc.perform(
                get("/project/1")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchProjectDetailsForInvalidProjectId() throws Exception {
        when(marketplaceService.getProject(1l)).thenReturn(null);
        mockMvc.perform(
                get("/project/1")
        )
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFetchWinningBid() throws Exception {
        Project project = buildProject();
        when(marketplaceService.getProject(1l)).thenReturn(project);
        project.setDeadLine(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        mockMvc.perform(
                get("/1/bid/winner")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchWinningBid_Bid_Still_Open() throws Exception {
        Project project = buildProject();
        when(marketplaceService.getProject(1l)).thenReturn(project);
        mockMvc.perform(
                get("/1/bid/winner")
        )
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object object) throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(object);
    }

    private Project buildProject() {
            Project project = new Project();
            project.setBiddings(new ArrayList<>());
            project.setId(1);
            project.setDescription("Airline reservation");
            project.setMaximumBudget(3000);
            project.setDeadLine(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
            return project;
    }

}