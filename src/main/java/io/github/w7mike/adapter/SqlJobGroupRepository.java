package io.github.w7mike.adapter;

import io.github.w7mike.model.JobGroup;
import io.github.w7mike.model.JobGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlJobGroupRepository extends JobGroupRepository, JpaRepository<JobGroup, Integer> {

    @Override
    @Query("select distinct g from JobGroup g join fetch g.jobs")
    List<JobGroup> findAll();

    @Override
    boolean existsByCompleteIsFalseAndProject_Id(Integer projectId);
}
