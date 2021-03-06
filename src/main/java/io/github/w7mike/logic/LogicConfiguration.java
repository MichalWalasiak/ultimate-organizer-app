package io.github.w7mike.logic;

import io.github.w7mike.JobConfigurationProperties;
import io.github.w7mike.model.JobGroupRepository;
import io.github.w7mike.model.JobRepository;
import io.github.w7mike.model.ProjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {

    @Bean
    public ProjectsService service(
            final ProjectRepository projectRepository,
            final JobGroupRepository groupsRepository,
            final JobGroupService service,
            final JobConfigurationProperties properties
    ){
        return new ProjectsService(projectRepository, groupsRepository, service, properties);
    }

    @Bean
    public JobGroupService jobGroupsService(
            final JobGroupRepository groupsRepository,
            final JobRepository jobRepository
    ){
        return new JobGroupService(groupsRepository, jobRepository);
    }
}
