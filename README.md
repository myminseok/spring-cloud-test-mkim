spring cloud test

config http://localhost:8888

eureka http://localhost:8761

rabbitmq 
brew install rabbitmq
/usr/local/sbin/rabbitmq-server
http://localhost:15672

service1 http://localhost:8081/call
service1 hystrix.stream http://localhost:8081/hystrix.stream

service2 http://localhost:8082/call
service2 hystrix.stream http://localhost:8082/hystrix.stream

turbine.stream http://localhost:9999

hystrix dashboard: http://localhost:7989/hystrix



