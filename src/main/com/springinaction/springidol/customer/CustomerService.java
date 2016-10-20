package main.com.springinaction.springidol.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("SbtService")
public class CustomerService {

    //@Qualifier("CustomerDaoJdbs")
    private CustomerDao customerDao;

    //@Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public String getNameById(long id) {
        return customerDao.getNameById(id);
    }
}
