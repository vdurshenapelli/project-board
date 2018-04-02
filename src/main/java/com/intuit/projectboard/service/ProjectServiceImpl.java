package com.intuit.projectboard.service;

import com.intuit.projectboard.model.Bid;
import com.intuit.projectboard.model.Project;
import com.intuit.projectboard.repo.BidRepository;
import com.intuit.projectboard.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikas on 3/28/2018.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private BidRepository bidRepository;

    public String getResponse() {
        return "Hi From Service";
    }

    public List<Project> getAllProjects() {

        List<Project> projectList = new ArrayList<>();
        projectRepository.findAll()
                .forEach(projectList::add);
        return projectList;
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void submitBid(Bid bid) {
        bidRepository.save(bid);
    }

    public List<Bid> getAllBidsProjectId(String projectId) {

        return bidRepository.findByProjectId(projectId);
    }

    public Bid getWinningBidForProjectId(String projectId) {
//        return bidRepository.findTopByProjectIdOrderByBidAmountAsc(projectId);

        return  new Bid();
    }

}
