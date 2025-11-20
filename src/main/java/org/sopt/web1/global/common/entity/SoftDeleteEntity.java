package org.sopt.web1.global.common.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class SoftDeleteEntity extends BaseTimeEntity {

    /**
     * @SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
     * @SQLRestriction("deleted_at IS NULL")
     *
     * 도메인에서 사용시 위 어노테이션 추가
     * -> 1. Repository의 delete() 메소드가 호출될 때, 진짜 DELETE 쿼리 대신 이 UPDATE 쿼리 날림
     * -> 2. findAll, findById 등 모든 조회 쿼리에 자동으로 AND deleted_at IS NULL 조건을 붙여줌 (삭제된 건 안 가져오게 함)
     */
    private LocalDateTime deletedAt;

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted(){
        return this.deletedAt != null;
    }
}
