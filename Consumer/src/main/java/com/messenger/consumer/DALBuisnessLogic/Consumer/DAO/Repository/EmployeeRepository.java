package com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Repository;



import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}


