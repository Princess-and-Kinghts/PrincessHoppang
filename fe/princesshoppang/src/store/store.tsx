import { atom } from "jotai"
// import { getNationType, getTokenStatusType } from "@/types/common/apiReturnTypes"

// export const mainStore = createStore();

const webSocketMessagesState = atom("");
const webSocketConnectionState = atom(false);

export{
    webSocketMessagesState,
    webSocketConnectionState,
}