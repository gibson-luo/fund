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