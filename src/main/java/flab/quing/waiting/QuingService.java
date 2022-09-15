package flab.quing.waiting;

import flab.quing.store.Menu;
import flab.quing.store.Store;

import java.util.List;


public interface QuingService {
    //quingService
    //waiting
    //List<waitingQueue> ??
    //waitingItem
    //reponse, request


    //input class, optional field
    Waiting add(String phone, Store store);
    //append

    WaitingUser add(String phone, Store store, Menu menu);

    WaitingUser add(WaitingUser user, Store store);

    WaitingUser add(WaitingUser user, Store store, Menu menu);

    List<WaitingUser> getList(Store store);

    int countForward(WaitingUser waitingUser);

    void sendMessage(WaitingUser user, String msg);
//    void sendEnterMessage(){
//        sendMessage("들어오세요");
//    }

    void changeDone(WaitingUser user);

    void restoreWaiting(WaitingUser user);//과하다는 느낌

    void cancelWaiting(WaitingUser user);


}
