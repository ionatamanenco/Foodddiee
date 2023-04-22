package ia.ffy.foodforyou.payment_data;

import ia.ffy.foodforyou.login.security.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentData {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String card;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String cVV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_payment_info_id")
    private User userPaymentInfo;

}
