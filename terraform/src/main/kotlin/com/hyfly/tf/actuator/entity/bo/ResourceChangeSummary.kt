package com.hyfly.tf.actuator.entity.bo;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class ResourceChangeSummary {

    private final Map<String, ResourceSummary> create;

    private final Map<String, ResourceSummary> update;

    private final Map<String, ResourceSummary> delete;

    private final Set<String> createdSet;

    private final Set<String> updatedSet;

    private final Set<String> deletedSet;

    public ResourceChangeSummary() {
        this.create = new HashMap<>();
        this.update = new HashMap<>();
        this.delete = new HashMap<>();
        this.createdSet = new HashSet<>();
        this.updatedSet = new HashSet<>();
        this.deletedSet = new HashSet<>();
    }

    public void addCreate(String key, ResourceSummary value) {
        this.create.put(key, value);
    }

    public void addCreated(String key) {
        this.createdSet.add(key);
    }

    public void addUpdate(String key, ResourceSummary value) {
        this.update.put(key, value);
    }

    public void addUpdated(String key) {
        this.updatedSet.add(key);
    }

    public void addDelete(String key, ResourceSummary value) {
        this.delete.put(key, value);
    }

    public void addDeleted(String key) {
        this.deletedSet.add(key);
    }

    public void setCreate(Map<String, ResourceSummary> create) {
        this.create.clear();
        this.create.putAll(create);
    }

    public void setUpdate(Map<String, ResourceSummary> update) {
        this.update.clear();
        this.update.putAll(update);
    }

    public void setDelete(Map<String, ResourceSummary> delete) {
        this.delete.clear();
        this.delete.putAll(delete);
    }

    public int createCount() {
        return this.create.size();
    }

    public int updateCount() {
        return this.update.size();
    }

    public int deleteCount() {
        return this.delete.size();
    }

    public boolean isNoOperation() {
        return this.createCount() + this.updateCount() + this.deleteCount() == 0;
    }

}
