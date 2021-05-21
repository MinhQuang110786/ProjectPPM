package com.heaven.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name required")
    private String projectName;

    @NotBlank(message = "Project identifier required")
    @Size(min=4,max=5,message="Please use 4-5 character")
    @Column(updatable = false,unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Project description required")
    private String description;

    @JsonFormat(pattern="yyyy-mm-dd")
    @Column(updatable = false)
    private Date startDate;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date endDate;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date update_At;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

}
