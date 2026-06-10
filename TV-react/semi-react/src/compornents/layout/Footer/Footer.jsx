// components/Layout.jsx

import styles from "./Footer.styles";

const Footer = ({ children }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <footer className={styles.footer}>
        <p>© 2026 Agile. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Footer;
