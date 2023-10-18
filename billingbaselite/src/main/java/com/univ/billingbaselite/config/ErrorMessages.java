package com.univ.billingbaselite.config;

public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String QRTZ_JOB_KEY_ALREADY_EXISTS = "Job with JobKey = '%s' already exists";
    public static final String QRTZ_JOB_RUN_CLASS_NOT_FOUND = "Failed to find class = %s";
    public static final String QRTZ_JOB_NOT_FOUND = "Job with id = '%s' not found";
    public static final String QRTZ_FAILED_TO_PAUSE_JOB = "Failed to pause job with id = '%s'";
    public static final String QRTZ_FAILED_TO_RESUME_JOB = "Failed to resume job with id = '%s'";
    public static final String QRTZ_FAILED_TO_START_JOB = "Failed to start job with id = '%s'";
    public static final String QRTZ_FAILED_TO_INTERRUPT_JOB = "Failed to interrupt job with id = '%s'";
    public static final String QRTZ_FAILED_DESERIALIZE_INPUT_PARAM = "Failed to deserialize input param = %s";
    public static final String QRTZ_INVALID_CLASS_NAME = "Impossible to create job of class = '%s' because there is no implementation of such a class";
}
