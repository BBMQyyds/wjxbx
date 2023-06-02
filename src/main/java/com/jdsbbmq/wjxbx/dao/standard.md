# dao层规范说明

## 命名规范

### 1. 分为功能实现、辅助功能两大类，功能实现分为查询、增删改两类

#### 功能实现是前端直接调用的方法，如：login、register

#### 辅助功能是功能实现的辅助方法，不对外提供调用，如：selectIdByUsername

### 2. select insert update delete 依次排列

#### 特殊方法名提前，如：login、register

#### 条件查询方法名以select开头，后面跟查询结果和查询条件，如：selectIdByUsername

#### 条件中属性名应该与数据库中字段名一致，将下划线命名转换为驼峰命名，如：user_id -> userId
