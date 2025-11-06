package frdmplayer.Repository;

import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployesphoneRepository extends JpaRepository<Employeephonerelation, Integer> {
   List<Employeephonerelation> findAll();
   Employeephonerelation findById(int id);
}
