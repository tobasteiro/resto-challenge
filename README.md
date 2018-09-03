# Restaurant delivery app

## Project Setup

<h1>KAFKA server init.</h1>

1- Download kafka tar

```shell
	tar -xzf kafka_2.11-0.9.0.0.tgz
	cd kafka_2.11-0.9.0.0
```

2- Run Zookeeper server

```shell
	bin/zookeeper-server-start.sh config/zookeeper.properties &
```

3- Start kafka server
```shell
	bin/kafka-server-start.sh config/server.properties
```

4- Create topic for orders
```shell
	bin/kafka-topics.sh --create --zookeeper localhost:2181 \
       --replication-factor 1 --partitions 1 --topic orders
```

5- Check topic creation
```shell
	bin/kafka-topics.sh --list --zookeeper localhost:2181
```

<h1>Producer execution.</h1>
