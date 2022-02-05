# spring-boot-demo-2022
Spring Web + Thymeleaf + Spring Data JPA(hibernate) + mysql demo code for spring boot project

## 开发工具
[JDK 11](https://developer.ibm.com/languages/java/semeru-runtimes/downloads)

[Spring Tools Suite 4](https://spring.io/tools)

[Mysql Community](https://dev.mysql.com/downloads/installer/)


## 开发步骤
1. 配置Spring，Mysql，Log4j
2. 功能1
	* View - Thymeleaf	
	* Model - 数据Bean
	* Controller - API/Path
	* persistence - 持久层数据操作
	* Service - 服务层，需要的时候
3. 功能2
	...
	
## 讲解重点
### Day1
1. 项目创建
	```
	Spring starter project
		MySQL
		Spring Data JPA
		Spring Web
		Thymeleaf
		Spring Boot DevTools
	```
2. pom.xml
	* 排除 spring-boot-starter-logging
	* 增加log4j-slf4j-impl com.lmax（disruptor）
3. 配置log4j2
4. 配置mysql,jpa 
5. Model
6. Repository
7. Service
8. Controller
9. View（Thymeleaf）
10. 测试，联动
11. 观察日志