package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class TestFourteenMocks {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Spy
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;



    @Test
    void should_CalculatedCorrectPrice() {

        try (MockedStatic<CurrencyConverter> converterMockedStatic = mockStatic(CurrencyConverter.class)) {

            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
                    LocalDate.of(2020, 1, 5), 2, true);

            double expected = 400.0;

            converterMockedStatic.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

            double actual = bookingService.calculatePriceEuro(bookingRequest);

            assertEquals(expected,actual);
        }





    }

}
