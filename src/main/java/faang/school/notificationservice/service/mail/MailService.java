package faang.school.notificationservice.service.mail;

import faang.school.notificationservice.dto.UserDto;
import faang.school.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailService implements NotificationService {

    private final JavaMailSender emailSender;
    private final Environment env;

    @Override
    public void send(UserDto user, String message) {
        //TODO: i have to get user email
        String senderMail = env.getProperty("spring.mail.sender.email");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(senderMail);
        mail.setTo("gutorov.k@mail.ru");
        mail.setSubject("subject");
        mail.setText(message)   ;
        emailSender.send(mail);
    }

    @Override
    public UserDto.PreferredContact getPreferredContact() {
        return UserDto.PreferredContact.EMAIL;
    }
}
