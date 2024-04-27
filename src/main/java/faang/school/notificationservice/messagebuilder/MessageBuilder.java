package faang.school.notificationservice.messagebuilder;

import java.util.Locale;

public interface MessageBuilder<T> {

    String buildMessage(T event, Locale locale);

    Class<?> getEventType();
}
