package com.hyfly.tf.actuator.processor

import com.hyfly.tf.actuator.entity.constants.TfCommand
import java.util.*

object TfCommandFactory {
    @JvmStatic
    fun createBaseInit(): TfCommand {
        return TfCommand(false, LinkedList(mutableListOf("terraform", "init", "-no-color")))
    }

    @JvmStatic
    fun createBasePlan2Json(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "plan", "-no-color", "-out=./tfplan", "-json")))
    }

    @JvmStatic
    fun createBaseShowPlanJson(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "show", "-no-color", "-json", "./tfplan")))
    }

    fun createBaseValidate(): TfCommand {
        return TfCommand(false, LinkedList(mutableListOf("terraform", "validate", "-no-color", "-json")))
    }

    @JvmStatic
    fun createBaseApply(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "apply", "-auto-approve", "-no-color", "-json")))
    }

    fun createBaseShow(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "show", "-no-color", "-json")))
    }

    @JvmStatic
    fun createBaseDestroy(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "destroy", "-auto-approve", "-no-color", "-json")))
    }
}
