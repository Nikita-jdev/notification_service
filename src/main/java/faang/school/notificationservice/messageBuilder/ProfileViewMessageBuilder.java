package faang.school.notificationservice.messageBuilder;

import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.event.EventDto;
import faang.school.notificationservice.dto.event.ProfileViewEventDto;
import faang.school.notificationservice.dto.user.UserDto;
import faang.school.notificationservice.dto.user.UserNameDto;
import faang.school.notificationservice.listener.ProfileViewListener;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProfileViewMessageBuilder implements MessageBuilder<ProfileViewEventDto, String>{
    private final UserServiceClient userServiceClient;

    @Override
    public String buildMessage(ProfileViewEventDto event, String locale) {
        Yaml yaml = new Yaml();
        ClassPathResource resource = new ClassPathResource("messages.yaml");

        try (InputStream inputStream = resource.getInputStream()) {
            Map<String, Map<String, String>> messagesConfig = yaml.load(inputStream);
            String eventMessagePattern = messagesConfig.get("profile").get("view");

            UserNameDto userOwner = userServiceClient.getUserName(event.getProfileOwnerId());
            UserNameDto userViewer = userServiceClient.getUserName(event.getViewerId());

            return String.format(eventMessagePattern, userOwner.getUsername(), userViewer.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}