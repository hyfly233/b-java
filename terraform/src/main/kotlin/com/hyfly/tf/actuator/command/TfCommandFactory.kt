package com.hyfly.tf.actuator.command

import java.util.*

object TfCommandFactory {
    @JvmStatic
    fun init(): TfCommand {
        return TfCommand(false, LinkedList(mutableListOf("terraform", "init", "-no-color")))
    }

    @JvmStatic
    fun plan2Json(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "plan", "-no-color", "-out=./tfplan", "-json")))
    }

    @JvmStatic
    fun showPlanJson(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "show", "-no-color", "-json", "./tfplan")))
    }

    @JvmStatic
    fun validate(): TfCommand {
        return TfCommand(false, LinkedList(mutableListOf("terraform", "validate", "-no-color", "-json")))
    }

    @JvmStatic
    fun apply(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "apply", "-auto-approve", "-no-color", "-json")))
    }

    @JvmStatic
    fun show(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "show", "-no-color", "-json")))
    }

    @JvmStatic
    fun destroy(): TfCommand {
        return TfCommand(true, LinkedList(mutableListOf("terraform", "destroy", "-auto-approve", "-no-color", "-json")))
    }
}
