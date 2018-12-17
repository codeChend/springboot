# springboot基础分层初始化框架

## 项目结构

### 项目结构图



### 概述

springboot是基于springboot搭建的Demo，其主要分为六部分，springboot-service、springboot-repository、springboot-model、
springboot-api、springboot-web、document。

* springboot-api:API层。作为管理Http接口、Dubbo接口的文档，仅包含对外提供的接口。该目录上传至nexus。
* springboot-model:实体模块。作为全局常量、请求实体、返回实体、业务实体dto、数据库实体do、配置文件实体等类的模块。
* springboot-service:业务逻辑层。作为业务接口、业务接口、以及设计模式使用的抽象类、接口的模块。
* springboot-repository:持久层。包含Mapper接口、Repository接口、Repository实现、多级缓存。
* springboot-web:表现层。作为Controller层、定时任务执行、启动加载、配置加载、单元测试、配置文件管理模块。该目录打包为war作为发布使用。
* document:作为项目说明、设计、流程、数据库设计文档目录。

特别注意：

* 关于项目命名：建议例如：startdt-demo、startdt表示公司，demo表示项目名称。
* 关于局部常量：Service、Repository、Web都应包含自己的常量目录，该常量作用域为该模块，限定常量作用范围。
* 关于常用工具类：若多个项目都可能使用，
* 关于工具类目录：第一种方式，单独的工具类，可以在Model中建立utils目录，这样支持Service、Repository使用该工具。第二种方式，Service中建立utils，repository中不应使用自定义utils。
* 关于单元测试：单元测试可以分为两种，一种以XXTest，该测试为自己测试和观察某些功能使用；另一种为XXcheck，该测试应可重复执行验证，应考虑每次测试完成后保持数据不变，可以重复验证。
建议每次修改之后，重新执行所有Check，尽可能保证原有模块正确性。

-----

### 项目结构详细说明

#### document
* md:markdown文档，可以自行简历子目录。
* img:保存所有markdown文件使用的图片。

#### springboot-api
* dubbo:保存dubbo所需要的接口
* dto:保存dubbo提供服务的传输实体
* http:初步考虑是通过生成工具生成访问Controller的方法，目的是让调用方不自己构造HttpClient，只关注入参和返参

#### springboot-model
* vo:返回实体
* request:请求实体
* exception:自定义异常实体
* enums:枚举类型
* dto:业务实体，一般resp->dto->vo
* po:表结构映射实体
* constants:全局常量类
* annotation:自定义注解

#### springboot-repository
* dal.cache:数据持久化缓存实体
* dal.dao:数据库接口实现层
* dal.mapper:数据库mapper.xml的映射接口

#### springboot-service
* service:接口层
* service.impl:接口层的实现
* service.config:接口层的配置文件

#### springboot-web
* test:单元测试
* test.XXTest:自己的测试和观察某些功能使用
* test.XXCheck:对某些类、功能的全量测试，可重复执行，修改上线前应保障Check测试都通过
* resources:项目资源文件
* resources.logback.xml:logback使用的配置文件
* resources.log4j.properties:log4j使用的配置文件，要注意logback和log4j2的冲突问题
* resources.application.properties:选择启动环境，配置全局配置。例如：连接池大小、检测时间等
* resources.application-production.properties:正式环境配置
* resources.application-dev.properties:测试环境配置
* mapper:mybatis的xml配置文件
* Application:Springboot项目启动类。不要轻易变动位置，尽量应在项目package的最外层.
* task:定时任务
* controller:Controller类，对外提供Http服务
* controller.StoreController:使用@Valid用于验证的Demo
* config:启动时加载的配置文件
* filter:对文件的过滤层

## 使用

1. git fork整个项目
2. 按照自己项目的名称修改pom中groupId、artifactId、name
3. 修改文件名称，建议好pom中name名称一致
4. 最后删除iml文件重新导入整个项目
5. 修改git push至gitlab
6. 开始写代码吧~ヾ(◍°∇°◍)ﾉﾞ

