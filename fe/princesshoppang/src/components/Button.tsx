import { confirmButtonStyle, pillButtonStyle } from "../styles/ButtonStyles";

type ButtonProps = {
  children: string;
  type: string;
};

const Button = ({ children, type }: ButtonProps) => {
  let buttonStyle;

  if (type === "basic") {
    buttonStyle = confirmButtonStyle;
  } else if (type === "pill") {
    buttonStyle = pillButtonStyle;
  }

  return <button css={[buttonStyle]}>{children}</button>;
};

export default Button;
