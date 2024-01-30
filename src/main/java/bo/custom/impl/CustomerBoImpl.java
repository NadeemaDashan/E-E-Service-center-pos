package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(dto.getId(), dto.getName(),
        dto.getContactNumber(),dto.getEmail()));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(dto.getId(), dto.getName(),
                dto.getContactNumber(), dto.getEmail()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customersAll = customerDao.getAll();
        List<CustomerDto> customersDtoList= new ArrayList<>();
        for(Customer customer:customersAll){
            CustomerDto customerDto = new CustomerDto(customer.getId()
                    ,customer.getName()
                    ,customer.getContactNumber()
                    ,customer.getEmail());
            customersDtoList.add(customerDto);
        }
        return customersDtoList;
    }
}
