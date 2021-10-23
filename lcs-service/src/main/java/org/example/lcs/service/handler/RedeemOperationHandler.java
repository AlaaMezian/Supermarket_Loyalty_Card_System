package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.enums.TransactionEntryType;
import org.example.lcs.common.exceptions.InSufficienttBalanceException;
import org.example.lcs.common.requests.RedeemRequest;
import org.example.lcs.common.responses.RedeemResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.TransactionsJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedeemOperationHandler extends OperationHandler<RedeemRequest, RedeemResponse> {

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    @Autowired
    private TransactionsJPARepository transactionsJPARepository;

    @Override
    protected Transaction doTransaction(RedeemRequest request, Cashier cashier, UserAccount userAccount) {

        Integer pointsToRedeem = request.getPointToRedeem();
        if (request.getNumberOfWaterPackets() > 0) {
            pointsToRedeem = (request.getNumberOfWaterPackets() * 100) + request.getPointToRedeem();
        }

        if (userAccount.getTotalPointAmount() < pointsToRedeem) {
            throw new InSufficienttBalanceException("Unable to redeem points Insufficient balance");
        }

        Transaction transaction = Transaction.builder()
                .userAccount(userAccount)
                .cashier(cashier)
                .transactionEntryType(TransactionEntryType.REDEEM)
                .pointsAmount(pointsToRedeem)
                .build();
        transaction = transactionsJPARepository.save(transaction);
        userAccount.getTransactionList().add(transaction);
        userAccount.setTotalPointAmount(userAccount.getTotalPointAmount() - transaction.getPointsAmount());
        userAccountJPARepository.save(userAccount);
        return transaction;
    }

    @Override
    protected RedeemResponse buildResponse(Transaction transaction) {
        return RedeemResponse.builder()
                .pointsRedeemed(String.valueOf(transaction.getPointsAmount()))
                .transactionDate(transaction.getCreatedDate())
                .message("Points Redeemed Successfully")
                .status(ResponseStatus.SUCCESS)
                .build();
    }
}
