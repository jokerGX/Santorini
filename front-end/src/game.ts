type State = "newgame" | "init" | "move" | "build" | "end";

interface GameState {
    cells: Cell[];
    currentPlayer: number;
    currState: State;
    workers_0: Worker[];
    workers_1: Worker[];
    playerOneRole: string;
    playerTwoRole: string;
  }
  
  interface Cell {
    x: number;
    y: number;
    height: number;
    hasWorker: boolean;
    text: string;
  }

  interface Worker {
    id: number;
    Cell: Cell;
  }

  interface Player{
    id : number;
    color: string;
  }
  
  export type {State, GameState, Cell, Worker, Player}