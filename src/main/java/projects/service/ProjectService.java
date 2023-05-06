package projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {
	
/*This method calls the DAO class to insert a project row
 * */	
	
private	ProjectDao projectDao = new ProjectDao ();


public Project addProject(Project project) {
	return projectDao.insertProject(project);
}
	

}
