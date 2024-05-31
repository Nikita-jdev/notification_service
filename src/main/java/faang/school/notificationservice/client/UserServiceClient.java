package faang.school.notificationservice.client;

import faang.school.notificationservice.dto.TgContactDto;
import faang.school.notificationservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.host}" +
                                          ":${user-service.port}" +
                                          "/${user-service.version}")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    UserDto getUser(@PathVariable long userId);

    @GetMapping("/tgContact/{userId}")
    TgContactDto getTgContact(@PathVariable long userId);
}
