package frdmplayer.services;

import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckRelation {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployesphoneRepository emphoneRepository;

    @Autowired
    PhoneRepository phoneRepository;

//    public checkDbRelation(EmployePhoneDTO empPhoneDTO) throws Exception {
//        if(empPhoneDTO.getEmployeId()==null ){
//            throw new Exception("employee id is null or employee missing");
//        }
//    }
}
