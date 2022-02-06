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

### Day1 回顾-知识点
1. 理解Spring配置文件
2. 理解log4j配置文件
3. 理解JPA的开发
4. 熟悉Thymeleaf的构成
5. 理解Controller的数据如何传给View（Request Level）
6. Log4j, 使用日志来调试

### Day2 
1. 实现Login(View)画面
2. 实现Login(controller)
3. 实现Login(db, service)数据密码验证
4. 验证login输入项目
5. 显示输入错误提醒信息（非空，字符串长度）
6. 显示用户名，密码不正确的输入错误
7. 输入成功是显示下一个画面
8. 登录成功后不需要再次登录

### Day2 回顾-知识点
*	View数据传递Controller（Request Level)
*	Controller数据传递给View（Request Level)
*	hibernate validation 字段级验证
*	Thymeleaf error 字段级验证结果显示
*	用户密码加密后比较，用户名密码业务级验证
*	验证结果（global error）信息显示	
*	Session级变量和Request级变量

### Day3
1. 实现下拉选择框
	*	Model
	*	JPA Repository
	*	Service
	*	Controller
	*	view(Thymeleaf)
2. 实现Radio Button
	*	Model
	*	JPA Repository
	*	Service
	*	Controller
	*	view(Thymeleaf)