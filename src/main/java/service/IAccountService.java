package service;

import entity.Account;

public interface IAccountService<E> {
    Account login(String name, String pass);

}
