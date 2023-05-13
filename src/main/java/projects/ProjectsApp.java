/**


 * 
 */
package projects;

import java.math.BigDecimal;


import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;


/**
 * This is our main method that will call the DbConnection.getConnection() method to see if we can gain connection to the MySQL server  
 *
 */
public class ProjectsApp {
	/*Adding a private instance variable list of operations 
	 * */
	//@formatter:off
	private List<String> operations = List.of (
			"1) Add a project",
			"2) List projects",
			"3) Select a Project"
			);
	//@formatter:on
	
	/*Adding a private instance variable for the scanner 
	 * */
	private Scanner scanner = new Scanner (System.in);
	
	/*Adding a private instance variable ProjectService named projectService
	 * */
	
	private ProjectService projectService = new ProjectService();
	
	/*Adding an instance variable type Project named curProject
	 * */
	private Project curProject;
	
	/*creating a new main class ProjectApp object and call the method processUserSelections()
	 * */
	public static void main(String[] args) {
		
	ProjectsApp app = new ProjectsApp();
	app.processUserSelections ();
					
	}

	/*creating the method orocessUserSelections() method as an instance method 
	 * */
	private void processUserSelections() {
		boolean done = false;
		
		while (!done) {
			try {
				int selection = getUserSelection();
				
				switch(selection) {
				case -1:
					done = exitmenu();
					break;
					
				case 1:
					createProject();
					break;
					
				case 2:
					listProjects();
					break;
					
				case 3:
					selectProject();
					
				default:
					System.out.println("\n" + selection +  "  is not valid selection. Try again.");
					break;
				
				}
			}
		catch (Exception e) {
			System.out.println("\nError: " + e + ". Try again");
			e.printStackTrace();
		}
		}
		
	}
	
	/*Method will list project IDs and names so that the user can select a project
	 * ID. Once the ID is entered, the service is called to return the project details
	 * */
	private void selectProject() {
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to select a project");
		
		/*Unselecting the current project*/
		curProject = null;
		
		/*This part will throw an exception if an invalid project is selected
		 * */
		curProject = projectService.fetchProjectById(projectId);
	}

	/*Creating a variable to hold a list of projects and assigning it to results of a method
	 * */
	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		System.out.println("\nProjects:");
		
		/*for each project this will print the ID and name separated by " :"
		 * indenting each line with a couple of spaces 
		 * */
		for (Project project:projects) {System.out.
		println("  " + project.getProjectId() + ": " + project.getProjectName());
		}	
		
	}

	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the  project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
		
	}

	private BigDecimal getDecimalInput(String prompt) {
		
		String input = getStringInput(prompt);
		
		if (Objects.isNull(input)) {
			return null;
		}
	
		try {
			/*Create the BigDecimal object and set it to two decimal places*/
			return new BigDecimal (input).setScale(2);
		}
	catch (NumberFormatException e) {
		throw new DbException(input + " is not a valid decimal number.");	
		
	}	
	}

	private boolean exitmenu() {
		System.out.println("Exiting the menu");
		return true;
	}

	/*creating the method getUserSekection() that takes no parameters and return int
	 * */
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput ("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input ;
	}

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if (Objects.isNull(input)) {
			return null;
		}
	
		try {
			
			return Integer.valueOf(input);
		}
	catch (NumberFormatException e) {
		throw new DbException(input + " is not a valid number.");
	}
		
	}

	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();
		
		if (input.trim ().isBlank()){
			return null;
		}
		return input.trim();
	}

	/*creating a method printOperations() that takes no parameters and return nothing 
	 * */
	private void printOperations() {
		
	System.out.println("\nThese are avilable selection. Press the enter key to quit:");
	
	/*print all the available menu selections, one on each line using lambda
	 *  expression
	 * */
	for (String line : operations) {
		System.out.println(" " + line);
		
		if (Objects.isNull(curProject)) {System.out.println("\nYou are not working with a project.");
			
		}
		else {
			System.out.println("\nYou are working with project: " + curProject);
		}
	}
	
	
		
	}
	
	
	
			
	}