import { useNavigate } from "react-router-dom";
import Button from "../../components/Button";

const Home = () => {

  const navigate = useNavigate();

  // 매칭 페이지로 이동
  const toMatching = () => {
    navigate("/game/0fc97f82-46eb-4772-975b-ecb7a82038e2")
  };

  // 게임 시작 버튼
  const startGame = () =>{
    toMatching()
  };


  return (
    <div>
      <h1>Home</h1>
      <Button
          type="button"
          shapeType={"confirm"}
          key="0"
          onClick={() => { startGame() }}
        >
          게임 시작
        </Button>
    </div>
  );
};

export default Home;
