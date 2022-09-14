package flab.quing.user;

public interface UserService {

    User signUp(String name, String phone);

    User login(String name, String phone);

    User storeSignUp(String name, String pw);

    User storeLogin(String name, String pw);

}
