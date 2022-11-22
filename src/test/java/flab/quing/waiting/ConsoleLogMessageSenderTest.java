package flab.quing.waiting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
class ConsoleLogMessageSenderTest {

    private final MessageSender messageSender = new ConsoleLogMessageSender();

    @Test
    void send(CapturedOutput output) {
        messageSender.send("010-0000-0000", "hi test");

        Assertions.assertThat(output).contains("test");
    }
}
