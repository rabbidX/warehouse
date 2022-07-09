package dmitry.garyanov.warehouse.service;

import dmitry.garyanov.warehouse.model.IEntity;

import java.util.List;

public interface IService <T extends IEntity> {
    List<T> getAll();

}
