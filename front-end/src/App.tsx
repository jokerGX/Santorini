import React from 'react';
import './App.css'; // import the css file to enable your styles.
import {GameState, Cell} from './game';
import BoardCell from './Cell';
import { BrowserRouter as Router, Routes, Route, Navigate, useNavigate} from 'react-router-dom';
import RoleSelection from './RoleSelection';
/**
 * Define the type of the props field for a React component
 */
interface Props {}

/**
 * Using generics to specify the type of props and state.
 * props and state is a special field in a React component.
 * React will keep track of the value of props and state.
 * Any time there's a change to their values, React will
 * automatically update (not fully re-render) the HTML needed.
 * 
 * props and state are similar in the sense that they manage
 * the data of this component. A change to their values will
 * cause the view (HTML) to change accordingly.
 * 
 * Usually, props is passed and changed by the parent component;
 * state is the internal value of the component and managed by
 * the component itself.
 */
class App extends React.Component<Props, GameState> {
  private initialized: boolean = false;
  /**
   * @param props has type Props
   */
  constructor(props: Props) {
    super(props)
    /**
     * state has type GameState as specified in the class inheritance.
     */
    this.state = { cells: [], 
                   currentPlayer: -1,
                   currState: "newgame",
                   workers_0 : [],
                   workers_1 : [],
                   playerOneRole: '',
                   playerTwoRole: '',}
  }

  /**
   * This function is called when the user clicks on the "New Game" button.
   * 
   * @see https://reactjs.org/docs/handling-events.html
   */
  handleNewGame = () => {
    window.location.href = '/';
  }

  /**
   * This function is called when the user clicks on the "Start Game" button.
   */
  handleRolesSelected = (playerOneRole: string, playerTwoRole: string) => {
    this.setState({ playerOneRole: playerOneRole, 
                    playerTwoRole: playerTwoRole 
                  } , () => {
                    this.backEndInit();
                  })
  }

  /**
   * Use arrow function, i.e., () => {} to create an async function,
   * otherwise, 'this' would become undefined in runtime. This is
   * just an issue of Javascript.
   */
  passAction = async () => {
    const response = await fetch(`/pass?state=${this.state.currState}`);
    const json = await response.json();
    this.setState({ cells: json['cells']
                    , currentPlayer: json['currPlayer']
                    , currState: json['currState']
                    , workers_0: json['workers_0']
                    , workers_1: json['workers_1']});
  }

  /**
   * Use arrow function, i.e., () => {} to create an async function,
   * otherwise, 'this' would become undefined in runtime. This is
   * just an issue of Javascript.
   */
  backEndInit = async () => {
    const playerOneRole = this.state.playerOneRole;
    const playerTwoRole = this.state.playerTwoRole;
    const response = await fetch(`/newgame?playerOneRole=${playerOneRole}&playerTwoRole=${playerTwoRole}`);
    const json = await response.json();
    this.setState({ cells: json['cells']
                    , currentPlayer: json['currPlayer']
                    , currState: json['currState']
                    , workers_0: json['workers_0']
                    , workers_1: json['workers_1']});
  }


  /**
   * Create the instruction based on the current state.
   */
  createInstruction(): React.ReactNode {
    const currState = this.state.currState;
    const PlayerRole = this.state.currentPlayer === 0 ? this.state.playerOneRole : this.state.playerTwoRole;
    switch(currState) {
      case "newgame":
        return (
          <div>
            <p>Click "New Game" to start a new game.</p>
          </div>
        )
      case "init":
        return (
          <div>
            <p>Player {this.state.currentPlayer}({PlayerRole}) choose a worker to place.</p>
          </div>
        )
      case "move":
        return (
          <div>
            <p>Player {this.state.currentPlayer}({PlayerRole}) choose a worker to move.</p>
          </div>
        )
      case "build":
        return (
          <div>
            <p>Player {this.state.currentPlayer} ({PlayerRole}) select a cell to build.</p>
          </div>
        )
      case "end":
        return (
          <div>
            <p>Player {this.state.currentPlayer} ({PlayerRole}) wins!</p>
          </div>
        )
      default:
        return (
          <div>
            <p>Click "New Game" to start a new game.</p>
          </div>
        )
    }
  }


  /**
   * play will generate an anonymous function that the component
   * can bind with.
   * @param x 
   * @param y 
   * @returns 
   */
  play(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      const GameState = this.state.currState;
      const role = this.state.currentPlayer === 0 ? this.state.playerOneRole : this.state.playerTwoRole;
      let response = null;
      let json;
      console.log("before " + this.state.currState);
      switch(GameState) {
        case "newgame":
          alert("Please start a new game!");
          break;

        case "init":
          response = await fetch(`/addWorker?x=${x}&y=${y}`)
          break;

        case "move":
          response = await fetch(`/move?x=${x}&y=${y}`)
          break;

        case "build":
          response = await fetch(`/build?x=${x}&y=${y}&role=${role}`)
          break;

        default:
          alert("Please start a new game!");
      }
      if (response != null) {
        json = await response.json();
        this.setState({ cells: json['cells'] 
                        , currentPlayer: json['currPlayer']
                        , currState: json['currState']
                        , workers_0: json['workers_0']
                        , workers_1: json['workers_1']});
      }
      console.log(this.state);
    }
  }

  /**
   * Logic to create a cell on the board.
   * @param cell
   * @param index
   */
  createCell(cell: Cell, index: number): React.ReactNode {
      /**
       * key is used for React when given a list of items. It
       * helps React to keep track of the list items and decide
       * which list item need to be updated.
       * @see https://reactjs.org/docs/lists-and-keys.html#keys
       */
      if (cell.height === 4) {
        return (
          <BoardCell cell={cell} workers_0={this.state.workers_0} workers_1={this.state.workers_1} ></BoardCell>
        )
      }
        return (
          <div key={index}>
            <a href='/' onClick={this.play(cell.x, cell.y)}>
              <BoardCell cell={cell} workers_0={this.state.workers_0} workers_1={this.state.workers_1} ></BoardCell>
            </a>
          </div>
        )
  }

  /**
   * This function will call after the HTML is rendered.
   * We update the initial state by creating a new game.
   * @see https://reactjs.org/docs/react-component.html#componentdidmount
   */
  componentDidMount(): void {
    /**
     * setState in DidMount() will cause it to render twice which may cause
     * this function to be invoked twice. Use initialized to avoid that.
     */
    if (!this.initialized) {
      this.backEndInit();
      this.initialized = true;
    }
  }

  /**
   * The only method you must define in a React.Component subclass.
   * @returns the React element via JSX.
   * @see https://reactjs.org/docs/react-component.html
   */
  render(): React.ReactNode {
    /**
     * We use JSX to define the template. An advantage of JSX is that you
     * can treat HTML elements as code.
     * @see https://reactjs.org/docs/introducing-jsx.html
     */
    return (
      <Router>
        <Routes>
          <Route path="/" element={<RoleSelection onRolesSelected={this.handleRolesSelected} />} />
          <Route path="/game" element={
          <div>
            <div id="instructions">{this.createInstruction()}</div>
            <div id="board">
              {this.state.cells.map((cell, i) => this.createCell(cell, i))}
            </div>
            <div id="bottombar">
              <button onClick={this.handleNewGame}>New Game</button>
              <button onClick={this.passAction}>Pass</button>
            </div>
          </div>
          } />
        </Routes>
      </Router>
    );
  }
}

export default App;
