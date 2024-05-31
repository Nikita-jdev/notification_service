package faang.school.notificationservice.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.FollowerEvent;
import faang.school.notificationservice.dto.UserDto;
import faang.school.notificationservice.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FollowerEventListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private final UserServiceClient userServiceClient;
    private final TelegramService telegramService;
    private final MessageSource messageSource;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        FollowerEvent event;
        try {
            event = objectMapper.readValue(message.getBody(), FollowerEvent.class);
            log.info("Do json from event, {}", message);
        } catch (IOException e) {
            log.error("Not possible to make an event from json, {}", message);
            throw new RuntimeException(e);
        }
        UserDto user = userServiceClient.getUser(event.getFolloweeId());
        String text = user.getUsername() + " " + messageSource.getMessage(
                "follower.new", null, null);
        try {
            telegramService.send(user, text);
        } catch (Exception e) {
            log.error("Error sending message {}", text);
            throw new IllegalStateException(e);
        }
    }
}
