package faang.school.notificationservice.config.telegram;

import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.TgContactDto;
import faang.school.notificationservice.dto.UserDto;
import faang.school.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot implements NotificationService {
    private final TelegramBotConfig telegramBotConfig;
    private final UserServiceClient userServiceClient;

    @Override
    public void send(UserDto user, String messageText) {
        try {
            TgContactDto tgContactDto = userServiceClient.getTgContact(user.getId());
            SendMessage message = new SendMessage();
            message.setChatId(tgContactDto.getChatId());
            message.setText(messageText);
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto.PreferredContact getPreferredContact() {
        return UserDto.PreferredContact.TELEGRAM;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getBotToken();
    }
}

