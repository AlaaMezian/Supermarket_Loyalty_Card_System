package org.example.lcs.service;

import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.exceptions.InvalidPurchaseAmount;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.CashierJPARepository;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.example.lcs.service.handler.PurchaseOperationHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class PurchaseOperationTest {

    @InjectMocks
    PurchaseOperationHandler purchaseOperationHandler = new PurchaseOperationHandler();

    @Mock
    private UserAccountJPARepository userAccountJPARepository;

    @Mock
    private TransactionsJPARepository transactionsJPARepository;

    @Mock
    private CashierJPARepository cashierJPARepository;

    PurchaseRequest purchaseRequest = new PurchaseRequest();

    @Before
    public void setUp() {
        mocks();
        purchaseRequest.setCashierId(1l);
        purchaseRequest.setMobileNumber("07962315974");
        purchaseRequest.setPurchaseAmount(new BigDecimal(50));
    }

    @Test
    public void givenValidPurchaseRequest_whenPurchaseOperationCalled_thenPurchaseResponseReturnedSuccessfully() {
        PurchaseResponse purchaseResponse = purchaseOperationHandler.handle(purchaseRequest);
        assertNotNull(purchaseResponse);
        assertNotNull(purchaseResponse.getMessage());
        assertNotNull(purchaseResponse.getPointsEarned());
        assertNotNull(purchaseResponse.getStatus());
        assertEquals(purchaseResponse.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(purchaseResponse.getPointsEarned(), "10.0");
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidCashierId_whenPurchaseOperationCalled_thenEntityNotFoundExceptionThrown() {
        purchaseRequest.setCashierId(50L);
        purchaseOperationHandler.handle(purchaseRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidMobileNumber_whenPurchaseOperationCalled_thenEntityNotFoundExceptionThrown() {
        purchaseRequest.setMobileNumber("123");
        purchaseOperationHandler.handle(purchaseRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidIdCardNumber_whenPurchaseOperationCalled_thenEntityNotFoundExceptionThrown() {
        purchaseRequest.setIdCardNumber("123");
        purchaseOperationHandler.handle(purchaseRequest);
    }

    //this test case won't happen because in request dto we have @Min validating the amount, but I added extra validation in service layer
    @Test(expected = InvalidPurchaseAmount.class)
    public void givenInvalidPurchaseAmount_whenPurchaseOperationCalled_InvalidPurchaseAmountThrown() {
        purchaseRequest.setPurchaseAmount(new BigDecimal(-1));
        purchaseOperationHandler.handle(purchaseRequest);
    }

    private void mocks() {
        Mockito.when(cashierJPARepository.findById(1l))
                .thenReturn(java.util.Optional.of(buildCashier()));
        Mockito.when(userAccountJPARepository.existsByMobileOrIdCardNumber("07962315974", null))
                .thenReturn(true);
        Mockito.when(userAccountJPARepository.findByMobile("07962315974"))
                .thenReturn(java.util.Optional.of(buildUserAccount()));

        Mockito.when(transactionsJPARepository.save(Mockito.any(Transaction.class)))
                .thenReturn(buildTransaction());
    }

    private Transaction buildTransaction() {
        return Transaction.builder()
                .userAccount(new UserAccount(1L, "TEST", "testSurName", "07962315974", "123", new ArrayList<>(), 10f))
                .cashier(new Cashier(1l, "test"))
                .transactionEntryType(TransactionEntryType.EARN)
                .pointsAmount(10f)
                .build();
    }

    private Cashier buildCashier() {
        return new Cashier(1l, "test");
    }

    private UserAccount buildUserAccount() {
        return new UserAccount(1L, "TEST", "testSurName", "07962315974", "123", new ArrayList<>(), 10f);
    }
}
