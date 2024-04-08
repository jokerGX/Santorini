package com.Bruce.app;

import java.io.IOException;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

public class App extends NanoHTTPD{
    
    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private Game game;
    private boolean selected = false;
    private Worker worker = null;
    private int dBuildTime = 0;
    private int hBuildTime = 0;
    private static final int PORT = 8080;
    /**
     * start the server
     * @throws IOException
     */
    public App() throws IOException {
        super(PORT);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    /**
     * handle the request
     * @param session the session of the request
     */
    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        GameState a = new GameState(game, State.newgame);
        if (uri.equals("/newgame")){
            String playerOneRole = params.get("playerOneRole");
            String playerTwoRole = params.get("playerTwoRole");
            game = new Game();
            game.initGame(playerOneRole, playerTwoRole);
            System.out.println("\nHere we are, the game is initialized, playerOneRole is " + playerOneRole + ", playerTwoRole is " + playerTwoRole + "\n");
            a = new GameState(game, State.init);
        }else if(uri.equals("/addWorker")){
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            int id = game.getCurrPlayer();
            int numOfWorker = game.getPlayers().get(id).getNumOfWorker();
            game.getPlayers().get(id).addWorker(game.getCell(x, y));
            int newNumOfWorker = game.getPlayers().get(id).getNumOfWorker();

            if(game.getPlayers().get(id).getNumOfWorker() == 2){
                game.switchPlayer();
                id = game.getCurrPlayer();
                game.switchPlayer();
                // check whehter the other player has 2 workers
                if(game.getPlayers().get(id).getNumOfWorker() == 2){
                    game.switchPlayer();
                    a = new GameState(game, State.move);
                    return newFixedLengthResponse(a.toString());
                }
            }

            if (numOfWorker != newNumOfWorker){
                game.switchPlayer();
            }
            a = new GameState(game, State.init);

        }else if(uri.equals("/move")){
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            int id = game.getCurrPlayer();

            if (selected == false){
                worker = game.getPlayers().get(id).getWorker(x, y);
                // check it is a valid worker
                if (worker != null){
                    selected = true;
                    
                }
                a = new GameState(game, State.move);
                return newFixedLengthResponse(a.toString());
            }

            Cell originCell = worker.getCurrentCell();
            game.getPlayers().get(id).moveWorker(worker, game.getCell(x, y));

            // check whether the worker has moved
            if ((worker.getCurrentCell().getX() == x && worker.getCurrentCell().getY() == y) && originCell != worker.getCurrentCell()){
                selected = false;
                Player winner = game.checkWin();
                if (winner != null){
                    System.out.println("win\n");
                    a = new GameState(game, State.end);
                }else{
                    a = new GameState(game, State.build);
                }
            }else{
                a = new GameState(game, State.move);
            }
            
        }else if(uri.equals("/build")){
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            String role = params.get("role");
            int id = game.getCurrPlayer();
            int initHeight = game.getCell(x, y).getHeight();
            Cell targetCell = game.getCell(x, y);
            if (dBuildTime == 0 && hBuildTime == 0){
                game.getPlayers().get(id).buildTower(worker, targetCell);
                if (targetCell.getHeight() == initHeight){
                    a = new GameState(game, State.build);
                } else {
                    if (role.equals("Demeter") || role.equals("Hephaestus")){
                        if (role.equals("Demeter")){
                            dBuildTime = 1;
                        }else{
                            hBuildTime = 1;
                        }
                        a = new GameState(game, State.build);
                    }else{
                        game.switchPlayer();
                        a = new GameState(game, State.move);
                    }
                }
            }else{
                if (role.equals("Demeter")){
                    game.getPlayers().get(id).buildTower(worker, targetCell);
                    if (targetCell.getHeight() == initHeight){
                        a = new GameState(game, State.build);
                        return newFixedLengthResponse(a.toString());
                    }else{
                        dBuildTime = 0;
                        game.switchPlayer();
                        a = new GameState(game, State.move);
                        return newFixedLengthResponse(a.toString());
                    }
                }else if (role.equals("Hephaestus")){
                    game.getPlayers().get(id).buildTower(worker, targetCell);
                    if (targetCell.getHeight() == initHeight){
                        a = new GameState(game, State.build);
                        return newFixedLengthResponse(a.toString());
                    }else{
                        hBuildTime = 0;
                        game.switchPlayer();
                        a = new GameState(game, State.move);
                        return newFixedLengthResponse(a.toString());
                    }
                }else{
                    dBuildTime = 0;
                    hBuildTime = 0;
                    game.switchPlayer();
                    a = new GameState(game, State.move);
                }
                
            }
        }else if(uri.equals("/pass")){
            String state = params.get("state");
            if (state.equals("build")){
                if(dBuildTime == 1){
                    game.getPlayers().get(game.getCurrPlayer()).setPrevBuildCellToNull();
                    dBuildTime = 0;
                    game.switchPlayer();
                    a = new GameState(game, State.move);
                    return newFixedLengthResponse(a.toString());
                }else if(hBuildTime == 1){
                    game.getPlayers().get(game.getCurrPlayer()).setPrevBuildCellToNull();
                    hBuildTime = 0;
                    game.switchPlayer();
                    a = new GameState(game, State.move);
                    return newFixedLengthResponse(a.toString());
                }
            }
            a = new GameState(game, State.valueOf(state));
        }
        return newFixedLengthResponse(a.toString());
    }
} 
