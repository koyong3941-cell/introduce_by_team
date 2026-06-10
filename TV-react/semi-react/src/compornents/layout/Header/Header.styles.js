// components/Header.styles.js
const styles = {
  header: {
    left: 0,
    right: 0,
    height: "72px",
    display: "flex",
    alignItems: "center",
    backgroundColor: "rgba(0, 0, 0, 0.6)",
    borderBottom: "1px solid rgba(255, 255, 255, 0.1)",
    color: "white",
  },

  inner: {
    maxWidth: "1200px",
    width: "100%",
    margin: "0 auto",
    padding: "0 32px",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
  },

  logo: {
    fontSize: "26px",
    fontWeight: 600,
    letterSpacing: "-0.5px",
    cursor: "pointer",
    userSelect: "none",
    fontFamily: "'Oswald', sans-serif",
  },

  nav: {
    display: "flex",
    alignItems: "center",
    gap: "36px",
    fontSize: "15px",
  },

  navLink: {
    cursor: "pointer",
    color: "rgba(255, 255, 255, 0.8)",
    transition: "color 0.2s",
  },

  rightSection: {
    display: "flex",
    alignItems: "center",
    gap: "12px",
  },

  loginBtn: {
    padding: "9px 20px",
    fontSize: "14px",
    fontWeight: 500,
    color: "rgba(255, 255, 255, 0.9)",
    background: "transparent",
    border: "none",
    cursor: "pointer",
    fontFamily: "'Oswald', sans-serif",
  },

  primaryBtn: {
    padding: "9px 24px",
    fontSize: "14px",
    fontWeight: 600,
    backgroundColor: "white",
    color: "black",
    border: "none",
    borderRadius: "14px",
    cursor: "pointer",
    fontFamily: "'Oswald', sans-serif",
  },
};

export default styles;
