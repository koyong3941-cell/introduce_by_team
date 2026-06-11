// components/Header.jsx
import { useNavigate } from "react-router-dom";
import styles from "./Header.styles";

const Header = () => {
  const navi = useNavigate();

  return (
    <header style={styles.header}>
      <div style={styles.inner}>
        {/* Logo */}
        <div style={styles.logo} onClick={() => navi("/")}>
          Mobbin
        </div>

        {/* Navigation */}
        <nav style={styles.nav}>
          <span style={styles.navLink} onClick={() => navi("/")}>
            홈
          </span>
          <span style={styles.navLink} onClick={() => navi("/browse")}>
            Browse
          </span>
          <span style={styles.navLink} onClick={() => navi("/collections")}>
            Collections
          </span>
        </nav>

        {/* Right Buttons */}
        <div style={styles.rightSection}>
          <button style={styles.loginBtn} onClick={() => navi("/login")}>
            Log in
          </button>
          <button style={styles.primaryBtn} onClick={() => navi("/signup")}>
            SignUp
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
