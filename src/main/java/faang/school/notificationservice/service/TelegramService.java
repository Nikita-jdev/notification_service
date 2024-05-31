package faang.school.notificationservice.service;

import faang.school.notificationservice.config.telegram.TelegramBot;
import faang.school.notificationservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramBot telegramBot;

    public void send(UserDto user, String messageText) {
        telegramBot.send(user, messageText);
    }
}
