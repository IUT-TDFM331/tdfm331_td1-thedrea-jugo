package fr.iut.tdfm331.td1.service;

import fr.iut.tdfm331.td1.model.Employee;
import fr.iut.tdfm331.td1.model.Meeting;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test file to test ListApiService class
 */
public class ListApiServiceTest {

    private ListApiService service;

    @Before
    public void setupService() {
        service = new ListApiService();
    }
    /**
     * Test to check if list of Meeting is ∞correctly generated
     */
    @Test
    public void getListMeetingWithSuccess() {
        List<Meeting> listMeetings = service.getListMeetings();
        List<Meeting> expectedListMeetings = ListMeetingsGenerator.LIST_MEETINGS;
        assertThat(listMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListMeetings.toArray()));
    }

    /**
     * Test to check it list of Employee is correctly generated
     */
    @Test
    public void getListEmployeeWithSuccess() {
        List<Employee> listEmployees = service.getListEmployees();
        List<Employee> expectedListEmployees = ListEmployeesGenerator.LIST_EMPLOYEES;
        assertThat(listEmployees, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListEmployees.toArray()));

    }

    /**
     * Test to check if a new Meeting object is correctly added to the list
     */
    @Test
    public void addNewMeetingWithSuccess() {

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);
        Assert.assertTrue(service.getListMeetings().contains(newMeeting));
    }

    /**
     * Test to check if a selected Meeting is correctly removed from list
     */
    @Test
    public void removeMeetingWithSuccess() {
        // Get first Meeting from list
        Meeting meetingToRemove = service.getListMeetings().get(0);
        service.getListMeetings().remove(meetingToRemove);
        Assert.assertFalse(service.getListMeetings().contains(meetingToRemove));
    }

    @Test
    public void removeMeeting(){

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);

        service.removeMeeting(newMeeting);
        Assert.assertFalse(service.getListMeetings().contains(newMeeting));
    }

    @Test
    public void findByObjectExist() throws MeetingNotFound {
        //meeting qui existe

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);

        Assert.assertEquals(service.findByObject("Réunion d'avancement"),newMeeting);

    }

    @Test
    public void findByObjectNotExist() throws MeetingNotFound{
        //meeting meeting n'existe pas



    }

    @Test
    public void findByNameExist() throws EmployeeNotFound{
        //employé qui existe

        List<Employee> listEmployees = Arrays.asList(new Employee("Julien", "julien.didier@etu.unice.fr", 1),
                new Employee("Andrea", "andrea.larboulletmarin@etu.unice.fr", 2),
                new Employee("Hugo", "hugo.goncalves-silva@etu.unice.fr", 3),
                new Employee("Theo", "theo.ripoll@etu.unice.fr", 4));

        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Room",
                "12/11/21",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        service.addMeeting(newMeeting);

        Assert.assertNotEquals(service.findByName("Julien"), null);

    }
@Disabled
    @Test
    public void findByNameNotExist() throws EmployeeNotFound{
        //employé n'existe pas

        List<Employee> listEmployees = service.getListEmployees();

        Employee julien = new Employee("Julien", "julien.didier@etu.unice.fr", 1);
        Employee andrea = new Employee("Andrea", "andrea.larboulletmarin@etu.unice.fr", 2);
        Employee hugo = new Employee("Hugo", "hugo.goncalves-silva@etu.unice.fr", 3);
        Employee theo = new Employee("Theo", "theo.ripoll@etu.unice.fr", 4);

        listEmployees.add(1, andrea);
        listEmployees.add(2, hugo);
        listEmployees.add(3, theo);

        Assert.assertEquals(service.findByName("Andrea"), andrea);

        try {
            service.findByName("Julien");
            Assertions.fail("Employee not found");
        }
        catch(EmployeeNotFound e){
            Assert.assertEquals(null, e.getMessage());
        }
    }
}
