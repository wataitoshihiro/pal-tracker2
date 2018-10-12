package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private TimeEntryRepository timeEntryRepository;
    private final int MAX_TIME_ENTRIS = 5;

    @Autowired
    public TimeEntryHealthIndicator(TimeEntryRepository timeEntriesRepository) {
        this.timeEntryRepository = timeEntriesRepository;
    }

    @Override
    public Health health() {
        int size;
        Builder builder = new Health.Builder();

        size = timeEntryRepository.list().size();

        if (size < MAX_TIME_ENTRIS) {
            builder.up();
        } else {
            builder.down();
        }
        return builder.build();
    }
}
