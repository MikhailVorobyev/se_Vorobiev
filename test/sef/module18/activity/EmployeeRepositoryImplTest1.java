package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class EmployeeRepositoryImplTest1 extends TestCase {

    private Connection conn;
    private String url;
    private String username;
    private String password;
    Log logger = LogFactory.getLog(this.getClass());

    public void setUp() throws Exception {
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
    }

    public void testFindEmployeeByID() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            Employee result = repository.findEmployeeByID(1);
            assertEquals("john", result.getFirstName().toLowerCase());
            assertEquals("doe", result.getLastName().toLowerCase());
            assertEquals(1, result.getProfLevel());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindEmployeeByName() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> result = repository.findEmployeeByName("Scott", "Feist");
            assertEquals(1, result.size());
            assertEquals("scott", result.get(0).getFirstName().toLowerCase());
            assertEquals("feist", result.get(0).getLastName().toLowerCase());
            assertEquals(1, result.get(0).getProfLevel());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindEmployeeByProfLevel() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> result = repository.findEmployeeByProfLevel(1);
            assertEquals(2, result.size());
            assertEquals("john", result.get(0).getFirstName().toLowerCase());
            assertEquals("doe", result.get(0).getLastName().toLowerCase());
            assertEquals(1, result.get(0).getProfLevel());

            assertEquals("scott", result.get(1).getFirstName().toLowerCase());
            assertEquals("feist", result.get(1).getLastName().toLowerCase());
            assertEquals(1, result.get(1).getProfLevel());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindEmployeeByProject() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> result = repository.findEmployeeByProject(3);
            assertEquals(2, result.size());
            assertEquals("jane", result.get(0).getFirstName().toLowerCase());
            assertEquals("doe", result.get(0).getLastName().toLowerCase());
            assertEquals(2, result.get(0).getProfLevel());

            assertEquals("james", result.get(1).getFirstName().toLowerCase());
            assertEquals("donnell", result.get(1).getLastName().toLowerCase());
            assertEquals(3, result.get(1).getProfLevel());
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testInsertEmployee() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            Employee employee = new EmployeeImpl(0, "New firstname", "New lastname", 6);
            int employeeId = repository.insertEmployee(employee);
            assertEquals(employee.getEmployeeID(), employeeId);
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testUpdateEmployee() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(conn);
        try {
            boolean result = repository.updateEmployee(
                    new EmployeeImpl(5, "Updated firstname", "Updated lastname", 6));
            assertTrue(result);
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void tearDown() throws Exception {
        try{
            super.tearDown();
            conn.close();
            System.out.println("Connection closed!");
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            logger.error(sef.module.percentage.Percentage.setFailedCount(true,6));
        }
    }
}