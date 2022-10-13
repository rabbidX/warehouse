package dmitry.garyanov.warehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class AbstractDocument implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
