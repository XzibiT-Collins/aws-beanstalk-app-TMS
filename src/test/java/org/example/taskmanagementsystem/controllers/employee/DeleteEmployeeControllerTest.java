package org.example.taskmanagementsystem.controllers.employee;

import org.example.taskmanagementsystem.dao.EmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteEmployeeControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private EmployeeDao employeeDao;

    private DeleteEmployeeController deleteEmployeeController;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a testable version of the controller with a mock EmployeeDao
        deleteEmployeeController = new TestableDeleteEmployeeController(employeeDao);

        // Setup common request dispatcher mock
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet_WithValidEmployeeId_DeletesEmployeeAndForwardsToAddEmployee() throws ServletException, IOException, SQLException {
        // Arrange
        when(request.getParameter("employeeId")).thenReturn("42");
        when(request.getRequestDispatcher("/addEmployee")).thenReturn(requestDispatcher);

        // Act
        deleteEmployeeController.doGet(request, response);

        // Assert
        verify(employeeDao).deleteEmployee(42);
        verify(request).getRequestDispatcher("/addEmployee");
        verify(requestDispatcher).forward(request, response);
        verify(request, never()).setAttribute(eq("errorMessage"), anyString());
    }

    @Test
    public void testDoGet_WithBlankEmployeeId_ForwardsToErrorPage() throws ServletException, IOException, SQLException {
        // Arrange
        when(request.getParameter("employeeId")).thenReturn("");
        when(request.getRequestDispatcher("view/templates/errors/error.jsp")).thenReturn(requestDispatcher);

        // Act
        deleteEmployeeController.doGet(request, response);

        // Assert
        verify(request).setAttribute("errorMessage", "Employee id is required");
        verify(request).getRequestDispatcher("view/templates/errors/error.jsp");
        verify(requestDispatcher).forward(request, response);
        verify(employeeDao, never()).deleteEmployee(anyInt());
    }

    @Test
    public void testDoGet_WithZeroEmployeeId_ForwardsToErrorPage() throws ServletException, IOException, SQLException {
        // Arrange
        when(request.getParameter("employeeId")).thenReturn("0");
        when(request.getRequestDispatcher("views/templates/errors/error.jsp")).thenReturn(requestDispatcher);

        // Act
        deleteEmployeeController.doGet(request, response);

        // Assert
        verify(request).setAttribute("errorMessage", "Employee id must be greater than 0");
        verify(request).getRequestDispatcher("views/templates/errors/error.jsp");
        verify(requestDispatcher).forward(request, response);
        verify(employeeDao, never()).deleteEmployee(anyInt());
    }

    @Test
    public void testDoGet_WithNegativeEmployeeId_ForwardsToErrorPage() throws ServletException, IOException, SQLException {
        // Arrange
        when(request.getParameter("employeeId")).thenReturn("-5");
        when(request.getRequestDispatcher("views/templates/errors/error.jsp")).thenReturn(requestDispatcher);

        // Act
        deleteEmployeeController.doGet(request, response);

        // Assert
        verify(request).setAttribute("errorMessage", "Employee id must be greater than 0");
        verify(request).getRequestDispatcher("views/templates/errors/error.jsp");
        verify(requestDispatcher).forward(request, response);
        verify(employeeDao, never()).deleteEmployee(anyInt());
    }

    @Test
    public void testDoGet_WithNonNumericEmployeeId_ThrowsNumberFormatException() throws ServletException, IOException, SQLException {
        // Arrange
        when(request.getParameter("employeeId")).thenReturn("abc");

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> {
            deleteEmployeeController.doGet(request, response);
        });

        verify(employeeDao, never()).deleteEmployee(anyInt());
    }

//    @Test
//    public void testDoGet_WhenDeleteEmployeeThrowsSQLException_ForwardsToErrorPage() throws ServletException, IOException, SQLException {
//        // Arrange
//        when(request.getParameter("employeeId")).thenReturn("42");
//        when(request.getRequestDispatcher("view/templates/errors/error.jsp")).thenReturn(requestDispatcher);
//        doThrow(new SQLException("Database error")).when(employeeDao).deleteEmployee(42);
//
//        // Act
//        deleteEmployeeController.doGet(request, response);
//
//        // Assert
//        verify(employeeDao).deleteEmployee(42);
//        verify(request).setAttribute("errorMessage", "Error deleting employee");
//        verify(request).getRequestDispatcher("view/templates/errors/error.jsp");
//        verify(requestDispatcher).forward(request, response);
//    }

    /**
     * A testable version of DeleteEmployeeController that allows for dependency injection of EmployeeDao
     */
    private static class TestableDeleteEmployeeController extends DeleteEmployeeController {
        private final EmployeeDao injectedEmployeeDao;

        public TestableDeleteEmployeeController(EmployeeDao employeeDao) {
            this.injectedEmployeeDao = employeeDao;
        }

        @Override
        protected EmployeeDao createEmployeeDao() {
            return injectedEmployeeDao;
        }
    }
}