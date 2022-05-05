# ibm-kafka-may-2022
IBM Kafka Training May 2022 

### System Requirement 
- System with 8 GB 
- Hypervisor Support to test with docker 
- abount 50 GB free space 
- Operating System Mac / Windows / Linux
- Open internet access 


### Step 1 
> Download and install jdk 11 for your respective OS(Mac/Win/Linux) - https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html

### Step 2 
>  Download Kafka from - https://dlcdn.apache.org/kafka/3.1.0/kafka_2.13-3.1.0.tgz
> Extract with softwares like 7 zip / win zip etc 

* Note Windows Users: install wsl2 first -  https://docs.microsoft.com/en-us/windows/wsl/install
### Step 3 
> Add Kafka to system path - kafka_2.13-3.1.0/bin 



## Introduction 

-  Suresh - 10.5 year exp, working  on tibco, new to kafka, understand      holistic view of kafka, 
- Sreeni - 17 year exp, 5 years in ibm, automation arch, know about kafka, know concepts of kakfa, need more of advance features. 
- Soumen - 16 years of exp, automation arch, getting started with kafka, and understand how it works at enterprise level, 
- Anshuman - 5.5 yrs exp, got introduction to kakfa,worked with CLI, looking for configuration, with AWS 
- Bhaskara Naidu - 6.5 years, test developer, new to kafka, worked on selenium and UI 
- Jasleen Kaur - 6 years exp, 1+ in IBM, automation, does not have hands on exp with kafka, interested to understand deeper concepts of kafka, and it is used in kafka 
- Kavia G - 5 yrs exp, automation testing, API tester, new to kafka 
- Muralidhar - 16 years exp, worked in Cypress, selenium, UFT, working with MQ, understand arch 
- ritika - 9 years exp, knows basics of kafka, wants to get into deeper understanding of kafka. 


- Naveen Kumar - 20 Years exp, 70+ companies, 

- 4th, 5th, 6th may 2022 

- spoke about TOC 



- Installation 
    - Java 11 is installed 
    - for windows users's install wsl 
    - ex : wsl --install -d Ubuntu 
- Apache Kafka 
    - /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/bin - linux / mac 
        - KAFKA_HOME=/Volumes/Kanchan/Softwares/kafka/kafka-3.1.0

export PATH=$HOME/.local/bin:$PATH:/Volumes/Kanchan/Softwares/mongo-db/bin:$M2_HOME/bin:$JAVA_HOME/bin:/usr/local/bin:/usr/local/mysql/bin:$POST_GRES:$GO:$PROTOC_HOME/bin:$KAFKA_HOME/bin
   - /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/bin/windows


Why we need ESB 

- real time stream of data 
- send message async / sync 
- any kind of streaming / event based system 


- Monolithic 
    - view 
    - BL 
    - DB 
    - Service 

- Microserivce 
    - login 
    - SSO 
    - Stock 
    - Product 
    - video 
    - comments 
    - likes
    - dislikes.... 

- ESB Service Providers 
    - Open MQ 
    - Rabbit MQ 
    - Tibco 
    - Mule soft 
    - Kafka 
    ... 

- Kafka 
    - JVM - Platform Independent 
    - Scala 
        - Groovy, ruby,R etc... 
    - java upto 8 - did not support functional feature -> 
    - 3.x - with Scala + Java 
    - -> it becomes by default asycn / support reactive / sync 


- In DB you can query, in kafka cluster you cannot query 
    - to send message you need kafka producer 
    - to receive message you need kafka consumer - Have to listen to Topic 

- by default every message is kept for 1 week 
- when you create a topic by default there shall be 1 partition 
- kafka 2.x - zookeeper is mandatory 
- kafka 3.x - zookeeper is not mandatory - there is another tool called raft for kafka which is called kraft
- kafka 4.x - zookeeper shall be removed 


Step to start kafka 

> zookeeper-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/zookeeper.properties

> kafka-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/server.properties

> kafka-server-start.sh ../../config/server.properties 

> kafka-topics.sh --bootstrap-server localhost:9092 --list 

> kafka-topics.sh --bootstrap-server localhost:9092 --create --topic first-topic

> kafka-topics.sh --bootstrap-server localhost:9092 --describe  (describe all the topics)

> kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic ibm-first-topic  (describe specific topic)

> kafka-console-producer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic --from-beginning 




- producer sending messages with key 

> kafka-console-producer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic --property parse.key=true --property key.separator=:

```
    somekey:somevalue
    ritika:12345
```
> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic --property parse.key=true --property key.separator=:  (this consumer shall not print the key values FYI)




> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic --property parse.key=true --property key.separator=: --property print.key=true --property  print.timestamp=true --formatter kafka.tools.DefaultMessageFormatter




## to whow working with partitions 

> kafka-topics.sh --bootstrap-server localhost:9092 --create --topic ibm-second-topic --partitions 3 

> kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic ibm-second-topic 

- consumer + consumer groups 

```
- $ kafka-console-consumer.sh - --group <String: consumer group id>      The consumer group id of the consumer.

- $ kafka-consumer-groups.sh - --group <String: consumer group id>      The consumer group id of the consumer.

```

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-first-topic --group my-first-group 

> kafka-topics.sh --bootstrap-server localhost:9092 --topic ibm-second-topic --describe 

> kafka-console-producer.sh --bootstrap-server localhost:9092 --topic ibm-second-topic 

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-second-topic 

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-second-topic --group p-first-group

> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ibm-second-topic --group p-second-group

-- how uber work 
-- swiggy works 
-- netflix works 

- reset the offset vales when we read 

> kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group p-first-group --reset-offsets  --to-earliest --execute --topic ibm-second-topic



- start kafka without zookeeper 



--- 

# Day 2 
- Working with program - Java 
    - producer 
    - consumer 
    - working with groups
    - rebalancing 

### Software list 
- Java 11 
- IDE - Eclipse / Itellij / VCode 

- you can create a core project (not a good option)
- create a maven project / gradle 

- Single Node Single Cluster  (1 Zookeeper, 1 Kafka) 
- Single Node Multi Cluster (1 Zookeepr, N Kafka)
- Multi Node Multi Cluster (M zookeepr, N Kafka)

- start kafka without zookeeper 




https://kafka.apache.org/23/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html

-- the replication factor of more than 1 will not work till you have n number of brokers >1 
$ kafka-topics.sh --bootstrap-server localhost:9092 --create --topic ibm-java-topic --replication-factor 1


## Steps for sending message from Java to Kafka 
1. Create a Maven Project 
2. Get dependincies 
    2.1 Kafka Clients - pom.xml 
    2.2 slf4j api + simple 
3. Program 
    3.1 Configuration - bootstrap server : 9092 and what kind of data are you sending key, value 
    3.2 Producer - will take the configuration 
    3.3 Generate a kafka record which shall have topic and message 
    3.4 producer.send(record, callback) - callbacks are called implicitily by KafkaClient 
    3.5 make sure you flush and close if not the connection to kafka is open 
4. Kafka 
    4.1 start zookeeper 
    4.2 start kafka 
    4.3 create a topic 
5. Run your java program 


Kafka uses Murmur 

targetPartition = Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1)










Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 77 

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 78

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 79

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 80

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 81

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 82

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 83

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 84

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 85

Partition 2
Time Stamp Thu May 05 12:14:38 IST 2022
Offset 86