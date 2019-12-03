package com.lihao.mockito;

import javax.servlet.http.HttpServletRequest;

public class AccountLoginController {

    private final AccountDao accountDao;

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request) {

        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        try {

            Account account = accountDao.findAccount(username, password);
            if (account == null) {
                return "login.jsp";
            } else {
                return "/index.jsp";
            }
        } catch (Exception e) {

            return "/500";
        }
    }

}
