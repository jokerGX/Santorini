## Set Up Backend Server ##
Either run the Java backend by using your IDE or by typing 

```
mvn exec:exec
```
in the Santorini(back-end) folder. This will start the Java server at http://localhost:8080.

## Set Up Frontend Server ##
In the front-end folder, run

```
npm install
npm start
```
This will start the front-end server at http://localhost:3000. You can update the front-end code as the server is running in the development mode (i.e., npm start). It will automatically recompile and reload.

## Play the Game ##
The gameplay is intuitive and easy to understand. However, it's important to note a specific rule during the 'move worker' phase: once you select a worker, you are committed to moving that particular worker. In other words, after selecting a valid worker, you cannot change your selection to a different worker. Your next click should be the cell where you intend to move the chosen worker. If you click on another worker after making your selection, the system will not change your chosen worker; instead, it will reject the action. Therefore, choose your worker carefully before deciding on their move.



