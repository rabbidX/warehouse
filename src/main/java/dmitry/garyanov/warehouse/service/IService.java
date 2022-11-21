package dmitry.garyanov.warehouse.service;

import dmitry.garyanov.warehouse.model.IEntity;

import java.util.List;
import java.util.Optional;

public interface IService <T extends IEntity> {
    List<T> getAll();
    T getById(long id);

}
