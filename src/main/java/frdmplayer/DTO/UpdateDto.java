package frdmplayer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO переделать этот КОСТЫЛЬ ЕБАНЫЙ
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private Integer id;

    private Integer employeeId;
    private String fio;
    private String address;
    private String position;

    private Integer phoneId;
    private String number;
    private String type;


}
