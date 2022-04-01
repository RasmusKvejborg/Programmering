package Opgave_2_og_3.application.controller;

import Opgave_2_og_3.application.model.Company;
import Opgave_2_og_3.application.model.Customer;
import Opgave_2_og_3.application.model.Employee;
import Opgave_2_og_3.storage.Storage;

import java.util.ArrayList;

public class Controller {


 // har lavet 2 constructorer, så det er muligt at oprette en customer med og uden company
    public static Customer createCustomer(String name){
        Customer customer = new Customer(name);
        Storage.addCustomer(customer);
        return customer;
    }

    public static Customer createCustomer(String name, Company company){
        Customer customer = new Customer(name);
        Storage.addCustomer(customer);
        company.addCustomer(customer);  //havde glemt at bruge parametret company. Husk når der er noget gråt, så er det ikke brugt...
        return customer;
    }

    // forskellige metoder
    public static void addCustomerToCompany(Customer customer, Company company){ //har ingen remove inden i funktionen, for customer kan godt have flere virksomheder at shoppe i.
        company.addCustomer(customer);
    }
    public static void removeCustomerFromCompany(Customer customer, Company company){
        if (company != null) { //tjekker vel bare på om firmaet findes... underligt man ikke tjekker om kunden er tilknyttet.
            company.removeCustomer(customer);
        }
    }
    public static ArrayList<Customer> getCustomers() {
        return Storage.getCustomers();
    }



    /**
     * Creates a new Company.<br />
     * Requires: hours >= 0.
     */
    public static Company createCompany(String name, int hours) {
        Company company = new Company(name, hours);
        Storage.addCompany(company);
        return company;
    }

    /**
     * Deletes the company.<br />
     * Requires: The company has no employees.
     */
    public static void deleteCompany(Company company) {
        Storage.removeCompany(company);
    }

    /**
     * Updates the company.<br />
     * Requires: hours >= 0.
     */
    public static void updateCompany(Company company, String name, int hours) {
        company.setName(name);
        company.setHours(hours);
    }

    /**
     * Get all the companies
     */
    public static ArrayList<Company> getCompanies() {
        return Storage.getCompanies();
    }

    // -------------------------------------------------------------------------

    /**
     * Creates a new employee.<br />
     * Requires: wage >= 0.
     */
    public static Employee createEmployee(String name, int wage, int employementYear) {
        Employee employee = new Employee(name, wage, employementYear);
        Storage.addEmployee(employee);
        return employee;
    }

    /**
     * Creates a new employee.<br />
     * Requires: wage >= 0, company!=null.
     */
    public static Employee createEmployee(String name, int wage, Company company, int employementYear) {
        Employee employee = createEmployee(name, wage, employementYear);
        company.addEmployee(employee);
        return employee;
    }

    /**
     * Deletes the employee.
     */
    public static void deleteEmployee(Employee employee) {
        Company company = employee.getCompany();
        if (company != null) {
            company.removeEmployee(employee);
        }
        Storage.removeEmployee(employee);
    }

    /**
     * Updates the employee.<br />
     * Requires: wage >= 0.
     */
    public static void updateEmployee(Employee employee, String name, int wage, int employmentYear) {
        employee.setName(name);
        employee.setWage(wage);
        employee.setEmployementYear(employmentYear);
    }
    
    /**
     * Adds the employee to the company. Removes the employee from the old company if present.
     */
    public static void addEmployeeToCompany(Employee employee, Company company) {
        company.addEmployee(employee);
    }
    
    /**
     * Removes the employee from the old company if not null.
     * @param company The old company. Can be null.
     * @param employee The employee to remove.
     */
    public static void removeEmployeeFromCompany(Employee employee, Company company) {
        if (company != null) {
            company.removeEmployee(employee);            
        }
    }

    /**
     * Get all the employees.
     */
    public static ArrayList<Employee> getEmployees() {
        return Storage.getEmployees();
    }

    // -------------------------------------------------------------------------

    /**
     * Initializes the storage with some objects.
     */
    public static void initStorage() {
        Company c1 = Controller.createCompany("IBM", 37);
        Company c2 = Controller.createCompany("AMD", 40);
        Controller.createCompany("SLED", 45);
        Controller.createCompany("Vector", 32);

        Controller.createEmployee("Bob Dole", 210, c2,2020);
        Controller.createEmployee("Alice Schmidt", 250, c1,2022);
        Controller.createEmployee("George Down", 150, c2,2021);

        Controller.createEmployee("Rita Uphill", 300,2019);

        Controller.createCustomer("Kunde-Hans",c1);
        Controller.createCustomer("Kunde-knud",c2);

        Customer cust3 = Controller.createCustomer("Børge");

        addCustomerToCompany(cust3,c2);

    }

    public static void init() {

        initStorage();

    }

}
