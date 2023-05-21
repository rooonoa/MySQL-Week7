package projects.service;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

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
/*this call projectDao.modifyProjectDetails() and then pass the object
 * as a parameter. the Dao returns a boolean that indicates whether the update
 * operation was successful. if false it throws a DbException 
 */
public void modifyProjectDetails(Project project) {
	if (!projectDao.modifyProjectDetails(project)) {
		throw new DbException ("Project with ID=" + project.getProjectId()
		 +  "does not exist. ");
	}
	
}

/*call deleteProject() in the project DAO. pass the project as a parameter.
 * Test the return value from the method call. If it returns false, throw a DbException
 */

public void deleteProject(Integer projectId) {
	if(!projectDao.deleteProject(projectId)){
		throw new DbException("Project with ID=" + projectId + " does not exist. ");
	}
	
}
	

}
