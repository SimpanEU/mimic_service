Mimic ReadMe
------------

Mimic is a learning virtual service. Use Mimic to simulate the behaviour of any http service.


Install and Run
---------------

Simply download and double-click the mimic.jar file. Yo may also start Mimic from the command line:

java -jar mimic.jar

Communicate with Mimic using a browser or any http application.

Mimic uses port 8080, by default. Use the command below to start Mimic on port 80:

java -jar mimic.jar -p 80


Commands
--------

Send any http command to Mimic. It will send a response back or ask for a response. For example:

http://localhost:8080/add?value1=10&value2=20

Mimic will respond with a form for filling out the response. Mimic will auto detect the mime type.

Trigger the same request again and Mimic will respond with your previously defined response.

Mimic will learn to respond to an unknown request based on the pattern of previous request/responses.


Use the command RelearnResponse to change a response to the last request:

http://localhost:8080/RelearnResponse

Use the command AddResponse to add a new response (and still remember the sequence of previous responses)
to the last request:

http://localhost:8080/AddResponse


Unlearn the previous response using the UnlearnResponse command:

http://localhost:8080/UnlearnResponse

or unlearn all responses using:

http://localhost:8080/UnlearnAllResponses


Define a response before triggering a request to prevent the form from appearing:

http://localhost:8080/LearnNextResponse?text=Hello

or by specifying the mime type:

http://localhost:8080/LearnNextResponse?text=<value>1</value>&mime=application/xml


Reset the simulated application state (to repeat all sequences again) using:

http://localhost:8080/ResetState


View all requests using the ViewRequests command:

http://localhost:8080/ViewRequests


Close Mimic and save the learned responses by using the command:

http://localhost:8080/KillMimic


Release Notes:

Version 0.5:
- Fixed problem with blank responses.
- Corrected problem when not specifying a value.
- Fixed problem learning a longer path.
- More informative responses than just OK.
- Click on link in ViewRequests list to view response.
- Specify the mime type in a drop-down instead of auto detecting.

Version 0.4:
- Fix problem when trying to unlearn a repeated request.
- Change relearn into RelearnResponse and add AddResponse.
- Change unlearn to UnlearnResponse and unlearnAll to UnlearnAllResponses.
- Correct problem when not specifying a value, for example /test?param.
- Handle responses that begins or ends with the same characters.
- Learns complex responses faster.
- View all responses that Mimic have learned.

Version 0.3:
- Learns to respond to a request pattern.
- Unlearn works like relearn but without learning a new response.

Version 0.2:
- Learns a sequence of responses to the same request.
- New commands: relearn, unlearnAll and resetState
- Saves the learned responses to the "brain" file when the killMimic command is executed.
- Loads learned responses from the "brain" file if it exists.

Version 0.1:
- Learns responses to requests with or without using the form.
- May unlearn a request/response.
- Does not store learned request/responses to file.
