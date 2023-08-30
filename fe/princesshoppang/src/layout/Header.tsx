import styled from "@emotion/styled";
import Colors from "../styles/Colors";
import logoImage from "../assets/pc.png";
import { Link } from "react-router-dom";

const HeaderContainer = styled.header`
  display: flex;
  align-items: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 100px;
  background-color: ${Colors.white};
  color: white;
`;

const MobileMenu = styled.div`
  display: none;

  @media (max-width: 768px) {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    border-radius: 100px;
    padding: 5px 10px;
    margin: 10px;
    background-color: #f8f8f8;
    width: 50px;
    height: 50px;
  }
`;

const Logo = styled.img`
  width: 60px;
  height: auto;
  margin: 10px;
`;

const Nav = styled.nav`
  ul {
    display: flex;
    gap: 30px;
    min-width: 200px;

    li {
      a {
        text-decoration: none;
        font-weight: bold;
        color: ${Colors.gray};
        &:hover {
          color: ${Colors.black};
        }
      }
    }
  }

  @media (max-width: 768px) {
    display: none;
  }
`;

const Seperater = styled.div`
  flex: 1;
  margin: 10px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const TempSearchBar = styled.div`
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 20px;
  padding: 5px 10px;
  margin: 10px;
  background-color: #f8f8f8;
  width: 295px;
  height: 24px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const Seperater2 = styled.div`
  flex: 0.2;
  margin: 10px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const TempProfile = styled.div`
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 20px;
  padding: 5px 10px;
  margin: 10px;
  background-color: #f8f8f8;
  width: 222px;
  height: 60px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const TempMoblieProfile = styled.div`
  display: none;

  @media (max-width: 768px) {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    border-radius: 100px;
    padding: 5px 10px;
    margin: 10px;
    background-color: #f8f8f8;
    width: 50px;
    height: 50px;
  }
`;

const Header = () => {
  return (
    <HeaderContainer>
      <MobileMenu></MobileMenu>
      <Link to="/">
        <Logo src={logoImage} alt="로고"></Logo>
      </Link>
      <Nav>
        <ul>
          <li>
            <Link to="/">홈</Link>
          </li>
          <li>
            <Link to="/chat">채팅</Link>
          </li>
          <li>
            <Link to="/community">MBTI톡</Link>
          </li>
        </ul>
      </Nav>
      <Seperater></Seperater>
      <TempSearchBar></TempSearchBar>
      <Seperater2></Seperater2>
      <Link to="/mypage">
        <TempProfile></TempProfile>
      </Link>
      <TempMoblieProfile></TempMoblieProfile>
    </HeaderContainer>
  );
};

export default Header;
