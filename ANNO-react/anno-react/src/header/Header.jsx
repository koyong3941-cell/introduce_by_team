import { Bar, Brand, Dot, Inner, Nav, NavLink } from "./Header.styles";

const Header = () => {
  return (
    <Bar>
      <Inner>
        <Brand href="/board">
          ANON
          <Dot />
        </Brand>

        <Nav>
          <NavLink href="/board">익명게시판</NavLink>
        </Nav>
      </Inner>
    </Bar>
  );
};

export default Header;
