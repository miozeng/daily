# redis结合spring的使用方法
## 1. maven配置
```xml
		<dependency>
		    <groupId>redis.clients</groupId>
		    <artifactId>jedis</artifactId>
		    <version>2.9.0</version>
		</dependency>  
		<dependency>
	        <groupId>org.springframework.data</groupId>
	        <artifactId>spring-data-redis</artifactId>
	        <version>1.7.6.RELEASE</version>
	    </dependency>
```

## 2. spring 配置 redis.proerties +application-config-redis.xml

## 3.对象 serializer策略， RedisObjectSerializer

## 4.使用
```Java
redisTemplate.opsForValue();
redisTemplate.opsForHash();
redisTemplate.opsForList();
redisTemplate.opsForSet();
redisTemplate.opsForZSet();
......
```