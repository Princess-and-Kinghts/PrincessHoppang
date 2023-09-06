import { atom } from "jotai"
// import { getNationType, getTokenStatusType } from "@/types/common/apiReturnTypes"

// export const mainStore = createStore();

const players = atom("");
const me = atom(0);

export{
    players,
    me,
}