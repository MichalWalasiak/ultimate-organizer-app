package io.github.w7mike.model.event;

import java.time.Instant;

public abstract class JobEvent {
    private Integer jobId;
    private Instant occurrence;

    public Integer getJobId() {
        return jobId;
    }

    public Instant getOccurrence() {
        return occurrence;
    }
}
