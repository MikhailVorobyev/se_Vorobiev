package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProjectRepositoryImplTest1 extends TestCase {

    private Connection conn;
    private String url;
    private String username;
    private String password;
    Log logger = LogFactory.getLog(this.getClass());

    protected void setUp() throws Exception {
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
    }

    public void testFindProjectByID() {
        ProjectRepository repository = new ProjectRepositoryImpl(conn);
        try {
            Project result = repository.findProjectByID(1);
            assertEquals("online insurance system", result.getProjectName().toLowerCase());
            assertEquals("a web application that automates insurance transactions.",
                    result.getProjectDescription().toLowerCase());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindProjectByName() {
        ProjectRepository repository = new ProjectRepositoryImpl(conn);
        try {
            List<Project> result = repository.findProjectByName("time report system");
            assertEquals(1, result.size());
            assertEquals("time report system", result.get(0).getProjectName().toLowerCase());
            assertEquals("a stand-alone application that records and generates time reports.",
                    result.get(0).getProjectDescription().toLowerCase());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindProjectByEmployee() {
        ProjectRepository repository = new ProjectRepositoryImpl(conn);
        try {
            List<Project> result = repository.findProjectByEmployee(4);
            assertEquals(1, result.size());
            assertEquals("real estate search system", result.get(0).getProjectName().toLowerCase());
            assertEquals("an online search engine specifically for real estates.",
                    result.get(0).getProjectDescription().toLowerCase());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }

    }

    public void testInsertProject() {
        ProjectRepository repository = new ProjectRepositoryImpl(conn);
        try {
            Project project = new ProjectImpl(0, "Project Name", "Project description");
            int projectId = repository.insertProject(project);
            assertEquals(project.getProjectID(), projectId);
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testUpdateProject() {
        ProjectRepository repository = new ProjectRepositoryImpl(conn);
        try {
            boolean result = repository.updateProject(new ProjectImpl(4, "New Project Name", "New description"));
            assertTrue(result);
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    protected void tearDown() throws Exception {
        try {
            super.tearDown();
            conn.close();
            System.out.println("Connection closed!");
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            logger.error(sef.module.percentage.Percentage.setFailedCount(true, 5));
        }
    }
}