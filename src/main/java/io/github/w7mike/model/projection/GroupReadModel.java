package io.github.w7mike.model.projection;

import io.github.w7mike.model.Job;
import io.github.w7mike.model.JobGroup;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {

    private Integer id;
    private String specification;

    /**
     *deadline from the latest job in group
     */
    private LocalDateTime deadline;
    private Set<GroupJobReadModel> jobs;

    public GroupReadModel(JobGroup source){
        id = source.getId();
        specification = source.getSpecification();
            source.getJobs().stream()
                    .map(Job::getDeadline)
                    .filter(Objects::nonNull)
                    .max(LocalDateTime::compareTo)
                    .ifPresent(date -> deadline = date);

            jobs = source.getJobs()
                    .stream()
                    .map(GroupJobReadModel::new)
                    .collect(Collectors.toSet());

    }

    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(final String specification) {
        this.specification = specification;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Set<GroupJobReadModel> getJobs() {
        return jobs;
    }

    void setJobs(final Set<GroupJobReadModel> jobs) {
        this.jobs = jobs;
    }
}
