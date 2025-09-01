package org.example.project01lms.Services.CustomerServices;

import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Services.CRUDservies;

public interface CustomerService extends CRUDservies<CustomerDto  , Customers> {
    CustomerDto getCustomerByLibraryId(String libraryId);
}
