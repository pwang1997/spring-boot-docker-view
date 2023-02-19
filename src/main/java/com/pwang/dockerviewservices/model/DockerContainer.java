package com.pwang.dockerviewservices.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
public class DockerContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docker_container_id")
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, columnDefinition="CHAR(36)")
    private String uuid;

    private String name;

    @ElementCollection // 1
    @CollectionTable(name = "port_list", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "ports") // 3
    private List<String> ports;

    private Long startAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createAt = System.currentTimeMillis();

    private Long updatedAt = System.currentTimeMillis();

    @Column(name="docker_image",unique = true, nullable = false)
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        DockerContainer that = (DockerContainer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
