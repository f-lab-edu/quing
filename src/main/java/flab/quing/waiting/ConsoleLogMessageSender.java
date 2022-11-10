package flab.quing.waiting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsoleLogMessageSender implements MessageSender {

    @Override
    public boolean send(String phoneNumber, String message) {
        log.info("phoneNumber = " + phoneNumber);
        log.info("message = " + message);
        return true;
    }
}