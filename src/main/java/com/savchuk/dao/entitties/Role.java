package com.savchuk.dao.entitties;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "role")
@EnableJpaRepositories
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "role_native")
    @GenericGenerator(name = "role_native", strategy = "native")
    @Column(name = "role_id", nullable = false, unique = true, updatable = false)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, unique = true, updatable = false)
    private RoleType type;

    @Override
    public String getAuthority() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        if (id != 0 && role.id != 0)
            return id == role.id;
        return type == role.type;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (id != 0)
            return Objects.hash(id);
        return Objects.hash(type);
    }

    public RoleType getType() {
        return this.type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public enum RoleType implements IBaseEnumValueText {
        ROLE_FREE(1, "Бесплатный", "/FREE"),
        ROLE_STANDART(2, "Стандарт", "/STANDART");

        private final int value;
        private final String text;
        private final String homeUrl;

        RoleType(int value, String text, String homeUrl) {
            this.value = value;
            this.text = text;
            this.homeUrl = homeUrl;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getDescription() {
            return text;
        }

        public String getHomeUrl() {
            return homeUrl;
        }

        @Override
        public String toString() {
            if(this.value == 1)
                return "admin";
            else return "user";
        }
    }
}
