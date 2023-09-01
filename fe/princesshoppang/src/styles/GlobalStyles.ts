import { css } from "@emotion/react";
import backgroundImage from "../assets/background.png";

const GlobalStyles = css`
  body {
    height: 100vh;
    margin: 0px;
    padding: 0px;
    background: url(${backgroundImage}) center/cover no-repeat;
    background-attachment: fixed;
    position: relative;
  }

  body::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.85);
    z-index: -1;
  }
`;

export default GlobalStyles;
