package frdmplayer.CrudTemplate;

import frdmplayer.Abstracts.CrudAbstractMethods;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.MapEntity.EntityToDTO;
import frdmplayer.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeCrudTemplate extends CrudAbstractMethods<EmployeeDTO> {
    private final EmployeeRepository employeeRepository;
    private final EntityToDTO entityToDTO;


    @Override
    protected boolean existsId(Integer id) {
        return employeeRepository.existsById(id);
    }

    @Override
    protected void performDelete(Integer id) {
        employeeRepository.deleteById(id);
        System.out.println("Deleted Employee with id " + id);

    }

    @Override
    protected void performReadAll() {
        employeeRepository.findAll().forEach(System.out::println);
//        employeeRepository.findAll().stream().forEach(entityToDTO::convertToEmployeDTO)

    }
}
