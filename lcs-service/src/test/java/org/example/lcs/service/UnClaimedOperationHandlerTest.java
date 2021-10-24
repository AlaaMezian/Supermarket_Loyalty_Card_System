package org.example.lcs.service;

import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.responses.UnClaimedUsersAccounts;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.example.lcs.service.handler.UnClaimedUserAccountHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UnClaimedOperationHandlerTest {

    @InjectMocks
    UnClaimedUserAccountHandler unClaimedUserAccountHandler = new UnClaimedUserAccountHandler();

    @Mock
    private UserAccountJPARepository userAccountJPARepository;

    @Test
    public void givenValidGetUnClaimedAccount_whenUnClaimedAccountHandlerCalled_thenReturnSuccessResponseWithNoUnclaimedAccountFoundMessage() {
        Mockito.when(userAccountJPARepository.findAllUnclaimedAccounts()).thenReturn(new ArrayList<>());
        UnClaimedUsersAccounts unClaimedUsersAccounts = unClaimedUserAccountHandler.getUnClaimedPositiveUserAccounts();
        assertNotNull(unClaimedUsersAccounts.getMessage());
        assertEquals("No UnClaimed Users Account found", unClaimedUsersAccounts.getMessage());
        assertEquals(unClaimedUsersAccounts.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    public void givenValidGetUnClaimedAccount_whenUnClaimedAccountHandlerCalledAndThereIsUnClaimedAccounts_thenReturnSuccessResponse() {
        Mockito.when(userAccountJPARepository.findAllUnclaimedAccounts()).thenReturn(buildUserAccount());
        UnClaimedUsersAccounts unClaimedUsersAccounts = unClaimedUserAccountHandler.getUnClaimedPositiveUserAccounts();
        assertNotNull(unClaimedUsersAccounts.getMessage());
        assertEquals(1, unClaimedUsersAccounts.getUsersAccount().size());
        assertEquals("User Accounts Fetched Successfully", unClaimedUsersAccounts.getMessage());
        assertEquals(unClaimedUsersAccounts.getStatus(), ResponseStatus.SUCCESS);
    }

    private List<UserAccount> buildUserAccount() {
        List<UserAccount> userAccounts = new ArrayList<>();
        UserAccount userAccount = new UserAccount(1L, "TEST", "testSurName", "07962315974", "123", new ArrayList<>(), 10f);
        userAccounts.add(userAccount);
        return userAccounts;
    }

}
