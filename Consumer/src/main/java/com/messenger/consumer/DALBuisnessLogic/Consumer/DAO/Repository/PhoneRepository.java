package com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Repository;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {
}
