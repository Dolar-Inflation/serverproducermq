package com.messenger.consumer.DALBuisnessLogic.Consumer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String fio;
    private String address;
    private String position;
}
