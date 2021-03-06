package be.ucll.da.dentravak.repository;

import be.ucll.da.dentravak.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {

}
