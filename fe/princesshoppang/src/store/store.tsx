import { createStore, atom } from "jotai"
import { getNationType, getTokenStatusType } from "@/types/common/apiReturnTypes"

export const mainStore = createStore()

const webSocketMessagesState = atom("");

export{
    webSocketMessagesState,
}