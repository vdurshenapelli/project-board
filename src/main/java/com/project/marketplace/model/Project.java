package com.project.marketplace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long maximumBudget;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "UTC")
    @NotNull(message = "Deadline cannot be null")
    private Timestamp deadLine;
    @OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bid> biddings = new ArrayList<>();

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

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }

    public List<Bid> getBiddings() {
        return biddings;
    }


    public void setBiddings(List<Bid> biddings) {
        this.biddings = biddings;
    }
}
