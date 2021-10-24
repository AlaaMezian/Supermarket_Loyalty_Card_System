package org.example.lcs.repository.loader;

import org.example.lcs.repository.entity.Cashier;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.CashierJPARepository;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CashierJPARepository cashierJPARepository;

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeData();
    }

    private void initializeData() {
        initializeCashiers();
        initializeUserAccounts();
    }

    private void initializeCashiers() {
        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cashier cashier = Cashier.builder()
                    .location("test " + i)
                    .build();
            cashiers.add(cashier);
        }
        cashierJPARepository.saveAll(cashiers);
    }

    private void initializeUserAccounts() {
        List<UserAccount> userAccounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserAccount userAccount = UserAccount.builder()
                    .name("test" + i)
                    .mobile(String.valueOf((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L))
                    .surName("test " + i)
                    .idCardNumber(UUID.randomUUID().toString())
                    .build();
            userAccounts.add(userAccount);
        }

        UserAccount userAccount = UserAccount.builder()
                .name("test" + 60)
                .mobile("07962315974")
                .surName("test " + 60)
                .idCardNumber(UUID.randomUUID().toString())
                .build();
        userAccounts.add(userAccount);
    
        userAccountJPARepository.saveAll(userAccounts);
    }
}
