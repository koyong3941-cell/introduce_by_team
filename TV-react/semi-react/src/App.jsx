import "./App.css";
import Footer from "./compornents/layout/Footer/Footer";
import Header from "./compornents/layout/Header/Header";
import { Routes, Route } from "react-router-dom";
import SignUp from "./signup/SignUp";

function App() {
  return (
    <>
      <Header />
      <main
        style={{
          paddingTop: "72px", // ← 헤더 높이만큼
          minHeight: "100vh",
        }}
      >
        <Routes>
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </main>
      <Footer />
    </>
  );
}

export default App;
