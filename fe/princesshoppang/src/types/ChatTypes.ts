interface GameChat {
    nickname: string;
    message: string;
}

interface Player {
    userId: number,
    color: string,
}

export type {
    GameChat,
    Player,
};