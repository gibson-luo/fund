# Assignment for coding challenge

Author: Zherui (Gibson) Luo

## Getting Started

Download the latest version package from the releases.

[Check Out Versions](https://github.com/gibson-luo/fund/releases)

Unzip the file and run following commands.

```bash
# build with Maven
mvn install -DskipTests -f pom.xml

# build docker image
docker build -t api-docker.jar .

# run with docker-compose
docker-compose up -d
```
After the server is running, open the link with Browser.
```
http://127.0.0.1:8080/swagger-ui/index.html
```
## REST API
### POST v1/fund/importData
```
http://127.0.0.1:8080/v1/fund/importData
```
Add a file to form-data with the 'file' param name, and a type of File.

### GET v1/rule/load
```
http://127.0.0.1:8080/v1/rule/load
```
Refresh the rules of limitation from DB.   

## About the design
### Rule engine for limits
Considering that there are multiple rules, and new rules may need to be added in the future. 
Also, the applying rules may be able to change on the fly(e.g. changing the loadsPerDay limit from 3 to 2).
I have separated this part in a RuleEngine.java and stored the rules in DB. 

The rules will be loaded once when the application launching. 
If it needs to update any of the existing rules, you need to call the API v1/rule/load to refresh them. 
In advance, we can have a Zookeeper cluster to hold those rules, and provide a portal to allow you to change the rules. 
Once the rules changed, Zookeeper will push the data to API service on the fly without any downtime.

### Decouple rule classes
To make it easier to maintain, all the rules (under the rules.strategies package) are separated class with implementing Strategy interface.
Each Rule is injecting to RuleEngine by using @Resource and @Service with key. In this way, you can easily to plug-in and plug-out any of rules.
```java
// RuleEngine.java
@Resource
public void setRuleMap(Map<String, Strategy> ruleMap) {
    RuleEngine.ruleMap = ruleMap;
}

// MaximumLoadsPerDayRule.java
@Service("maximumLoadsPerDay")
public class MaximumLoadsPerDayRule implements Strategy {
    
}
```
### Calculating daily data for each user
Although we have stored every load in the load_fund_record table, lots of database access is still required for each load verification. 
In that case, I create another table load_calculation_daily to calculate daily data for each user. For each verification, we just need to fetch few of data. 
```
CREATE TABLE "load_calculation_daily" (
  "id" int8 NOT NULL DEFAULT nextval('load_calculation_daily_id_seq'::regclass),
  "customer_id" varchar,
  "load_amount" numeric,
  "load_times" int,
  "date" date,
  PRIMARY KEY ("id")
);
```


