package ia.ffy.foodforyou.sitting_tables;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SittingTablesService {

    private final SittingTablesRepository sittingTablesRepository;
    private final RestaurantRepository restaurantRepository;

    public SittingTablesService(final SittingTablesRepository sittingTablesRepository,
                                final RestaurantRepository restaurantRepository) {
        this.sittingTablesRepository = sittingTablesRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<SittingTablesDTO> findAll() {
        final List<SittingTables> sittingTables = sittingTablesRepository.findAll(Sort.by("id"));
        return sittingTables.stream()
                .map(sittingTable -> mapToDTO(sittingTable, new SittingTablesDTO()))
                .toList();
    }

    public SittingTablesDTO get(final String id) {
        return sittingTablesRepository.findById(id)
                .map(sittingTables -> mapToDTO(sittingTables, new SittingTablesDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final SittingTablesDTO sittingTablesDTO) {
        final SittingTables sittingTables = new SittingTables();
        mapToEntity(sittingTablesDTO, sittingTables);
        sittingTables.setId(sittingTablesDTO.getId());
        return sittingTablesRepository.save(sittingTables).getId();
    }

    public void update(final String id, final SittingTablesDTO sittingTablesDTO) {
        final SittingTables sittingTables = sittingTablesRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sittingTablesDTO, sittingTables);
        sittingTablesRepository.save(sittingTables);
    }

    public void delete(final String id) {
        sittingTablesRepository.deleteById(id);
    }

    private SittingTablesDTO mapToDTO(final SittingTables sittingTables,
                                      final SittingTablesDTO sittingTablesDTO) {
        sittingTablesDTO.setId(sittingTables.getId());
        sittingTablesDTO.setNumber(sittingTables.getNumber());
        sittingTablesDTO.setQrCode(sittingTables.getQrCode());
        sittingTablesDTO.setRestaurantTables(sittingTables.getRestaurantTables() == null ? null : sittingTables.getRestaurantTables().getId());
        return sittingTablesDTO;
    }

    private SittingTables mapToEntity(final SittingTablesDTO sittingTablesDTO,
                                      final SittingTables sittingTables) {
        sittingTables.setNumber(sittingTablesDTO.getNumber());
        sittingTables.setQrCode(sittingTablesDTO.getQrCode());
        final Restaurant restaurantTables = sittingTablesDTO.getRestaurantTables() == null ? null : restaurantRepository.findById(sittingTablesDTO.getRestaurantTables())
                .orElseThrow(() -> new NotFoundException("Restaurant Tables not found!"));
        sittingTables.setRestaurantTables(restaurantTables);
        return sittingTables;
    }

    public boolean idExists(final String id) {
        return sittingTablesRepository.existsByIdIgnoreCase(id);
    }

    public boolean numberExists(final Integer number) {
        return sittingTablesRepository.existsByNumber(number);
    }

    public boolean qrCodeExists(final String qrCode) {
        return sittingTablesRepository.existsByQrCodeIgnoreCase(qrCode);
    }

}
