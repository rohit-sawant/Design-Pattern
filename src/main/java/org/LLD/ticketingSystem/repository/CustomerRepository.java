package org.LLD.ticketingSystem.repository;

import org.LLD.ticketingSystem.entity.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getEmail(), customer);
    }

    public Customer findByEmail(String email) {
        return customers.get(email);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public void delete(String email) {
        customers.remove(email);
    }
}