package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {
	
/*This method calls the DAO class to insert a project row
 * */	
	
private	ProjectDao projectDao = new ProjectDao ();


public Project addProject(Project project) {
	return projectDao.insertProject(project);
}

/*The method fetchAllProjects call the fetchAllProjects() on the
 * projectDao object
 * */
public List<Project> fetchAllProjects() {
	return projectDao.fetchAllProjects();
	
}

/*This method calls the project Doa to get project details. it the project ID
 * is invalid it throws an exception
 * */

public Project fetchProjectById(Integer projectId) {
	return projectDao.fetchProjectById(projectId).orElseThrow(
			() -> new NoSuchElementException(
					"Project with Project ID =" + projectId 
					+ " does not exist."));
}
	

}
