package com.hyfly.tf.actuator.entity.bo

data class ResourceChangeSummary(
    val create: MutableMap<String, ResourceSummary> = mutableMapOf(),
    val update: MutableMap<String, ResourceSummary> = mutableMapOf(),
    val delete: MutableMap<String, ResourceSummary> = mutableMapOf(),
    val createdSet: MutableSet<String> = mutableSetOf(),
    val updatedSet: MutableSet<String> = mutableSetOf(),
    val deletedSet: MutableSet<String> = mutableSetOf()
) {

    fun addCreate(key: String, value: ResourceSummary) {
        create[key] = value
    }

    fun addCreated(key: String) {
        createdSet.add(key)
    }

    fun addUpdate(key: String, value: ResourceSummary) {
        update[key] = value
    }

    fun addUpdated(key: String) {
        updatedSet.add(key)
    }

    fun addDelete(key: String, value: ResourceSummary) {
        delete[key] = value
    }

    fun addDeleted(key: String) {
        deletedSet.add(key)
    }

    fun setCreate(create: Map<String, ResourceSummary>) {
        this.create.clear()
        this.create.putAll(create)
    }

    fun setUpdate(update: Map<String, ResourceSummary>) {
        this.update.clear()
        this.update.putAll(update)
    }

    fun setDelete(delete: Map<String, ResourceSummary>) {
        this.delete.clear()
        this.delete.putAll(delete)
    }

    fun createCount(): Int {
        return create.size
    }

    fun updateCount(): Int {
        return update.size
    }

    fun deleteCount(): Int {
        return delete.size
    }

    fun isNoOperation(): Boolean {
        return createCount() + updateCount() + deleteCount() == 0
    }
}
