package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.exceptions.DataBaseOperationException;
import org.example.lcs.common.exceptions.InvalidPurchaseAmount;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PurchaseOperationHandler extends OperationHandler<PurchaseRequest, PurchaseResponse> {

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    @Autowired
    private TransactionsJPARepository transactionsJPARepository;


    @Override
    protected Transaction doTransaction(PurchaseRequest purchaseRequest, Cashier cashier, UserAccount userAccount) {
        try {
            Transaction transaction = buildTransaction(purchaseRequest, cashier, userAccount);
            transaction = transactionsJPARepository.save(transaction);
            userAccount.getTransactionList().add(transaction);
            userAccount.setTotalPointAmount(userAccount.getTotalPointAmount() + transaction.getPointsAmount());
            userAccountJPARepository.save(userAccount);
            return transaction;
        } catch (DataAccessException e) {
            log.error("Failed to create UserAccount", e);
            throw new DataBaseOperationException("failed to save user account");
        }
    }

    private float calculatePointsEarned(BigDecimal purchaseAmount) {
        if(purchaseAmount.floatValue() < 1){
            throw new InvalidPurchaseAmount("Invalid purchase amount");
        }
        return purchaseAmount.multiply(new BigDecimal(100)).multiply(new BigDecimal(0.002)).floatValue();
    }

    @Override
    protected PurchaseResponse buildResponse(Transaction transaction) {
        return PurchaseResponse.builder()
                .pointsEarned(String.valueOf(transaction.getPointsAmount()))
                .transactionDate(transaction.getCreatedDate())
                .message("Transaction Created Successfully")
                .status(ResponseStatus.SUCCESS)
                .build();
    }

    private Transaction buildTransaction(PurchaseRequest purchaseRequest, Cashier cashier, UserAccount userAccount) {
        return Transaction.builder()
                .userAccount(userAccount)
                .cashier(cashier)
                .transactionEntryType(TransactionEntryType.EARN)
                .pointsAmount(calculatePointsEarned(purchaseRequest.getPurchaseAmount()))
                .build();
    }
}
