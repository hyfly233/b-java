

测试请求参数

```json
{
    "producerType": "h3c",
    "tenantId": "9052ae85792143ff955c35c52e8e41bd",
    "resources": [
        {
            "type": "h3c_security_group",
            "alias": "test_sg_1",
            "params": {
                "name": "test_sg_11111",
                "user_id": "43a733a60f93412fb0bd84bf5008875c",
                "description": "11111"
            }
        },
        {
            "type": "h3c_security_group",
            "alias": "test_sg_2",
            "params": {
                "name": "test_sg_22222",
                "user_id": "43a733a60f93412fb0bd84bf5008875c",
                "description": "22222"
            }
        },
        {
            "type": "h3c_compute_instance",
            "alias": "test_ecs_1",
            "params": {
                "tenant_id": "9052ae85792143ff955c35c52e8e41bd",
                "admin_pass": "biitt@123",
                "flavor_id": "0e5d9e49-de52-447c-8c46-08b5a7bbd598",
                "image_id": "71410fab-1d38-42cd-b12c-896e8c2edca7",
                "name": "test-12",
                "nova_zone_name": "biitt15",
                "nova_zone_alias_uuid": "dc479ad3-901d-420e-9156-1c930bb69f91",
                "alias": "test_terraform",
                "description": "123123",
                "enable_admin_pass": "1",
                "user_name": "admin",
                "cinder_zone_name": "biitt_cinder",
                "cinder_zone_alias_uuid": "862b8d20-2cd4-4005-bdb3-222d349528ee",
                "storage_pool_name": "pool14",
                "volume_type": "hhd-thin",
                "volume_format": "raw",
                "storage_type_name": "cinder_pool",
                "system_disk_size": 80,
                "security_groups": [
                    "8202932b-3138-4854-859a-4dc85f2aee35"
                ],
                "networks": [
                    {
                        "uuid": "c4c68d34-941f-43b5-a713-8b92fec1f73c",
                        "enable_gateway": true,
                        "fixed_ip": "192.168.10.77"
                    }
                ]
            }
        },
        {
            "type": "h3c_volume",
            "alias": "test_volume",
            "params": {
                "tenant_id": "9052ae85792143ff955c35c52e8e41bd",
                "name": "test2",
                "description": "test2",
                "size": 10,
                "multi_attach": true,
                "cinder_zone_name": "biitt_cinder",
                "volume_type": "hhd-thin",
                "user_name": "admin",
                "cinder_zone_alias": "cinder15",
                "cinder_zone_alias_uuid": "862b8d20-2cd4-4005-bdb3-222d349528ee",
                "storage_type_name": "cinder_pool",
                "storage_type_id": "4e46c0df-aa25-49ed-a305-fa094e51aeac",
                "storage_pool_name": "pool14",
                "volume_format": "raw"
            }
        }
    ],
    "relations": [
        {
            "source": "test_ecs_1",
            "target": "test_volume"
        },
        {
            "source": "test_ecs_1",
            "target": "test_sg_1"
        }
    ],
    "depends": [],
    "variables": {
        "ECS-Password-hp5d": {
            "description": "Ecs password for ecs-9fnjp",
            "type": "string",
            "sensitive": true,
            "nullable": true,
            "default": "123"
        },
        "ECS-Password-igmi": {
            "description": "Ecs password for ecs-9fnjp",
            "type": "string",
            "sensitive": true,
            "nullable": true
        }
    }
}
```

