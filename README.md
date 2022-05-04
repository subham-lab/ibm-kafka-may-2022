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
