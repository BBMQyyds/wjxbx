## 项目启动

#### java -jar target/wjxbx-0.0.1-SNAPSHOT.jar 运行
#### nohup java -jar wjxbx-0.0.1-SNAPSHOT.jar & 后台运行
#### ctrl + c 退j出


## 分析wjxbx-manual并上传到sonarqube

#### mvn clean verify sonar:sonar \
#### -Dsonar.projectKey=wjxbx-manual \
#### -Dsonar.projectName='wjxbx-manual' \
#### -Dsonar.host.url=http://101.43.255.130:11000 \
#### -Dsonar.language=java,xml \
#### -Dsonar.token=sqp_14bfc344df85feb948b2ed4e6dfc6a589985b754


## 分析wjxbx-vue-manual并上传到sonarqube

#### sonar-scanner \
#### -Dsonar.projectKey=wjxbx-vue-manual \
#### -Dsonar.sources=. \
#### -Dsonar.host.url=http://101.43.255.130:11000 \
#### -Dsonar.language=html,js \
#### -Dsonar.token=sqp_8492318e1bb911f8ac76098450baa012f44b4b73


## 开放端口

#### firewall-cmd --zone=public --add-port=xxxx/tcp --permanent
#### firewall-cmd --reload
#### firewall-cmd --list-ports


## 杀死进程

#### lsof -i:xxxx
#### kill -9 xxxx