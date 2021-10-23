package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private float calculatePointsEarned(BigDecimal purchaseAmount) {
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
}
