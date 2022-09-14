package flab.quing.waiting;

import flab.quing.store.Menu;
import flab.quing.store.Store;

public interface WaitingUserService {

    Long add(String phone, Store store);
    Long add(String phone, Store store, Menu menu);

    Long add(WaitingUser user, Store store);
    Long add(WaitingUser user, Store store, Menu menu);

    void sendJoinMessage(WaitingUser user);

    void doneWaiting(WaitingUser user);

    void restoreWaiting(WaitingUser user);

    void cancleWaiting(WaitingUser user);

    //수정


}
