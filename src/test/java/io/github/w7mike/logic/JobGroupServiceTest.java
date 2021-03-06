package io.github.w7mike.logic;

import io.github.w7mike.model.JobGroup;
import io.github.w7mike.model.JobGroupRepository;
import io.github.w7mike.model.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JobGroupServiceTest {

    @Test
    @DisplayName("Should Throw IllegalStateException when incomplete jobs exists")
    void toggleGroup_uncompletedJobs_throwsIllegalStateException(){
        //given
        JobRepository mockJobRepository = jobRepositoryReturns(true);

        var toTest = new JobGroupService(null, mockJobRepository);
        //when
        var exception = catchThrowable(()-> toTest.toggleGroup(1));

        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("contains uncompleted jobs");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when group with given id do not exists")
    void toggleGroup_noGroups_throwsIllegalArgumentException(){
        //given
        var mockJobGroupRepository = mock(JobGroupRepository.class);
        when(mockJobGroupRepository.findById(anyInt())).thenReturn(Optional.empty());

        JobRepository mockJobRepository = jobRepositoryReturns(false);

        var toTest = new JobGroupService(mockJobGroupRepository, mockJobRepository);

        //when
        var exception = catchThrowable(()-> toTest.toggleGroup(1));

        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id does not exists");
    }

    @Test
    @DisplayName("Should change group status to incomplete")
    void toggleGroup_groupsComplete_groupExists_changeToIncomplete(){
        //given
        JobRepository mockJobRepository = jobRepositoryReturns(false);
        var group = new JobGroup();
        var beforeToggle = group.isComplete();

        var mockJobGroupRepository  = mock(JobGroupRepository.class);
        when(mockJobGroupRepository.findById(anyInt())).thenReturn(Optional.of(group));

        var toTest = new JobGroupService(mockJobGroupRepository, mockJobRepository);

        //when
        toTest.toggleGroup(0);

        //then
        assertThat(group.isComplete()).isEqualTo(!beforeToggle);
        assertThat(group.isComplete()).isNotEqualTo(beforeToggle);
    }

    private JobRepository jobRepositoryReturns(final boolean outcome) {
        var mockJobRepository = mock(JobRepository.class);
        when(mockJobRepository.existsByCompleteIsFalseAndJobGroup_Id(anyInt())).thenReturn(outcome);
        return mockJobRepository;
    }
}
