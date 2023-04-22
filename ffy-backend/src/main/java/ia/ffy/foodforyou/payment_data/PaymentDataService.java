package ia.ffy.foodforyou.payment_data;

import ia.ffy.foodforyou.login.security.user.User;
import ia.ffy.foodforyou.login.security.user.UserRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDataService {

    private final PaymentDataRepository paymentDataRepository;
    private final UserRepository userRepository;

    public PaymentDataService(final PaymentDataRepository paymentDataRepository,
                              final UserRepository userRepository) {
        this.paymentDataRepository = paymentDataRepository;
        this.userRepository = userRepository;
    }

    public List<PaymentDataDTO> findAll() {
        final List<PaymentData> paymentsData = paymentDataRepository.findAll(Sort.by("id"));
        return paymentsData.stream()
                .map(paymentData -> mapToDTO(paymentData, new PaymentDataDTO()))
                .toList();
    }

    public PaymentDataDTO get(final String id) {
        return paymentDataRepository.findById(id)
                .map(paymentData -> mapToDTO(paymentData, new PaymentDataDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final PaymentDataDTO paymentDataDTO) {
        final PaymentData paymentData = new PaymentData();
        mapToEntity(paymentDataDTO, paymentData);
        paymentData.setId(paymentDataDTO.getId());
        return paymentDataRepository.save(paymentData).getId();
    }

    public void update(final String id, final PaymentDataDTO paymentDataDTO) {
        final PaymentData paymentData = paymentDataRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(paymentDataDTO, paymentData);
        paymentDataRepository.save(paymentData);
    }

    public void delete(final String id) {
        paymentDataRepository.deleteById(id);
    }

    private PaymentDataDTO mapToDTO(final PaymentData paymentData,
                                    final PaymentDataDTO paymentDataDTO) {
        paymentDataDTO.setId(paymentData.getId());
        paymentDataDTO.setCard(paymentData.getCard());
        paymentDataDTO.setName(paymentData.getName());
        paymentDataDTO.setExpirationDate(paymentData.getExpirationDate());
        paymentDataDTO.setCVV(paymentData.getCVV());
        paymentDataDTO.setUserPaymentInfo(paymentData.getUserPaymentInfo() == null ? null : paymentData.getUserPaymentInfo().getId());
        return paymentDataDTO;
    }

    private PaymentData mapToEntity(final PaymentDataDTO paymentDataDTO,
                                    final PaymentData paymentData) {
        paymentData.setCard(paymentDataDTO.getCard());
        paymentData.setName(paymentDataDTO.getName());
        paymentData.setExpirationDate(paymentDataDTO.getExpirationDate());
        paymentData.setCVV(paymentDataDTO.getCVV());
        final User userPaymentInfo = paymentDataDTO.getUserPaymentInfo() == null ? null : userRepository.findById(paymentDataDTO.getUserPaymentInfo())
                .orElseThrow(() -> new NotFoundException("User Payment Info not found!"));
        paymentData.setUserPaymentInfo(userPaymentInfo);
        return paymentData;
    }

    public boolean idExists(final String id) {
        return paymentDataRepository.existsByIdIgnoreCase(id);
    }

    public boolean cardExists(final String card) {
        return paymentDataRepository.existsByCardIgnoreCase(card);
    }

}
