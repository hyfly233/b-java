package com.hyfly.actuator.entity;


import com.hyfly.actuator.entity.constants.TfCommand;

import java.util.Arrays;
import java.util.LinkedList;


public class TfCommandFactory {

    public static TfCommand createBaseInit() {
        return new TfCommand(true, new LinkedList<>(Arrays.asList("terraform", "init", "-no-color")));
    }

    public static TfCommand createBasePlan2Json() {
        return new TfCommand(true, new LinkedList<>(Arrays.asList("terraform", "plan", "-no-color", "-out=./tfplan", "-json", "-var-file=./values.tfvars.json")));
    }

    public static TfCommand createBaseShowPlanJson() {
        return new TfCommand(true, new LinkedList<>(Arrays.asList("terraform", "show", "-no-color", "-json", "./tfplan")));
    }

    public static TfCommand createBaseValidate() {
        return new TfCommand(false, new LinkedList<>(Arrays.asList("terraform", "validate", "-no-color", "-json")));
    }

    public static TfCommand createBaseApply() {
        return new TfCommand(false, new LinkedList<>(Arrays.asList("terraform", "apply", "-auto-approve", "-no-color", "-json")));
    }

    public static TfCommand createBaseShow() {
        return new TfCommand(true, new LinkedList<>(Arrays.asList("terraform", "show", "-no-color", "-json")));
    }

    public static TfCommand createBaseDestroy() {
        return new TfCommand(true, new LinkedList<>(Arrays.asList("terraform", "destroy", "-auto-approve", "-no-color", "-json")));
    }

}
