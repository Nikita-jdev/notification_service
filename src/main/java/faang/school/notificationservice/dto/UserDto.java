package faang.school.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String phone;
    private long telegramId;
    private PreferredContact preference;

    public enum PreferredContact {
        EMAIL, SMS, TELEGRAM
    }
}