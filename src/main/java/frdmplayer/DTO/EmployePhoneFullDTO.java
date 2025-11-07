package frdmplayer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployePhoneFullDTO {
        private Integer id;

    private Integer employeId;
    private String fio;
    private String address;
    private String position;

    private Integer phoneId;
    private String number;
    private String type;


}
