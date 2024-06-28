package com.hyfly.tf.processor.factory


import com.hyfly.tf.processor.entity.bo.TfCommand
import java.util.*

object TfCommandFactory {

    fun createBaseInit(): TfCommand =
        TfCommand(true, LinkedList(listOf("terraform", "init", "-no-color")))

    fun createBasePlan2Json(): TfCommand =
        TfCommand(
            true,
            LinkedList(
                listOf(
                    "terraform",
                    "plan",
                    "-no-color",
                    "-out=./tfplan",
                    "-json",
                    "-var-file=./values.tfvars.json"
                )
            )
        )

    fun createBaseShowPlanJson(): TfCommand =
        TfCommand(true, LinkedList(listOf("terraform", "show", "-no-color", "-json", "./tfplan")))

    fun createBaseValidate(): TfCommand =
        TfCommand(false, LinkedList(listOf("terraform", "validate", "-no-color", "-json")))

    fun createBaseApply(): TfCommand =
        TfCommand(false, LinkedList(listOf("terraform", "apply", "-auto-approve", "-no-color", "-json")))

    fun createBaseShow(): TfCommand =
        TfCommand(true, LinkedList(listOf("terraform", "show", "-no-color", "-json")))

    fun createBaseDestroy(): TfCommand =
        TfCommand(true, LinkedList(listOf("terraform", "destroy", "-auto-approve", "-no-color", "-json")))
}
