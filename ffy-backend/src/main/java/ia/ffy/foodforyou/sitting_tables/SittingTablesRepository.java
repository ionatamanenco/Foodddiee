package ia.ffy.foodforyou.sitting_tables;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SittingTablesRepository extends JpaRepository<SittingTables, String> {

    boolean existsByIdIgnoreCase(String id);

    boolean existsByNumber(Integer number);

    boolean existsByQrCodeIgnoreCase(String qrCode);

}
