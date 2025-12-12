# Spring MVC 课程管理示例（XML 配置）

基于 Spring MVC + Spring 的课程管理系统，使用 JSP 提供交互界面，课程信息保存在内存中，适合本地快速演示。包含简单登录功能、课程新增/查看/编辑流程。

## 运行方式
1. 安装 JDK 17 与 Maven。
2. 启动应用（Jetty）：
   ```bash
   mvn jetty:run
   ```
3. 浏览器访问：
   - 登录页: http://localhost:8080/login （默认账号：admin / 123456）
   - 登录成功后跳转首页 `/home`

### IDEA 启动方式（无 Spring Boot）
- 项目不包含 `SpringmvcStumApplication` 之类的 Spring Boot 启动类，直接删除或忽略本地遗留的该文件即可。
- 以 Maven 项目方式导入后，可使用 **Run/Debug Configurations → Maven** 创建配置，`Command line` 填写 `jetty:run`，点击运行即可启动。
- 如需用 Tomcat，配置 Artifacts（war exploded）并指向 `src/main/webapp/WEB-INF/web.xml`，按传统 Servlet 方式部署运行。

## 主要功能
- 登录/退出：固定账号验证，登录后跳转首页。
- 首页：提供新增课程、查看课程列表入口。
- 新增课程：填写课程名、描述、授课日期（格式 `yyyy-MM-dd`，后台存毫秒）、可选人数、教室（下拉）、可选专业（多选）、授课方式（线上/线下）。
- 课程列表：展示所有课程名，点击查看详情。
- 课程详情：展示全部信息，可进入编辑页面。
- 编辑课程：回填已有信息，可再次提交更新。

## 说明
- 系统当前使用内存存储，重启后数据会清空。如需持久化，可在此基础上接入数据库与 MyBatis/JPA。
- 项目采用 XML 配置（`web.xml` + `applicationContext.xml` + `servlet-context.xml`），不包含 Spring Boot 依赖或启动类。
