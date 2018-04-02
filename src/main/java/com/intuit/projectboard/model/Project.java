package com.intuit.projectboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikas on 3/28/2018.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long maximumBudget;
    private Date deadline;

    @OneToMany(mappedBy ="project", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bid> biddings= new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMaximumBudget() {
        return maximumBudget;
    }

    public void setMaximumBudget(long maximumBudget) {
        this.maximumBudget = maximumBudget;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Bid> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Bid> biddings) {
        this.biddings = biddings;
    }
}
