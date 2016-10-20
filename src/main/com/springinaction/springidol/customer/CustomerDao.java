package main.com.springinaction.springidol.customer;

import org.springframework.stereotype.Repository;

/**
 * Created by SBTJavastudent on 20.10.2016.
 */

@Repository
public interface CustomerDao {
    public String getNameById(long id);
}


