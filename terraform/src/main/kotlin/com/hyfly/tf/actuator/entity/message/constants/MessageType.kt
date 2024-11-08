package com.hyfly.tf.actuator.entity.message.constants

object MessageType {
    // Generic messages
    const val VERSION: String = "version"

    const val LOG: String = "log"

    const val DIAGNOSTIC: String = "diagnostic"

    // Operation results
    const val RESOURCE_DRIFT: String = "resource_drift"

    const val PLANNED_CHANGE: String = "planned_change"
    const val CHANGE_SUMMARY: String = "change_summary"

    const val OUTPUTS: String = "outputs"


    // Hook-driven messages
    const val APPLY_START: String = "apply_start"

    const val APPLY_PROGRESS: String = "apply_progress"

    const val APPLY_COMPLETE: String = "apply_complete"

    const val APPLY_ERRORED: String = "apply_errored"

    const val PROVISION_START: String = "provision_start"

    const val PROVISION_PROGRESS: String = "provision_progress"

    const val PROVISION_COMPLETE: String = "provision_complete"

    const val PROVISION_ERRORED: String = "provision_errored"

    const val REFRESH_START: String = "refresh_start"

    const val REFRESH_COMPLETE: String = "refresh_complete"

    // Test messages
    const val TEST_ABSTRACT: String = "test_abstract"

    const val TEST_FILE: String = "test_file"

    const val TEST_RUN: String = "test_run"

    const val TEST_PLAN: String = "test_plan"

    const val TEST_STATE: String = "test_state"

    const val TEST_SUMMARY: String = "test_summary"

    const val TEST_CLEANUP: String = "test_cleanup"

    const val TEST_INTERRUPT: String = "test_interrupt"

    const val TEST_STATUS: String = "test_status"

    const val TEST_RETRY: String = "test_retry"

    // Additional message types for completeness
    const val INIT_START: String = "init_start"
    const val INIT_COMPLETE: String = "init_complete"
    const val PLAN_START: String = "plan_start"
    const val PLAN_COMPLETE: String = "plan_complete"
    const val DESTROY_START: String = "destroy_start"
    const val DESTROY_COMPLETE: String = "destroy_complete"
}
