package com.intuit.projectboard.repo;

import com.intuit.projectboard.model.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vikas on 3/29/2018.
 */
public interface BidRepository extends CrudRepository<Bid, Integer> {

    List<Bid> findByProjectId(String projectId);

//    Bid findTopByProjectIdOrderByBidAmountAsc(String projectId);
}
