package main.com.springinaction.springidol.customer;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("JdbsCustomer")
public class CustomerDaoJdbs implements CustomerDao {

    @Override
    public String getNameById(long id) {
        return "Universal name";
    }
}
