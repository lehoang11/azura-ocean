package com.azura.common.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt"},
        allowGetters = true
)

public abstract class CreatedTimestampModel  {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at" , nullable = false,  updatable=false)
    @CreatedDate
    private Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}