package com.getir.interview.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.getir.interview.readingisgood.TestHelper;
import com.getir.interview.readingisgood.dto.CreateCustomerRequest;
import com.getir.interview.readingisgood.model.Customer;
import com.getir.interview.readingisgood.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getById_givenCustomer() {
        Customer customer = TestHelper.createCustomer();
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        assertEquals(customerService.getById(any()), customer);
    }

    @Test
    void create_existingEmail_throwException() {
        CreateCustomerRequest createCustomerRequest = TestHelper.createCustomerRequestObject("testmail");
        Mockito.when(customerRepository.findByEmail(createCustomerRequest.getEmail())).thenReturn(Optional.of(TestHelper.createCustomer()));
        assertThrows(RuntimeException.class, () -> customerService.create(createCustomerRequest));
    }
    
    @Test
    void create_givenNewCustomer() {
        CreateCustomerRequest createCustomerRequest = TestHelper.createCustomerRequestObject("testmail");
        Mockito.when(customerRepository.findByEmail(createCustomerRequest.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.save(any())).thenReturn(TestHelper.createCustomer());
        assertEquals(customerService.create(createCustomerRequest).getEmail(), "testmail");
    }
}