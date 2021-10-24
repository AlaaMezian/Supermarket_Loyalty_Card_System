package org.example.lcs.service;

import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.exceptions.InSufficienttBalanceException;
import org.example.lcs.common.requests.RedeemRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.common.responses.RedeemResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.CashierJPARepository;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.example.lcs.service.handler.RedeemOperationHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class RedeemOperationTest {

    @InjectMocks
    RedeemOperationHandler redeemPurchaseHandler = new RedeemOperationHandler();

    @Mock
    private UserAccountJPARepository userAccountJPARepository;

    @Mock
    private TransactionsJPARepository transactionsJPARepository;

    @Mock
    private CashierJPARepository cashierJPARepository;

    RedeemRequest redeemRequest = new RedeemRequest();

    @Before
    public void setUp() {
        mocks();
        redeemRequest.setCashierId(1l);
        redeemRequest.setMobileNumber("07962315974");
    }


    @Test
    public void givenValidRedeemRequestWithValidPointsToRedeem_whenPurchaseOperationCalled_thenPurchaseResponseReturnedSuccessfully() {
        redeemRequest.setPointToRedeem(100);
        RedeemResponse redeemResponse = redeemPurchaseHandler.handle(redeemRequest);
        assertNotNull(redeemResponse);
        assertNotNull(redeemResponse.getMessage());
        assertNotNull(redeemResponse.getPointsRedeemed());
        assertNotNull(redeemResponse.getStatus());
        assertEquals(redeemResponse.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(redeemResponse.getPointsRedeemed(), "100.0");
    }

    @Test
    public void givenValidRedeemRequestWithValidNumberOfWaterPacks_whenPurchaseOperationCalled_thenPurchaseResponseReturnedSuccessfully() {
        redeemRequest.setNumberOfWaterPackets(1);
        RedeemResponse redeemResponse = redeemPurchaseHandler.handle(redeemRequest);
        assertNotNull(redeemResponse);
        assertNotNull(redeemResponse.getMessage());
        assertNotNull(redeemResponse.getPointsRedeemed());
        assertNotNull(redeemResponse.getStatus());
        assertEquals(redeemResponse.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(redeemResponse.getPointsRedeemed(), "100.0");
    }

    @Test(expected = InSufficienttBalanceException.class)
    public void givenInvalidPointsToRedeem_whenRedeemCalled_thenInSufficientBalanceExceptionThrown(){
        redeemRequest.setPointToRedeem(200);
        redeemPurchaseHandler.handle(redeemRequest);
    }

    @Test(expected = InSufficienttBalanceException.class)
    public void givenInvalidWaterBottleNumberWithInSufficientBalance_whenRedeemCalled_thenInSufficientBalanceExceptionThrown(){
        redeemRequest.setNumberOfWaterPackets(2);
        redeemPurchaseHandler.handle(redeemRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidCashierId_whenRedeemOperationCalled_thenEntityNotFoundExceptionThrown() {
        redeemRequest.setCashierId(50L);
        redeemPurchaseHandler.handle(redeemRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidMobileNumber_whenRedeemOperationCalled_thenEntityNotFoundExceptionThrown() {
        redeemRequest.setMobileNumber("123");
        redeemPurchaseHandler.handle(redeemRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidIdCardNumber_whenRedeemOperationCalled_thenEntityNotFoundExceptionThrown() {
        redeemRequest.setIdCardNumber("123");
        redeemPurchaseHandler.handle(redeemRequest);
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
                .userAccount(new UserAccount(1L, "TEST", "testSurName", "07962315974", "123", new ArrayList<>(), 100f))
                .cashier(new Cashier(1l, "test"))
                .transactionEntryType(TransactionEntryType.EARN)
                .pointsAmount(100f)
                .build();
    }

    private Cashier buildCashier() {
        return new Cashier(1l, "test");
    }

    private UserAccount buildUserAccount() {
        return new UserAccount(1L, "TEST", "testSurName", "07962315974", "123", new ArrayList<>(), 100f);
    }

}
