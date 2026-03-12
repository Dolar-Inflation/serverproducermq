package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Consumer;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Repository.PhoneRepository;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.PhoneNumberDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;
import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;

import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.LockTable.LockTableService;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper.PhoneMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhoneDTOConsumerStrategy implements KafkaConsumerStrategy {





    private final PhoneMapper phoneMapper;
    private final LockTableService lockTableService;
    private final PhoneRepository phoneRepository;


    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka) {
            case CREATE ->{
                lockTableService.lockTable("phone");
                try {
                    PhoneNumberDTO phoneNumberDTO = (PhoneNumberDTO) obj;
                    Phone phone = phoneMapper.PhoneDTOToPhone(phoneNumberDTO);
                    phoneRepository.save(phone);

                } finally {
                    lockTableService.unlockTable("phone");
                }
            }
            case DELETE ->{
                lockTableService.lockTable("phone");
                try {
                    PhoneNumberDTO phoneNumberDTO = (PhoneNumberDTO) obj;
                    Phone phone = phoneMapper.PhoneDTOToPhone(phoneNumberDTO);
                    phoneRepository.deleteById(phone.getId());

                    lockTableService.unlockTable("phone");
                }
                catch(EntityNotFoundException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
            case READALL -> {
                List<Phone> phoneList = phoneRepository.findAll();
                phoneMapper.mapPhoneNumberDTOList(phoneList);
                phoneList.forEach(System.out::println);



//                List<PhoneNumberDTO> phoneDTOList = phoneCrudTemplate.readAll();
//                phoneDTOList.forEach(System.out::println);

            }
            case PATCH -> {
                lockTableService.lockTable("phone");
                try {
                    PhoneNumberDTO phoneDTO = (PhoneNumberDTO) obj;
                    Phone phone = phoneRepository.findById(phoneDTO.getId()).get();
                    phoneMapper.updatePhoneFromDto(phoneDTO, phone);
                    phoneRepository.save(phone);
//                    phoneCrudTemplate.patch(phoneDTO.getId(), phoneDTO);
                    lockTableService.unlockTable("phone");
                }
                catch(EntityNotFoundException e) {
                    System.err.println("<UNK>: " + e.getMessage());
                }
            }

            case READ -> {
                try {


                    PhoneNumberDTO dto = (PhoneNumberDTO) obj;
                    Phone phone = phoneRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
                    phoneMapper.PhoneToPhoneNumberDTO(phone);
                    System.out.println("Из базы: " + phone);
                }
                catch (EntityNotFoundException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }

            }
        }
    }

    @Override
    public String getClassName() {
        return PhoneNumberDTO.class.getSimpleName();
    }


}

