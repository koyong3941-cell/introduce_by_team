import { Brand, Copy, Dot, Inner, Links, Wrap } from "./Footer.styles";

const Footer = () => {
  return (
    <Wrap>
      <Inner>
        <Brand>
          ANON
          <Dot />
        </Brand>
        <Links>
          <a href="/notice">익명게시판</a>
        </Links>
        <Copy>익명성을 존중하는 자유 커뮤니티~</Copy>

        <Copy>© 2026 ANON. All rights reserved.</Copy>
      </Inner>
    </Wrap>
  );
};
export default Footer;
