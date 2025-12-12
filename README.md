# Spring MVC 课程管理示例（SSM + MyBatis）

基于 Spring MVC + Spring + MyBatis（SSM）的课程管理系统，使用 JSP 提供交互界面，课程信息存储在数据库（默认 H2）。包含简单登录功能、课程新增/查看/编辑流程。

## 运行方式
1. 安装 JDK 17 与 Maven。
2. 配置数据库连接：编辑 `src/main/resources/jdbc.properties`，默认使用本地文件型 H2 数据库。
3. 初始化数据表（以 H2 为例，首次运行前在项目根目录执行）：
   ```bash
   mvn -q -DskipTests package dependency:copy-dependencies
   java -cp "target/classes:target/dependency/*" org.h2.tools.Shell -url "jdbc:h2:./data/course_db" -user sa -sql "CREATE TABLE IF NOT EXISTS courses (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), description VARCHAR(200), session_time_millis BIGINT, capacity INT, classroom VARCHAR(50), majors VARCHAR(255), delivery_method VARCHAR(50));"
   ```
   如果使用 MySQL/其他数据库，请创建同名字段的 `courses` 表即可。
4. 启动应用（Jetty）：
   ```bash
   mvn jetty:run
   ```
5. 浏览器访问：
   - 登录页: http://localhost:8080/login （默认账号：admin / 123456）
   - 登录成功后跳转首页 `/home`

## 主要功能
- 登录/退出：固定账号验证，登录后跳转首页。
- 首页：提供新增课程、查看课程列表入口。
- 新增课程：填写课程名、描述、授课日期（格式 `yyyy-MM-dd`，后台存毫秒）、可选人数、教室（下拉）、可选专业（多选）、授课方式（线上/线下）。
- 课程列表：展示所有课程名，点击查看详情。
- 课程详情：展示全部信息，可进入编辑页面。
- 编辑课程：回填已有信息，可再次提交更新。

## 自行插入演示数据
数据库准备好后，可以通过 SQL 预置几条课程数据，例如（H2/MySQL 通用）：
```sql
INSERT INTO courses(name, description, session_time_millis, capacity, classroom, majors, delivery_method)
VALUES ('软件工程导论', '基础软件工程课程', 1633046400000, 80, 'A101', '软件工程,计算机科学', '线下');
INSERT INTO courses(name, description, session_time_millis, capacity, classroom, majors, delivery_method)
VALUES ('云计算概论', '云平台与架构', 1635734400000, 120, 'B202', '计算机科学,信息安全', '线上');
```
完成后重新访问课程列表即可看到数据。
