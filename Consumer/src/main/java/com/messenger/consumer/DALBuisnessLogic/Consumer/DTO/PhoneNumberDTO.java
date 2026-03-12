package com.messenger.consumer.DALBuisnessLogic.Consumer.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDTO {
    Integer id;
    String number;
    String type;

}
