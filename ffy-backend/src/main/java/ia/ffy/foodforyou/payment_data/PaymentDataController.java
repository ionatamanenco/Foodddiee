package ia.ffy.foodforyou.payment_data;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/paymentsData")
public class PaymentDataController {

    private final PaymentDataService paymentDataService;

    public PaymentDataController(final PaymentDataService paymentDataService) {
        this.paymentDataService = paymentDataService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDataDTO>> getAllPaymentsData() {
        return ResponseEntity.ok(paymentDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDataDTO> getPaymentData(@PathVariable final String id) {
        return ResponseEntity.ok(paymentDataService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createPaymentData(
            @RequestBody @Valid final PaymentDataDTO paymentDataDTO) {
        paymentDataService.create(paymentDataDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaymentData(@PathVariable final String id,
                                                  @RequestBody @Valid final PaymentDataDTO paymentDataDTO) {
        paymentDataService.update(id, paymentDataDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentData(@PathVariable final String id) {
        paymentDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
