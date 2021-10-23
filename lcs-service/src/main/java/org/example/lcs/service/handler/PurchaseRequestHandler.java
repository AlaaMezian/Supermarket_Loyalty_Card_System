package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.exceptions.DataBaseOperationException;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.CashierJPARepository;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class PurchaseRequestHandler {

    @Autowired
    private CashierJPARepository cashierJPARepository;

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    @Autowired
    private TransactionsJPARepository transactionsJPARepository;

    public PurchaseResponse handlePurchase(PurchaseRequest purchaseRequest) {
        Optional<Cashier> optionalCashier = cashierJPARepository.findById(purchaseRequest.getCashierId());
        Cashier cashier = optionalCashier.orElseThrow(() -> new EntityNotFoundException("cashier with the id " + purchaseRequest.getCashierId() + " not found in the system"));
        if (!userAccountJPARepository.existsByMobileOrIdCardNumber(purchaseRequest.getMobileNumber(), purchaseRequest.getIdCardNumber())) {
            throw new EntityNotFoundException("user Account does not exist");
        }
        try {
            Transaction transaction = doTransaction(purchaseRequest, cashier);
            return buildPurchaseResponse(transaction);

        } catch (DataAccessException e) {
            log.error("Failed to create Transaction", e);
            throw new DataBaseOperationException("failed to create transaction");
        }
    }

    private Transaction doTransaction(PurchaseRequest purchaseRequest, Cashier cashier) {
        UserAccount userAccount = findUserAccount(purchaseRequest);
        Transaction transaction = Transaction.builder()
                .userAccount(userAccount)
                .cashier(cashier)
                .transactionEntryType(TransactionEntryType.EARN)
                .pointsAmount(calculatePointsEarned(purchaseRequest.getPurchaseAmount()))
                .build();
        transaction = transactionsJPARepository.save(transaction);
        userAccount.getTransactionList().add(transaction);
        userAccount.setTotalPointAmount(userAccount.getTotalPointAmount() + transaction.getPointsAmount());
        userAccountJPARepository.save(userAccount);
        return transaction;
    }

    private UserAccount findUserAccount(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getIdCardNumber() != null && !purchaseRequest.getIdCardNumber().isEmpty()) {
            return userAccountJPARepository.findByIdCardNumber(purchaseRequest.getIdCardNumber()).orElseThrow(() -> new EntityNotFoundException("user Account does not exist"));
        } else {
            return userAccountJPARepository.findByMobile(purchaseRequest.getMobileNumber()).orElseThrow(() -> new EntityNotFoundException("user Account does not exist"));
        }
    }


    private float calculatePointsEarned(BigDecimal purchaseAmount) {
        return purchaseAmount.multiply(new BigDecimal(100)).multiply(new BigDecimal(0.002)).floatValue();
    }


    private PurchaseResponse buildPurchaseResponse(Transaction transaction) {
        return PurchaseResponse.builder()
                .pointsEarned(String.valueOf(transaction.getPointsAmount()))
                .transactionDate(transaction.getCreatedDate())
                .message("Transaction Created Successfully")
                .status(ResponseStatus.SUCCESS)
                .build();
    }
}
