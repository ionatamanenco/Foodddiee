package payment_data;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AllArgsConstructor
class PaymentDataControllerIntegrationTest {

    private MockMvc mockMvc;

    @Test
    void testGetAllPaymentsData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentsData")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    @Test
    void testGetPaymentData() throws Exception {
        String id = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentsData/" + id)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void testCreatePaymentData() throws Exception {
        String id = UUID.randomUUID().toString();
        String requestBody = "{ \"id\": \"" + id + "\", \"amount\": 100.00 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentsData")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testUpdatePaymentData() throws Exception {
        String id = UUID.randomUUID().toString();
        String requestBody = "{ \"id\": \"" + id + "\", \"amount\": 200.00 }";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/paymentsData/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeletePaymentData() throws Exception {
        String id = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/paymentsData/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
