# MessagingEngine
Interesting assignment to implement small message processing application

Scope:

- Messages will be processed in the order they are received. 
- One message will represent to ONE Message type. If one message is getting qualified for two message type format then 1st message type matching will be consider  for executing operation.
- One message type represents one operation. If required System needs be extended by adding chain of operation for one message type.
- Value would be in penny (p). If multiple currency support required then need to consider impact on entire application (specially on message pattern, currency conversion, operations and reports)
- Zero or negative quantity and value is currently not allowed as input.

Way to Test:

- Need to run my.assignment.messaging.service.MessagingEngineTest from Command prompt with argument of file name including file path.
- If no file name is provided then sample.txt file will be passed to the program for input provided execution directory is project base folder (MessagingEngine).
- Product type and it's plural form is maintained in products.txt file.
- There are two jUnit test cases are provided in test folder of src.
