import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./pages/HomePage/Home";
import Game from "./pages/GamePage/Game";
import Chat from "./pages/ChatPage/Chat";
import Community from "./pages/CommunityPage/Community";
import Profile from "./pages/MyPage/Profile";
import GlobalStyles from "./layout/GlobalLayout.tsx";
import PostDetail from "./pages/CommunityPage/PostDetail.tsx";

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/game" element={<Game />} />
          <Route path="/chat" element={<Chat />} />
          <Route path="/community" element={<Community />} />
          <Route path="/community/post/:id" element={<PostDetail />} />
          <Route path="/mypage" element={<Profile />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
