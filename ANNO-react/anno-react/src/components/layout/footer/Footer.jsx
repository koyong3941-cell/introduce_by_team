import { Wrap, Inner, Brand, Dot, Links, Copy } from "./Footer.styles";

const Footer = () => {
  return (
    <Wrap>
      <Inner>
        <Brand>Pre-Semi</Brand>
        <Links>
          <a href="http://localhost:5174/#">ANNO</a>
          <a href="#">이용약관</a>
          <a href="#">고객센터</a>
        </Links>
        <Copy>
          © {new Date().getFullYear()} ANNO-Semi. All rights reserved.{" "}
        </Copy>
      </Inner>
    </Wrap>
  );
};

export default Footer;
