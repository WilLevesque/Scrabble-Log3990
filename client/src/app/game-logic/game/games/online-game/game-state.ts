import { MagicCard } from '@app/game-logic/actions/magic-card/magic-card';
import { Letter } from '@app/game-logic/game/board/letter.interface';
import { Tile } from '@app/game-logic/game/board/tile';

export interface LightPlayer {
    name: string;
    points: number;
    letterRack: Letter[];
}

export interface GameState {
    players: LightPlayer[];
    activePlayerIndex: number;
    grid: Tile[][];
    lettersRemaining: number;
    isEndOfGame: boolean;
    winnerIndex: number[];
}

export interface PlayerInfoForfeit {
    name: string;
    previousPlayerName: string;
}

export interface MagicGameState extends GameState {
    drawableMagicCards: MagicCard[];
}
