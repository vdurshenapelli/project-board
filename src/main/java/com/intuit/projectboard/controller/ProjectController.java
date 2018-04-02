package com.intuit.projectboard.controller;

import com.intuit.projectboard.model.Bid;
import com.intuit.projectboard.model.Project;
import com.intuit.projectboard.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vikas on 3/28/2018.
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @RequestMapping("/hello")
    public String sayHi() {
        return projectServiceImpl.getResponse();
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity saveproject(@RequestBody Project project) {
        projectServiceImpl.addProject(project);

        return ResponseEntity.ok("Success.");
    }

    @RequestMapping(value = "/project/all", method = RequestMethod.GET)
    public ResponseEntity getAllProjects() {
        return ResponseEntity.ok(projectServiceImpl.getAllProjects());
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public void submitBid(@RequestBody Bid bid) {
        projectServiceImpl.submitBid(bid);
    }

    @GetMapping(value = "/bid/all/{projectId}")
    public ResponseEntity getBidsByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(projectServiceImpl.getAllBidsProjectId(projectId));
    }


    @RequestMapping(value = "/bid/winner/{projectId}", method = RequestMethod.GET)
    public ResponseEntity getWinnerByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(projectServiceImpl.getWinningBidForProjectId(projectId));
    }
}
