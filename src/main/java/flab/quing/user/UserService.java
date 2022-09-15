package flab.quing.user;

public interface UserService {

    User signUp(String name, String phone);

    User signIn(String name, String phone);

    StoreManager storeSignUp(String name, String password);

    StoreManager storeSignIn(String name, String password);

}
