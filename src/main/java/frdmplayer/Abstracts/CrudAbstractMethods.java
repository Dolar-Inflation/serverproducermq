package frdmplayer.Abstracts;

import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.MapEntity.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public abstract class CrudAbstractMethods<E,D> {
    protected final DtoMappinStrategy<E,D> mappingStrategy;

    protected CrudAbstractMethods(DtoMappinStrategy<E,D> mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
    }

    public final void deleteById(Integer id){
        if(!existsId(id)){
            throw new EntityNotFoundException("такого id нет в данной таблице");
        }
        performDelete(id);


    }
    public final List<D> readAll(){

     return performReadAll().stream()
                .map(mappingStrategy::toDto)
//                .forEach(System.out::println);
                .toList();



    }
    public final D readById(Integer id){
        E entity = performReadById(id)
                .orElseThrow(()-> new EntityNotFoundException("id not found"));
                return mappingStrategy.toDto(entity);
    }
    public final D create(D dto){
        E entity = mappingStrategy.toEntity(dto);
        E saved = performCreate(entity);
        return mappingStrategy.toDto(saved);
    }

    public final D patch(Integer id,D dto){
        if(!existsId(id)){
            throw new EntityNotFoundException("id not found");
        }
        E entity = performPatch(id, dto);
        return mappingStrategy.toDto(entity);
    }

    protected abstract boolean existsId(Integer id);
    protected abstract void performDelete(Integer id);
    protected abstract List<E> performReadAll();
    protected abstract Optional<E> performReadById(Integer id);
    protected abstract E performCreate(E entity);
    protected abstract E performPatch(Integer id, D dto);
}
