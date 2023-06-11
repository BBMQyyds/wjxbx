# junit测试类规范说明

## 方法规范

### 测试方法应该使用@Test注解进行标注

### 注意：有对数据库增删改的测试方法，应该使用@Transactional注解进行标注

## 结构规范

### 测试类路径规范

#### 测试类路径应该与被测试类路径保持一致

#### 例如，被测试类路径为：com.jdsbbmq.dao.UserEntityMapper

#### 则测试类路径应该为：com.jdsbbmq.dao.UserEntityMapperTests

### 测试类命名规范

#### 测试类命名应该以Tests结尾

#### 例如，被测试类路径为：com.jdsbbmq.dao.UserEntityMapper

#### 则测试类命名应该为：UserEntityMapperTests

### 测试方法规范

#### 测试方法命名应该以Test结尾，并按照被测试类中方法的顺序进行排列

#### 例如，被测试类方法为：login、register

#### 则测试方法命名应该为：loginTest、registerTest，并按照login、register的顺序进行排列

#### 测试结果输出应写出所测试的类，如：UserController: >>login登录测试成功