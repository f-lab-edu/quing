package flab.quing;

import flab.quing.store.Store;
import flab.quing.user.User;

public class DummyDataMaker {

    static User member() {
        User member = new User();
        return member;
    }

    static User member(String name) {
        User member = DummyDataMaker.member();
        return member;
    }

    static Store store() {
        Store store = new Store();
        return store;
    }

}
