package ws.cpcs.testCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.cpcs.testCar.domain.Trans;

@Repository
public interface TransactionsRepo  extends JpaRepository<Trans, Long> {
}
