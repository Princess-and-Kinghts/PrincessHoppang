import { atom } from "jotai"


const matchingModalAtom = atom(false);
const matchedAtom = atom(false);
const introAtom = atom(false);
const gameChatAtom = atom(false);
const voteAtom = atom(false);
const resultAtom = atom(false);

const channelIdAtom = atom("");
const titleAtom = atom("");
const playersAtom = atom([]);


const myNicknameAtom = atom("");
const myColorAtom = atom("")

export{
    matchingModalAtom,
    matchedAtom,
    introAtom,
    gameChatAtom,
    resultAtom,
    voteAtom,

    channelIdAtom,
    titleAtom,
    playersAtom,

    myNicknameAtom,
    myColorAtom,
}