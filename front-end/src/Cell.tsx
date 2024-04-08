import React from 'react';
import { Cell, Worker} from './game';

interface Props {
  cell: Cell
  workers_0: Worker[]
  workers_1: Worker[]
}

/**
 * BoardCell is a React component that renders a cell on the board.
 * It takes in a cell and two lists of workers as props.
 */
class BoardCell extends React.Component<Props> {
  render(): React.ReactNode {
    let hasWorker = '';
    const height = this.props.cell.height;
    let Display = '';

    if (this.props.cell.hasWorker) {
      for (let i = 0; i < this.props.workers_0.length; i++) {
        const x = this.props.workers_0[i].Cell.x;
        const y = this.props.workers_0[i].Cell.y;
        if (x === this.props.cell.x && y === this.props.cell.y) {
          Display = 'Player 0';
          hasWorker = 'hasWorker0';
        }
      }

      for (let i = 0; i < this.props.workers_1.length; i++) {
        const x = this.props.workers_1[i].Cell.x;
        const y = this.props.workers_1[i].Cell.y;
        if (x === this.props.cell.x && y === this.props.cell.y) {
          Display = 'Player 1';
          hasWorker = 'hasWorker1';
        }
      }
    }

    if (height === 4) {
      Display = "O";
      hasWorker = 'maxHeight';
    }
    
    this.props.cell.text = Display;
    let newheight = this.props.cell.height;
    if (newheight === 4) newheight = 3;

    return (
      <div className={`cell ${hasWorker}`}>
      <p>
          {'['.repeat(newheight)}
          {this.props.cell.text}
          {']'.repeat(newheight)}
      </p>
      </div>
    )
  }
}

export default BoardCell;