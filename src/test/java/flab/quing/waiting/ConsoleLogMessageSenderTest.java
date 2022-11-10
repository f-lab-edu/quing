package flab.quing.waiting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
class ConsoleLogMessageSenderTest {

    MessageSender messageSender;

    @BeforeEach
    void beforeEach() {
        messageSender = new ConsoleLogMessageSender();
    }

    @Test
    void send(CapturedOutput output) {
        messageSender.send("010-0000-0000", "hi test");

        Assertions.assertThat(output).contains("test");
    }
}