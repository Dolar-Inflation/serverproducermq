package frdmplayer.MapEntity;

import frdmplayer.Interfaces.DtoMappinStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Mapper {
    private final List<DtoMappinStrategy<?,?>> strategies;

    public Mapper(List<DtoMappinStrategy<?,?>> strategies) {
        this.strategies = strategies;
    }
   public <E,D> DtoMappinStrategy<E,D> map(Class<E> entityClass, Class<D> dtoClass) {
        return (DtoMappinStrategy<E,D>) strategies.stream()
                .filter(s ->s.supports(entityClass) && s.getDtoClass().equals(dtoClass))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find strategy for " + entityClass.getName()));

   }

}
