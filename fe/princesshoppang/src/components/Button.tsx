import buttonStyles from "../styles/ButtonStyles";

type ButtonProps = {
  children: string;
  // 스타일 추가한다면 여기도 추가 필요
  shapeType: "confirm" | "pill" | "selectedPill";
  onClick?: () => void;
  type?: "submit" | "button" | "reset";
};

const Button = ({
  children,
  shapeType,
  type = "submit",
  onClick,
}: ButtonProps) => {
  return (
    <button css={buttonStyles[shapeType]} type={type} onClick={onClick}>
      {children}
    </button>
  );
};

export default Button;