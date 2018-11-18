package be.ucll.da.dentravak.repository;

import be.ucll.da.dentravak.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    Order findByDate(LocalDateTime date);
}
