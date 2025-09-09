package com.github.sinorang.nutrimo.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@Entity
@Table(name = "user_role",
        indexes = @Index(name = "idx_user_role_role", columnList = "role_id"))
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY) @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role_role"))
    private Role role;
}
