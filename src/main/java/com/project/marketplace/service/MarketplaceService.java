package com.project.marketplace.service;

import com.project.marketplace.model.Bid;
import com.project.marketplace.model.Project;

import java.util.List;

public interface MarketplaceService {
    Project getProject(Long projectId);
    List<Project> getAllProjects();
    Project addProject(Project project);
    Bid submitBid(Bid bid);
    List<Bid> getAllBidsProjectId(Long projectId);
    Bid getWinningBidForProjectId(Long projectId);
}
