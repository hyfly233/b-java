package com.hyfly.tf.actuator.entity.constants

object TfFileName {
    const val VALUES_FILE_NAME: String = "values.tfvars.json"

    const val PLAN_FILE_NAME: String = "tfplan"

    const val PLAN_MINIO_BUCKET_NAME: String = "tfplan"

    const val TFSTATE_MINIO_BUCKET_NAME: String = "tfstate"

    const val TERRAFORM_PLUGIN_PATH: String = ".terraform"

    const val TERRAFORM_STATE: String = ".tfstate"

    const val PLUGIN_PROVIDER_FILE_NAME: String = "plugin_provider.tf.json"

    const val EMPTY_TERRAFORM_FILE_NAME: String = "empty.tf.json"

    const val TERRAFORM_LOCK_FILE_NAME: String = ".terraform.lock.hcl"
}
