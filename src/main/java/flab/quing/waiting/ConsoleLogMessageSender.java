package flab.quing.waiting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@Service
public class ConsoleLogMessageSender implements MessageSender {

    @Override
    public boolean send(String phoneNumber, String message) {
        log.info("phoneNumber = " + phoneNumber);
        log.info("message = " + message);
        return true;
    }
}
