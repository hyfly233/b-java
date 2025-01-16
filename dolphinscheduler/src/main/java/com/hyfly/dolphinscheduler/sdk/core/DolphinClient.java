package com.hyfly.dolphinscheduler.sdk.core;

import com.hyfly.dolphinscheduler.sdk.datasource.DataSourceOperator;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceOperator;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefinitionOperator;
import com.hyfly.dolphinscheduler.sdk.project.ProjectOperator;
import com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate;
import com.hyfly.dolphinscheduler.sdk.resource.ResourceOperator;
import com.hyfly.dolphinscheduler.sdk.schedule.ScheduleOperator;
import com.hyfly.dolphinscheduler.sdk.taskinstance.TaskInstanceOperator;
import com.hyfly.dolphinscheduler.sdk.tenant.TenantOperator;
import lombok.extern.slf4j.Slf4j;

/** dolphin scheduler client to operate dolphin scheduler */
@Slf4j
public class DolphinClient {

  private final DolphinsRestTemplate dolphinsRestTemplate;
  private final String dolphinAddress;
  private final String token;

  private DataSourceOperator dataSourceOperator;
  private ResourceOperator resourceOperator;
  private ProcessDefinitionOperator processDefinitionOperator;
  private ProcessInstanceOperator processInstanceOperator;
  private ScheduleOperator scheduleOperator;
  private ProjectOperator projectOperator;
  private TenantOperator tenantOperator;
  private TaskInstanceOperator taskInstanceOperator;

  public DolphinClient(
      String token, String dolphinAddress, DolphinsRestTemplate dolphinsRestTemplate) {
    this.token = token;
    this.dolphinAddress = dolphinAddress;
    this.dolphinsRestTemplate = dolphinsRestTemplate;
    this.initOperators();
  }

  public void initOperators() {
    this.dataSourceOperator =
        new DataSourceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.resourceOperator =
        new ResourceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.processDefinitionOperator =
        new ProcessDefinitionOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.processInstanceOperator =
        new ProcessInstanceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.scheduleOperator =
        new ScheduleOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.projectOperator =
        new ProjectOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.taskInstanceOperator =
        new TaskInstanceOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
    this.tenantOperator =
        new TenantOperator(this.dolphinAddress, this.token, this.dolphinsRestTemplate);
  }

  public DataSourceOperator opsForDataSource() {
    return this.dataSourceOperator;
  }

  public ResourceOperator opsForResource() {
    return this.resourceOperator;
  }

  public ProcessDefinitionOperator opsForProcessDefinition() {
    return this.processDefinitionOperator;
  }

  public ProcessInstanceOperator opsForProcessInst() {
    return this.processInstanceOperator;
  }

  public ScheduleOperator opsForSchedule() {
    return this.scheduleOperator;
  }

  public ProjectOperator opsForProject() {
    return this.projectOperator;
  }

  public TaskInstanceOperator opsForTaskInstance() {
    return this.taskInstanceOperator;
  }

  public TenantOperator opsForTenant() {
    return this.tenantOperator;
  }
}
