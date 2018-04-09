package com.project.marketplace.repo;

import com.project.marketplace.model.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<Bid, Integer> {

    List<Bid> findByProjectId(Long projectId);
    Bid findTopByProjectIdOrderByEstimatedAmtAsc(Long projectId);
}
