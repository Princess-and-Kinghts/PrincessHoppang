import { css } from "@emotion/react";
import backgroundImage from "../assets/background.png";

const GlobalStyles = css`
  body {
    margin: 0px;
    padding: 0px;
    background: url(${backgroundImage}) center/cover no-repeat;
    height: 100vh;
    position: relative;
  }

  body::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.85); /* 85% 투명한 흰색 배경 */
    z-index: -1;
  }
`;

export default GlobalStyles;
