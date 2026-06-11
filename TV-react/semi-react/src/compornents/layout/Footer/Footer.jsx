// components/Layout.jsx

import styles from "./Footer.styles";

const Footer = ({ children }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <footer style={styles.footer}>
        <div style={styles.inner}>
          <div style={styles.logo}>Mobbin</div>
          <div style={styles.copyright}>
            © 2026 Mobbin. All Rights Reserved.
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Footer;
