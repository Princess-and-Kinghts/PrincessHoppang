import { useState } from "react";
import Button from "../../components/Button";
import MBTITypes from "../../styles/MBTITypes";

type CategoryTypeProps = {
  value: string;
  onChange: (selectedCategory: string) => void;
};

const CategoryType = ({ onChange }: CategoryTypeProps) => {
  const [selectedCategory, setSelectedCategory] = useState("");

  return (
    <div>
      {MBTITypes.map((item, idx) => (
        <Button
          type="button"
          shapeType={selectedCategory == item ? "selectedPill" : "pill"}
          key={idx}
          onClick={() => {
            setSelectedCategory(item);
            onChange(item);
          }}
        >
          {item}
        </Button>
      ))}
    </div>
  );
};

export default CategoryType;
