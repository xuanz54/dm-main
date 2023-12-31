package sut.edu.zyp.dormitory.manage.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "admin")
public class AdminEntity extends sut.edu.zyp.dormitory.manage.entity.AbstractBaseEntity implements Serializable {

    /**
     * 管理员账号
     */
    @Column(unique = true, length = 32, nullable = false)
    private String name;

    /**
     * 管理员密码
     */
    @Column(length = 32, nullable = false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
