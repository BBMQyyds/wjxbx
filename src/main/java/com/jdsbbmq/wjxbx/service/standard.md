# service层规范说明

## 命名规范

### 接口名以Service结尾，如：UserService

### 接口方法名与Mapper中方法名一致

## 逻辑规范

### 使用@Service注解，标注为Service层

### 必要时使用@Transactional注解，标注为事务

### 在service层写业务逻辑，不要在Controller层写业务逻辑

### 在service层操作minIO等第三方服务

