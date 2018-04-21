Mimic ReadMe
------------

Mimic is a learning virtual service. Use Mimic to simulate the behaviour of any http service.


Install and Run
---------------

Simply download and double-click the mimic.jar file. Yo may also start Mimic from the command line:

java -jar mimic.jar

Communicate with mimic using a browser or any http application.

Mimic uses port 8080, by default. Use the command below to start Mimic on port 80:

java -jar mimic.jar -p 80


Commands
--------

Send any http command to Mimic. It will send a response back or ask for a response. For example:

http://localhost:8080/add?value1=10&value2=20

Mimic will respond with a form for filling out the response. Mimic will auto detect the mime type.

Trigger the same request again and Mimic will respond with your previously defined response.


Unlearn the previous request/response using the "unlearn" command:

http://localhost:8080/unlearn


Define a response before triggering a request to prevent the form from appearing:

http://localhost:8080/LearnNextResponse?text=Hello

or by specifying the mime type:

http://localhost:8080/LearnNextResponse?text=<value>1</value>&mime=application/xml


Close Mimic by using the command:

http://localhost:8080/KillMimic


Release Notes:

Version 0.1:
- Learns responses to requests with or without using the form.
- May unlearn a request/response.
- Does not store learned request/responses to file.
