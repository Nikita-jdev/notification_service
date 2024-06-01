package faang.school.notificationservice.service;

import faang.school.notificationservice.config.telegram.TelegramBotConfig;
import faang.school.notificationservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramBotConfig telegramBotConfig;

    public void send(UserDto user, String messageText) {
        telegramBotConfig.send(user, messageText);
    }
}
