import buttonStyles from "../styles/ButtonStyles";

type ButtonProps = {
  children: string;
  // 스타일 추가한다면 여기도 추가 필요
  type: "confirm" | "pill";
};

const Button = ({ children, type }: ButtonProps) => {
  return <button css={buttonStyles[type]}>{children}</button>;
};

export default Button;
