import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./pages/HomePage/Home";
import Game from "./pages/GamePage/Game";
import Chat from "./pages/ChatPage/Chat";
import Community from "./pages/CommunityPage/Community";
import Profile from "./pages/MyPage/Profile";

import WebSocketProvider from "./utils/WebSocketContext";
import MatchingModal from "./pages/GamePage/MatchingModal";
import { useAtomValue } from "jotai";
import { matchingModalAtom } from "./store/GameState";

function App() {
  const matchingModal = useAtomValue(matchingModalAtom);

  return (
    <WebSocketProvider>
    <BrowserRouter>
        <div className="App">
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/game/:channelId" element={<Game />} />
            <Route path="/chat" element={<Chat />} />
            <Route path="/community" element={<Community />} />
            <Route path="/mypage" element={<Profile />} />
          </Routes>

          { matchingModal ? <MatchingModal></MatchingModal>: null}
        </div>
      </BrowserRouter>
    </WebSocketProvider>
  );
}
export default App;
