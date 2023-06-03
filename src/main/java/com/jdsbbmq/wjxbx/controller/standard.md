# controller层规范说明

## 命名规范

### 类名以Controller结尾，如：UserController

### 方法名与Service层方法名一致

## 逻辑规范

### 使用@RestController注解，返回json数据

### 返回类型不为Entity，而是根据业务逻辑返回相应的数据类型

### 使用JSR303 @Valid注解，校验参数

### 使用@RequestBody注解，接收前端传递的json数据

### 不要在Controller层写业务逻辑，业务逻辑写在Service层