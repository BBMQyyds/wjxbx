# bean规范说明

## 命名规范

### 包名与entity类名一一对应，如：UserEntity->com.jdsbbmq.wjxbx.bean.user

### 每个包下均有一个与包名相同的bean类，如：com.jdsbbmq.wjxbx.bean.user.User

### 与包同名类为对应的entity类扩展类，增加了一些业务属性

### 与包不同名的类为辅助业务类，根据前端需求而定

## 属性规范

### 使用lombok @Data注解，自动生成getter/setter方法

### 使用lombok @NoArgsConstructor注解，自动生成无参构造方法

### 使用lombok @AllArgsConstructor注解，自动生成全参构造方法

### 使用JSR-303 Bean Validation规范，对属性进行校验
