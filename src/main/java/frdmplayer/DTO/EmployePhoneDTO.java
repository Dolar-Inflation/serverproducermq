package frdmplayer.DTO;

import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Phone;



public class EmployePhoneDTO {
    private Integer id;
    private Integer employeId;
    private Integer phoneId;

    public EmployePhoneDTO() {}

    public EmployePhoneDTO( Integer id,Integer employeId, Integer phoneId) {
        this.employeId = employeId;
        this.phoneId = phoneId;
        this.id = id;
    }
public Integer getId() {
        return id;
}

public void setId(Integer id) {
        this.id = id;
}
public Integer getEmployeId() {
        return employeId;
}
public void setEmployeeId(Integer employeeId) {
        this.employeId = employeId;
}
public Integer getPhoneId() {
        return phoneId;
}
public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
}







//    public Integer getEmployeeId() {
//        return employeId;
//    }
//
//    public void setEmployeId(Integer employeId) {
//        this.employeId = employeId;
//    }
//
//    public Integer getPhoneId() {
//        return phoneId;
//    }
//
//    public void setPhoneId(Integer phoneId) {
//        this.phoneId = phoneId;
//    }
//    public Integer getId() {
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
}





//}

