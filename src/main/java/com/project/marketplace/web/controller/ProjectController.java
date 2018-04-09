package com.project.marketplace.web.controller;

import com.google.common.collect.ImmutableBiMap;
import com.project.marketplace.message.ErrorRepresentation;
import com.project.marketplace.message.Messages;
import com.project.marketplace.model.Bid;
import com.project.marketplace.model.Project;
import com.project.marketplace.service.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static com.project.marketplace.web.ResponseEntityExtended.*;
import static org.springframework.http.ResponseEntity.accepted;

/**
 * Created by vikas on 3/28/2018.
 */
@RestController()
@RequestMapping(produces = "application/json")
public class ProjectController {

    @Autowired
    Messages messages;
    @Autowired
    private MarketplaceService marketplaceService;

    @PostMapping(value = "/project")
    public ResponseEntity saveproject(@RequestBody Project project) {

        project = marketplaceService.addProject(project);
        return accepted().body(ImmutableBiMap.of("projectId", project.getId()));
    }

    @GetMapping(value = "/project/{projectId}")
    public ResponseEntity fetchProject(@PathVariable Long projectId) {
        Project project = marketplaceService.getProject(projectId);
        if (project == null) {
            return notFound(getErrorResponseFor("project.id.invalid"));
        }
        return ok(project);
    }

    @GetMapping(value = "/project/all")
    public ResponseEntity getAllProjects() {
        List<Project> projectList = marketplaceService.getAllProjects();
        if (CollectionUtils.isEmpty(projectList)) {
            return notFound(getErrorResponseFor("projects.empty"));
        }
        return ResponseEntity.ok(projectList);
    }

    @PostMapping(value = "/bid")
    public ResponseEntity submitBid(@RequestBody Bid bid) {

        Project project = marketplaceService.getProject(bid.getProject().getId());
        if (project == null) {
            return notFound(getErrorResponseFor("project.id.invalid"));
        } else if (project.getDeadLine().before(currentDateTimeInUTC())) {
            return badRequest(getErrorResponseFor("bid.closed"));
        }
        bid = marketplaceService.submitBid(bid);
        return accepted().body(ImmutableBiMap.of("bidId", bid.getId()));
    }

    @GetMapping(value = "/{projectId}/bid/winner")
    public ResponseEntity getWinnerByProjectId(@PathVariable Long projectId) {
        Project project = marketplaceService.getProject(projectId);
        if (null == project) {
            return notFound(getErrorResponseFor("project.id.invalid"));
        } else if (project.getDeadLine().after(currentDateTimeInUTC())) {
            return badRequest(getErrorResponseFor("bid.still.open"));
        }
        return ok(marketplaceService.getWinningBidForProjectId(projectId));
    }

    private ErrorRepresentation getErrorResponseFor(String errorCode) {
        ErrorRepresentation error = new ErrorRepresentation(errorCode, messages.get(errorCode));
        return error;
    }

    private Date currentDateTimeInUTC() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
    }
}
