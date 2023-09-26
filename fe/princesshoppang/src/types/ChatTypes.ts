interface GameChat {
    type: string,
    nickname: string;
    messages: string[];
    sentAt: number;
}

interface GameChatRequest {
    nickname: string;
    message: string;
    sentAt: number;
}

interface Player {
    userId: number,
    color: string,
}
interface ChannelId {
    channelId: string
}
export type {
    GameChat,
    GameChatRequest,
    Player,
    ChannelId,
};