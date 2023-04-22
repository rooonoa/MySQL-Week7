/**
 * 
 */
package projects;

import projects.dao.DbConnection;

/**
 * This is our main method that will call the DbConnection.getConnection() method to see if we can gain connection to the MySQL server  
 *
 */
public class ProjectsApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		DbConnection.getConnection();

	}

}
