package config;

import static de.blog.config.ClockConfig.DEFAULT_CLOCK;
import static de.blog.config.ClockConfig.DEFAULT_ZONE_ID;
import static org.mockito.Mockito.when;

import java.time.Clock;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.internal.util.MockUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class BeforeEachHook implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {

        ApplicationContext applicationContext = getApplicationContext(context);

        final Clock clock = applicationContext.getBean(Clock.class);

        if (MockUtil.isMock(clock)) {
            when(clock.getZone()).thenReturn(DEFAULT_ZONE_ID);
            when(clock.instant()).thenReturn(DEFAULT_CLOCK.instant());
        }

    }

    static ApplicationContext getApplicationContext(final ExtensionContext context) {
        return SpringExtension.getApplicationContext(context);
    }
}
