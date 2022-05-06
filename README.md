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


-- with keys 


Partition 0
Key id_1
Value  Hello World 1

Partition 0
Key id_3
Value  Hello World 3

Partition 0
Key id_6
Value  Hello World 6

Partition 2
Key id_2
Value  Hello World 2

Partition 2
Key id_4
Value  Hello World 4


Partition 2
Key id_5
Value  Hello World 5

Partition 2
Key id_7
Value  Hello World 7

Partition 2
Key id_9
Value  Hello World 9

Partition 1
Key id_0
Value  Hello World 0

Partition 1
Key id_8
Value  Hello World 8


while(true) {
    keep getting the messages 
    will get the message with the smallest possible time ms/Ms/ps
}


To discuss on day 3 
	partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor, class org.apache.kafka.clients.consumer.CooperativeStickyAssignor]


- To create Single Node Multi Cluster Arch 

> zookeeper-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/zookeeper.properties

> kafka-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/server-1.properties

> kafka-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/server-2.properties

> kafka-server-start.sh /Volumes/Kanchan/Softwares/kafka/kafka-3.1.0/config/server-3.properties

> kafka-topics.sh --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --replication-factor 3 --topic replica-topic

> kafka-topics.sh --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --replication-factor 3 --topic replica-topic-p3 --partitions 3


> kafka-topics.sh --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --replication-factor 1 --topic ibm-test-1

>  kafka-console-producer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic replica-topic


>  kafka-console-consumer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic replica-topic

> lsof -i:9092 | grep LISTEN 
get the port number 
> kill -9 <port number>











-- creating multi node multi cluster 


- tickTime : the basic time unit in milliseconds used by ZooKeeper. It is used to do heartbeats and the minimum session timeout will be twice the tickTime.
- The new entry, initLimit is timeouts ZooKeeper uses to limit the length of time the ZooKeeper 
- The entry syncLimit limits how far out of date a server can be from a leader.


Steps 1: 
- in zookeeper.properites, create similar for zookeeper1, 2, 3 
pls change clientPort and dataDir appropriately 
```
    dataDir=/tmp/tutorial/zookeeper-1


    # the port at which the clients will connect

    clientPort=2181

    # disable the per-ip limit on the number of connections since this is a non-production config
    maxClientCnxns=0
    # Disable the adminserver by default to avoid port conflicts.
    # Set the port to something non-conflicting if choosing to enable this
    admin.enableServer=false
    # admin.serverPort=8080

    tickTime=2000
    initLimit=5
    syncLimit=2

    server.1=127.0.0.1:2566:3566
    server.2=127.0.0.1:2567:3567
    server.3=127.0.0.1:2568:3568

```

- create a file called myid /tmp/tutorial/zookeeper-1 
and put a value called 1 and similarly 2,3 for other zookeeper files 


- start zookeeper 

> zookeeper-server-start.sh ./config/zookeeper-1.properties 

> zookeeper-server-start.sh ./config/zookeeper-2.properties 

> zookeeper-server-start.sh ./config/zookeeper-3.properties 


-- start kafka cluster before that change content of server-1.properties, server-2.properties, server-3.properties

> zookeeper.connect=127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183

> kafka-server-start.sh ./confic/server-1.properites 

> kafka-server-start.sh ./confic/server-2.properites 

> kafka-server-start.sh ./confic/server-3.properites 

- create a topic 
> kafka-topics.sh --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic test-topic-1 --replication-factor 3

- create console producer 
> kafka-console-producer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic test-topic-1

- create console consumer 
>  kafka-console-consumer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic test-topic-1

- to test kill 1 of the zookeeper 
- lsof -i:2181 | grep LISTEN




--- 
# Day 3 


- [x] Pull data from db and give it to consumer 
- [x] Start Kafka Without Zookeeper 
    > .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
    
    > start kafka 

    > .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic topic1

    > kafka-storage.sh random-uuid 
    
    > kafka-storage.sh format -t random-uuid -c config/kraft/server.properties
    
    > kafka-server-start.sh config/kraft/server.properties

    > kafka-topics.sh --bootstrap-server localhost:9092 --create --topic firs-topic 

    > start your producer 

    > start your consumer 

- [ ] Rebalancing with consumers - Java 
    - Partition Rebalance 
        - you move the partitions between consumers then rebalancing shall happen 
        - resassignemnt of partitions should have i.e., it should and rejoin 
        - on the fly we may have more partitions coming in 
        - Eager Reblancing 
            - all the consumers stop, give us there membership with partitions 
            - then they rejoin the consumer group 
            - even in ths short time all consumers a paused which we dont like nor we want 
            > 	partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor, class org.apache.kafka.clients.consumer.CooperativeStickyAssignor]
        - incremental Reblancing 
            - reassigns a small subset from the partitions 
            - and it does not stop the reading from partitions 
- [ ] Working with Wikimedia - consuming large data 
    - GET - url 
    - POST 
    - PUT 
    - DELETE 
    -open api spec 
        - 

```
        {"$schema":"/mediawiki/recentchange/1.0.0","meta":{"uri":"https://www.wikidata.org/wiki/Q39778840","request_id":"4a3f998e-7c05-4607-8269-0180792e8f10","id":"1efc048e-1660-4c2b-a1f2-7689ca822477","dt":"2022-05-06T09:14:37Z","domain":"www.wikidata.org","stream":"mediawiki.recentchange","topic":"eqiad.mediawiki.recentchange","partition":0,"offset":3842352781},"id":1684264481,"type":"edit","namespace":0,"title":"Q39778840","comment":"/* wbsetdescription-set:1|en */ scientific article published on August 1, 1976, Modify PubMed ID: 133707 citation data [[User:Cewbot/log/20210701/problematic articles|from NCBI, Europe PMC and CrossRef]]","timestamp":1651828477,"user":"Cewbot","bot":true,"minor":false,"patrolled":true,"length":{"old":31675,"new":31703},"revision":{"old":1633165829,"new":1633165833},"server_url":"https://www.wikidata.org","server_name":"www.wikidata.org","server_script_path":"/w","wiki":"wikidatawiki","parsedcomment":"<span dir=\"auto\"><span class=\"autocomment\">Changed English description: </span></span> scientific article published on August 1, 1976, Modify PubMed ID: 133707 citation data <a href=\"/wiki/User:Cewbot/log/20210701/problematic_articles\" title=\"User:Cewbot/log/20210701/problematic articles\">from NCBI, Europe PMC and CrossRef</a>"}
        ```
- [ ] Metrics with Kafka 
- [ ] spring boot application with kafka 
- [ ] run docker with kafka 
