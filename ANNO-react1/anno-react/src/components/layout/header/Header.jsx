import { useNavigate } from "react-router-dom";
import { Bar, Inner, Brand, Dot, Nav, NavLink, Login } from "./Header.styles";

const Header = () => {
  const navi = useNavigate();

  return (
    <Bar>
      <Inner>
        <Brand onClick={() => navi("/")}>ANNO</Brand>
        <Nav>
          <NavLink onClick={() => navi("/boards")}>게시판</NavLink>
        </Nav>
      </Inner>
    </Bar>
  );
};

export default Header;
