This project is a simple Proof Of Concept:
 - to use Hash-based Message Authentication Code between frontend and backend. 
 - allowing backend to receive CORS requests http://localhost:3000 from web brawser.

The secret-key is only for the tests. 
In real Prod environment NEVER!! expose Secrets in frontend code!! Or hardcode them to Backend code too.

For implementing HMAC calculation use: crypto.**createHmac**("sha256", key);  
Without key use: crypto.**createHash**("sha256");

To run frontend:
in commandline to JsonHashComparator/ReactFrontend/frontend/ and run command:
"npm start"