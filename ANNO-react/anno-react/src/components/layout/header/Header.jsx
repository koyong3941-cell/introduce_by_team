import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Bar, Inner, Brand, Nav, NavLink } from "./Header.styles";

const Header = () => {
  const navi = useNavigate();
  const [count, setCount] = useState(0);

  const handleBrandClick = () => {
    const next = count + 1;
    setCount(next);

    if (next === 10) {
      setCount(0);
      navi("/login");
      return;
    }

    // 1~4번은 항상 메인으로
    navi("/");
  };

  return (
    <Bar>
      <Inner>
        <Brand onClick={handleBrandClick}>ANNO</Brand>

        <Nav>
          <NavLink onClick={() => navi("/boards")}>게시판</NavLink>
        </Nav>
      </Inner>
    </Bar>
  );
};

export default Header;