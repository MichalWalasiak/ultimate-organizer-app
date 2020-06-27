package io.github.w7mike.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;

    @NotBlank(message = "project specification must be not null")
    private String specification;

    @OneToMany(mappedBy = "project")
    private Set<JobGroups> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectSteps> steps;

    public Project() {
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

    Set<JobGroups> getGroups() {
        return groups;
    }

    public void setGroups(final Set<JobGroups> jobs) {
        this.groups = jobs;
    }

    public Set<ProjectSteps> getSteps() {
        return steps;
    }

    void setSteps(final Set<ProjectSteps> steps) {
        this.steps = steps;
    }
}
