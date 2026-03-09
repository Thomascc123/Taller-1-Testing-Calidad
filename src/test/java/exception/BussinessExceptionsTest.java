package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.bank.credit.exception.CustomerNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Custom Bussiness Exceptions Tests")
public class BussinessExceptionsTest {

    @Test
    @DisplayName("Customer Not Found Exception Test")
    public void customerNotFoundExceptionTest(){

        CustomerNotFoundException customerNotFoundException = new CustomerNotFoundException();

        assertNotNull(customerNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, customerNotFoundException.getStatus());
        assertEquals("CUSTOMER_NOT_FOUND", customerNotFoundException.getCode());
        assertEquals("Cliente no encontrado", customerNotFoundException.getMessage());
    }
}
