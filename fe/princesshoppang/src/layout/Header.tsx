import { css } from "@emotion/react";
import Colors from "../styles/Colors";
import logoImage from "../assets/pc.png";
import { Link } from "react-router-dom";

const headerContainer = css`
  display: flex;
  align-items: center;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 100px;
  padding: 0px 10px;
  background-color: ${Colors.white};
  color: white;
`;

const mobileMenu = css`
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

const logo = css`
  width: 60px;
  height: auto;
  margin: 10px;
`;

const nav = css`
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

const seperater = css`
  flex: 1;
  margin: 10px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const tempSearchBar = css`
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

const seperater2 = css`
  flex: 0.2;
  margin: 10px;

  @media (max-width: 768px) {
    display: none;
  }
`;

const tempProfile = css`
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

const tempMoblieProfile = css`
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
    <header css={headerContainer}>
      <div css={mobileMenu}></div>
      <Link to="/">
        <img css={logo} src={logoImage} alt="로고"></img>
      </Link>
      <nav css={nav}>
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
      </nav>
      <div css={seperater}></div>
      <div css={tempSearchBar}></div>
      <div css={seperater2}></div>
      <Link to="/mypage">
        <div css={tempProfile}></div>
      </Link>
      <div css={tempMoblieProfile}></div>
    </header>
  );
};

export default Header;
