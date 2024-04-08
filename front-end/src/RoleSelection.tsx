
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

type RoleSelectionProps = {
    onRolesSelected: (playerOneRole: string, playerTwoRole: string) => void;
};

const RoleSelection: React.FC<RoleSelectionProps> = ({ onRolesSelected }) => {
  const [playerOneRole, setPlayerOneRole] = useState('');
  const [playerTwoRole, setPlayerTwoRole] = useState('');
  const navigate = useNavigate();
  
  /**
   * This function is called when the user clicks on the "Start Game" button.
   */
  const handleStartGame = () => {
    //console.log(`here prints: Player One: ${playerOneRole}, Player Two: ${playerTwoRole}`);
    onRolesSelected(playerOneRole, playerTwoRole);
    navigate('/game');
  };

  return (
    <div className="role-selection-container">
      <h2>God Power</h2>
      <div>
        <label>Player Zero Power:</label>
        <select onChange={(e) => setPlayerOneRole(e.target.value)} value={playerOneRole}>
          <option value="">Select Power</option>
          <option value="Human">Human</option>
          <option value="Demeter">Demeter</option>
          <option value="Hephaestus">Hephaestus</option>
          <option value="Minotaur">Minotaur</option>
          <option value="Pan">Pan</option>
        </select>
      </div>
      <div>
        <label>Player One Power:</label>
        <select onChange={(e) => setPlayerTwoRole(e.target.value)} value={playerTwoRole}>
        <option value="">Select Power</option>
          <option value="Human">Human</option>
          <option value="Demeter">Demeter</option>
          <option value="Hephaestus">Hephaestus</option>
          <option value="Minotaur">Minotaur</option>
          <option value="Pan">Pan</option>
        </select>
      </div>
      <button onClick={handleStartGame} disabled={!playerOneRole || !playerTwoRole}>Start Game</button>
    </div>
  );
};

export default RoleSelection;

