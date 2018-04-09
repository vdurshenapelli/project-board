package com.project.marketplace.service;

import com.project.marketplace.model.Bid;
import com.project.marketplace.model.Project;
import com.project.marketplace.repo.BidRepository;
import com.project.marketplace.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketplaceServiceImpl implements MarketplaceService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BidRepository bidRepository;


    public Project getProject(Long projectId) {
        return projectRepository.findOne(projectId);
    }

    public List<Project> getAllProjects() {

        List<Project> projectList = new ArrayList<>();
        projectRepository.findAll()
                .forEach(projectList::add);
        return projectList;
    }

    public Project addProject(Project project) {

        return projectRepository.save(project);
    }

    public Bid submitBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public List<Bid> getAllBidsProjectId(Long projectId) {

        return bidRepository.findByProjectId(projectId);
    }

    public Bid getWinningBidForProjectId(Long projectId) {

        return bidRepository.findTopByProjectIdOrderByEstimatedAmtAsc(projectId);
    }

}
