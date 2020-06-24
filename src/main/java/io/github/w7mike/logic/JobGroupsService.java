package io.github.w7mike.logic;


import io.github.w7mike.model.JobGroups;
import io.github.w7mike.model.JobGroupsRepository;
import io.github.w7mike.model.JobRepository;
import io.github.w7mike.model.projection.GroupReadModel;
import io.github.w7mike.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequestScope
public class JobGroupsService {

    private JobGroupsRepository jobGroupsRepository;
    private JobRepository jobRepository;

    public JobGroupsService(final JobGroupsRepository jobGroupsRepository, final JobRepository jobRepository) {
        this.jobGroupsRepository = jobGroupsRepository;
        this.jobRepository = jobRepository;
    }

    public GroupReadModel createGroup(final GroupWriteModel source){
        JobGroups result = jobGroupsRepository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public Set<GroupReadModel> readAll(){
        return jobGroupsRepository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toSet());
    }

    public void toggleGroup(Integer groupId){
        if (jobRepository.existsByCompleteIsFalseAndJobGroups_Id(groupId)){
            throw new IllegalStateException("Group contains uncompleted jobs, please complete all jobs first");
        }

        JobGroups result = jobGroupsRepository.findById(groupId)
                .orElseThrow(()-> new IllegalArgumentException("Group with given id does not exists"));
        result.setComplete(!result.isComplete());
        jobGroupsRepository.save(result);
    }
}
