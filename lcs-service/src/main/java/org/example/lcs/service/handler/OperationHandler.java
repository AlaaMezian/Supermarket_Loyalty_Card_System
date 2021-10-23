package org.example.lcs.service.handler;

import org.example.lcs.common.requests.OperationRequest;
import org.example.lcs.common.responses.OperationResponse;
import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.Transaction;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.CashierJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public abstract class OperationHandler<REQUEST extends OperationRequest, Response extends OperationResponse> {

    @Autowired
    private CashierJPARepository cashierJPARepository;

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;


    public Response handle(REQUEST request) {

        Optional<Cashier> optionalCashier = cashierJPARepository.findById(request.getCashierId());
        Cashier cashier = optionalCashier.orElseThrow(() -> new EntityNotFoundException("cashier with the id " + request.getCashierId() + " not found in the system"));
        if (!userAccountJPARepository.existsByMobileOrIdCardNumber(request.getMobileNumber(), request.getIdCardNumber())) {
            throw new EntityNotFoundException("user Account does not exist");
        }
        UserAccount userAccount = findUserAccount(request);
        Transaction transaction = doTransaction(request, cashier, userAccount);
        return buildResponse(transaction);
    }


    protected abstract Transaction doTransaction(REQUEST request, Cashier cashier, UserAccount userAccount);

    protected abstract Response buildResponse(Transaction transaction);

    private UserAccount findUserAccount(REQUEST request) {
        if (request.getIdCardNumber() != null && !request.getIdCardNumber().isEmpty()) {
            return userAccountJPARepository.findByIdCardNumber(request.getIdCardNumber()).orElseThrow(() -> new EntityNotFoundException("user Account does not exist"));
        } else {
            return userAccountJPARepository.findByMobile(request.getMobileNumber()).orElseThrow(() -> new EntityNotFoundException("user Account does not exist"));
        }
    }

}
