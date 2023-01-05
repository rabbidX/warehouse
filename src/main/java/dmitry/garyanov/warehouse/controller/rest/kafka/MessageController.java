package dmitry.garyanov.warehouse.controller.rest.kafka;

import lombok.AllArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("message")
@AllArgsConstructor
public class MessageController {

   // private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void sendMessage(String messageId, String text) throws ExecutionException, InterruptedException {
       // CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(messageId, text);
       // SendResult<String, String> result = future.get();

    }
}
