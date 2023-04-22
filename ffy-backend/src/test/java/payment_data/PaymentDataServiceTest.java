package payment_data;

import ia.ffy.foodforyou.login.security.user.UserRepository;
import ia.ffy.foodforyou.payment_data.PaymentData;
import ia.ffy.foodforyou.payment_data.PaymentDataDTO;
import ia.ffy.foodforyou.payment_data.PaymentDataRepository;
import ia.ffy.foodforyou.payment_data.PaymentDataService;
import ia.ffy.foodforyou.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentDataServiceTest {

    @Mock
    private PaymentDataRepository paymentDataRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PaymentDataService paymentDataService;

    @Test
    void findAll_GivenMultiplePaymentData_ExpectListOfPaymentDataDTOs() {
        // Given
        List<PaymentData> paymentsData = new ArrayList<>();
        paymentsData.add(new PaymentData("1", "1111111111111111", "John Doe", LocalDate.parse("12/25", DateTimeFormatter.ofPattern("MM/dd")), "123", null));
        paymentsData.add(new PaymentData("2", "2222222222222222", "Jane Smith", LocalDate.parse("11/23", DateTimeFormatter.ofPattern("MM/dd")), "456", null));
        when(paymentDataRepository.findAll((Example<PaymentData>) any())).thenReturn(paymentsData);

        // When
        List<PaymentDataDTO> paymentsDataDTO = paymentDataService.findAll();

        // Then
        assertEquals(2, paymentsDataDTO.size());
        assertEquals("1", paymentsDataDTO.get(0).getId());
        assertEquals("1111111111111111", paymentsDataDTO.get(0).getCard());
        assertEquals("John Doe", paymentsDataDTO.get(0).getName());
        assertEquals(LocalDate.parse("12/25", DateTimeFormatter.ofPattern("MM/dd")), paymentsDataDTO.get(0).getExpirationDate());
        assertEquals("123", paymentsDataDTO.get(0).getCVV());
        assertNull(paymentsDataDTO.get(0).getUserPaymentInfo());
        assertEquals("2", paymentsDataDTO.get(1).getId());
        assertEquals("2222222222222222", paymentsDataDTO.get(1).getCard());
        assertEquals("Jane Smith", paymentsDataDTO.get(1).getName());
        assertEquals(LocalDate.parse("11/23", DateTimeFormatter.ofPattern("MM/dd")), paymentsDataDTO.get(1).getExpirationDate());
        assertEquals("456", paymentsDataDTO.get(1).getCVV());
        assertNull(paymentsDataDTO.get(1).getUserPaymentInfo());
    }

    @Test
    void get_GivenExistingId_ExpectMatchingPaymentDataDTO() {
        // Given
        String id = "1";
        PaymentData paymentData = new PaymentData(id, "1111111111111111", "John Doe",
                                                  LocalDate.parse("12/25", DateTimeFormatter.ofPattern("MM/dd")), "123", null);
        when(paymentDataRepository.findById(eq(id))).thenReturn(Optional.of(paymentData));

        // When
        PaymentDataDTO paymentDataDTO = paymentDataService.get(id);

        // Then
        assertEquals(id, paymentDataDTO.getId());
        assertEquals("1111111111111111", paymentDataDTO.getCard());
        assertEquals("John Doe", paymentDataDTO.getName());
        assertEquals(LocalDate.parse("12/25", DateTimeFormatter.ofPattern("MM/dd"))
                , paymentDataDTO.getExpirationDate());
        assertEquals("123", paymentDataDTO.getCVV());
        assertNull(paymentDataDTO.getUserPaymentInfo());
    }

    @Test
    void get_GivenNonExistingId_ExpectNotFoundException() {
        // Given
        String id = "1";
        when(paymentDataRepository.findById(eq(id))).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NotFoundException.class, () -> paymentDataService.get(id));
    }
}