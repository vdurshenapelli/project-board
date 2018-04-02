package com.intuit.projectboard.repo;

import com.intuit.projectboard.model.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vikas on 3/28/2018.
 */
public interface ProjectRepository extends CrudRepository<Project, String> {

}
