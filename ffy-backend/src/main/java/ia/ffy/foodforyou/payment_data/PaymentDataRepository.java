package ia.ffy.foodforyou.payment_data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataRepository extends JpaRepository<PaymentData, String> {

    boolean existsByIdIgnoreCase(String id);

    boolean existsByCardIgnoreCase(String card);

}
