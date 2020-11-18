

## lab后端说明 

​																																																				——created by 屈德林 in 2020/11/14

目录：

[TOC]



## 简介

- 本文件是对lab目录下后端代码的介绍，你将会了解lab目录结构，以及程序运行的方法。



## 运行环境

- 操作系统：Ubuntu20.04 LTS（Linux环境）/ windows
- 操作软件：IDEA



## 文件目录

```bash
$ tree -d
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── lin
    │   │           ├── config
    │   │           ├── controller
    │   │           ├── entity
    │   │           ├── mapper
    │   │           ├── service
    │   │           └── utils
    │   └── resources
    │       ├── mappers
    │       ├── static
    │       │   ├── css
    │       │   ├── fonts
    │       │   └── js
    │       └── templates
    └── test
        └── java
            └── com
                └── lin
                    └── mapper
```



## 如何运行？

- 创建数据库

在resource目录下的sql文件，执行他创建lab数据库

- 修改配置文件

修改application.yaml中的数据库配置信息

- 点击IDEA图标运行application