package com.lihao;

import com.lihao.mockito.Account;
import com.lihao.mockito.AccountDao;
import com.lihao.mockito.AccountLoginController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    //    1. @Mock, do not need add @RunWith(MockitoJUnitRunner.class)
    //    1. @Mock(answer = Answer.RETURNS_DEEP_STATUS)
    private AccountDao accountDao;

    private HttpServletRequest request;

    private AccountLoginController accountLoginController;


//    1. @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Before
    public void setUp() {
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void testLoginSuccess() {

        Account account = new Account();

        Mockito.when(request.getParameter("username")).thenReturn("lihaoma");
        Mockito.when(request.getParameter("password")).thenReturn("2341213");
        Mockito.when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);

        // start to mockito test
        Assert.assertEquals(accountLoginController.login(request), "/index.jsp");
    }

    @Test
    public void testLoginFailure() {

        Account account = new Account();

        Mockito.when(request.getParameter("username")).thenReturn("eeeee");
        Mockito.when(request.getParameter("password")).thenReturn("2341213");
        Mockito.when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);

        // start to mockito test
        Assert.assertEquals(accountLoginController.login(request), "/index.jsp");
    }

    @Test
    public void testLoginException() {

        Account account = new Account();

        Mockito.when(request.getParameter("username")).thenReturn("ss");
        Mockito.when(request.getParameter("password")).thenReturn("2341213");
        Mockito.when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);

        // start to mockito test
        Assert.assertEquals(accountLoginController.login(request), "/index.jsp");
    }
}
