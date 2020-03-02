**MongoDB Blog Post API**

A RESTful http java blog post api built using Dagger2 and Mongo DB 4.2.3

Default Address is Localhost and Port is 8080


**Prerequisites:**

1. Install MongoDB Community Server https://www.mongodb.com/download-center/community

2. (Optional) Install MongoDB Compass https://www.mongodb.com/products/compass

3. (Optional) Install Postman https://www.postman.com/downloads/

4. Install Java JDK8 https://www.oracle.com/java/technologies/javase-downloads.html

5. Install Maven http://maven.apache.org/download.cgi

6. (Optional) Install Eclipse https://www.eclipse.org/downloads/




**How to compile and run:**

**CMD Line:**

1. Run "mvn compile" in the root directory

2. Run the project by running "mvn exec:java"

**Eclipse:**

1. File -> Import -> Maven -> Existing Maven Projects

2. Right click the project -> Maven Build...

3. Input compile exec:java in the Goals input box

4. Click apply then run/close

5. Run the project by pressing the green play button

**Intellij**

1. Import project

2. Import project from external model -> Maven

3. Next -> Next -> Next -> Finish

4. Add Configuration -> + Button -> Maven

5. Name the configuration and input exec:java in the Command Line box

6. Apply -> Ok

7. Run the project by pressing the green play button





**How to hit server endpoints:**

**Command line:**

curl -X POST http://localhost:8080/api/v1/endpoint/ --data \
’{ "key": "value", "other": "thing" }’

The -X flag specifies the REST action you wish to use.
And the --data flag specifies the body of the request

**Postman:**

1. Open postman and select request you'd like

2. Enter the request URL (Ex: http://localhost:8080/api/v1/post)

3. Open the request body tab and select raw

4. Enter the request body data into the test box

5. Click send

**Python:**

import requests
d = { "key": "value" }
requests.put(’http://localhost:8080/api/v1/endpoint’, json=d)

The dictionary is the desired JSON request body
Can change requests.put(...) to requests.get(...) and requests.post(...)



**Commands:**

**PUT /api/v1/post**

JSON Request Body:
{
"title": String,
"author": String,
"content": String,
"tags": [
String,
String,
...
]
}

JSON Response Body:
{
"_id": String
}



**GET /api/v1/post**

JSON Request Body:
{
"title": String
}
OR
{
"_id": String
}
OR
{
"_id": String, 
"title": String
}

JSON Response Body:
[
{
"_id": {
"$oid": String
},
"title": String,
"tags": [
String,
String,
...
],
"author": String,
"content": String
},
...
]



**DELETE /api/v1/post**

JSON Request Body:
{
"_id": String
}

JSON Response Body:
N/A



**Responses:**
- 200 OK for a successful add
- 400 BAD REQUEST if the request body is improperly formatted or missing required information
- 500 INTERNAL SERVER ERROR if save or add was unsuccessful but the request was valid
- 405 METHOD NOT ALLOWED if called with something other than GET, PUT, DELETE
