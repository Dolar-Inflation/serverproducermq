package com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces;

public interface DtoMappinStrategy <E,D>{
    D toDto(E e);
    E toEntity(D d);
    boolean supports(Class<?> dtoClass);
    Class<E> getEntityClass();
    Class<D> getDtoClass();
}
