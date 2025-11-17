package frdmplayer.Abstracts;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public abstract class CrudAbstractMethods<T> {
    public final void deleteById(Integer id){
        if(!existsId(id)){
            throw new EntityNotFoundException("такого id нет в данной таблице");
        }
        performDelete(id);


    }
    public final void readAll(){
        performReadAll();
    }

    protected abstract boolean existsId(Integer id);
    protected abstract void performDelete(Integer id);
    protected abstract void performReadAll();
}
