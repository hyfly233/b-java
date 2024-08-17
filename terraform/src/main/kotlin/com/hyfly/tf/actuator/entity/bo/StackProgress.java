package com.hyfly.tf.actuator.entity.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StackProgress {

    private String stackId;

    private boolean isRollback;

    private boolean isCompleted;

    private Integer stackStatus;

    private Integer createdCount;

    private Integer toCreateCount;

    private Integer updatedCount;

    private Integer toUpdateCount;

    private Integer deletedCount;

    private Integer toDeleteCount;

    private Integer completedCount;

    private Integer total;

    public Integer getCompletedCount() {
        return this.createdCount + this.updatedCount + this.deletedCount;
    }

    public Integer getTotal() {
        return this.toCreateCount + this.toUpdateCount + this.toDeleteCount;
    }
}
